package tech.ericwathome.data.repository

import tech.ericwathome.data.data_source.local.ExchangeRateDao
import tech.ericwathome.data.data_source.remote.CurrencyInfoApiService
import tech.ericwathome.data.data_source.remote.ExchangeRateApiService
import tech.ericwathome.data.model.CurrencyInfo
import tech.ericwathome.data.model.CurrentRate
import tech.ericwathome.data.model.relations.CurrencyInfoWithCurrentRates
import tech.ericwathome.converter_domain.repository.ExchangeRateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import tech.ericwathome.data.util.Constants
import tech.ericwathome.data.util.Resource
import java.io.IOException
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val exchangeRateApiService: ExchangeRateApiService,
    private val currencyInfoApiService: CurrencyInfoApiService,
    private val dao: ExchangeRateDao
) : ExchangeRateRepository {
    override suspend fun getExchangeRates(baseCode: String): Flow<Resource<CurrencyInfoWithCurrentRates>> =
        flow {
            emit(tech.ericwathome.data.util.Resource.Loading())
            val localCurrencyInfo = withContext(Dispatchers.IO) {
                dao.getCurrencyInfoWithCurrencyRates(baseCode)
            }
            emit(tech.ericwathome.data.util.Resource.Loading(localCurrencyInfo))
            try {
                /**
                 * fetch exchange rate data from api
                 */
                val exchangeRateDto = exchangeRateApiService.getLatestRates(baseCode)

                /**
                 * fetch currency info details from api
                 */
                val currencyDtos =
                    currencyInfoApiService.getCurrencyInfo(Constants.CURRENCY_INFO_URL)

                /**
                 * create a list of conversionRates
                 */
                val conversionRates = buildList {
                    exchangeRateDto.conversionRates.map {
                        add(
                            CurrentRate(
                                code = it.key,
                                rate = it.value,
                                currencyInfoCode = baseCode
                            )
                        )
                    }
                }
                /**
                 * update conversion rates objects with a currency name and a symbol
                 */
                val updatedConversionRates = conversionRates.map { currentRate ->
                    val currencyDto = currencyDtos.find { it.code == currentRate.code }
                    currentRate.copy(
                        name = currencyDto?.name,
                        symbol = currencyDto?.symbol
                    )
                }

                /**
                 * creates a currencyInfo object, finds a single base rate and updates the
                 * currency info object with a symbol and a name
                 */
                val currencyInfo = CurrencyInfo(code = baseCode)
                val currentBaseRate = updatedConversionRates.find { it.code == baseCode }
                currencyInfo.apply {
                    symbol = currentBaseRate?.symbol
                    name = currentBaseRate?.name
                }
                /**
                 * Remove all items in the database and add them again
                 */
                dao.apply {
                    updatedConversionRates.forEach {
                        deleteCurrencyRate(it)
                    }
                    updatedConversionRates.forEach {
                        insertCurrencyRate(it)
                    }
                    deleteCurrencyInfo(currencyInfo)
                    insertCurrencyInfo(currencyInfo)
                }

            } catch (e: HttpException) {
                emit(
                    tech.ericwathome.data.util.Resource.Error(
                        message = "Oops! Something went wrong.",
                        data = localCurrencyInfo
                    )
                )
            } catch (e: IOException) {
                emit(
                    tech.ericwathome.data.util.Resource.Error(
                        message = "Couldn't reach server, check your internet connection.",
                        data = localCurrencyInfo
                    )
                )
            }
            val newLocalCurrencyInfo = withContext(Dispatchers.IO) {
                dao.getCurrencyInfoWithCurrencyRates(baseCode)
            }
            emit(tech.ericwathome.data.util.Resource.Success(newLocalCurrencyInfo))
        }

}