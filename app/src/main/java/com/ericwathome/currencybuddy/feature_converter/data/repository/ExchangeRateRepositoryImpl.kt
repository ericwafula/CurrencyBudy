package com.ericwathome.currencybuddy.feature_converter.data.repository

import com.ericwathome.currencybuddy.common.AppConstants
import com.ericwathome.currencybuddy.common.Resource
import com.ericwathome.currencybuddy.feature_converter.data.data_source.local.ExchangeRateDao
import com.ericwathome.currencybuddy.feature_converter.data.data_source.remote.CurrencyInfoApiService
import com.ericwathome.currencybuddy.feature_converter.data.data_source.remote.ExchangeRateApiService
import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrencyInfo
import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrentRate
import com.ericwathome.currencybuddy.feature_converter.domain.model.relations.CurrencyInfoWithCurrentRates
import com.ericwathome.currencybuddy.feature_converter.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val exchangeRateApiService: ExchangeRateApiService,
    private val currencyInfoApiService: CurrencyInfoApiService,
    private val dao: ExchangeRateDao
) : ExchangeRateRepository {
    override suspend fun getExchangeRates(baseCode: String): Flow<Resource<CurrencyInfoWithCurrentRates>> =
        flow {
            emit(Resource.Loading())
            val localCurrencyInfo = dao.getCurrencyInfoWithCurrencyRates(baseCode)
            emit(Resource.Loading(localCurrencyInfo))
            try {
                /**
                 * fetch exchange rate data from api
                 */
                val exchangeRateDto = exchangeRateApiService.getLatestRates(baseCode)

                /**
                 * fetch currency info details from api
                 */
                val currencyDtos =
                    currencyInfoApiService.getCurrencyInfo(AppConstants.CURRENCY_INFO_URL)

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
                 * update conversion rates objects with currency name and symbol
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
                    Resource.Error(
                        message = "Oops! Something went wrong.",
                        data = localCurrencyInfo
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Couldn't reach server, check your internet connection.",
                        data = localCurrencyInfo
                    )
                )
            }
            val newLocalCurrencyInfo = dao.getCurrencyInfoWithCurrencyRates(baseCode)
            emit(Resource.Success(newLocalCurrencyInfo))
        }

}