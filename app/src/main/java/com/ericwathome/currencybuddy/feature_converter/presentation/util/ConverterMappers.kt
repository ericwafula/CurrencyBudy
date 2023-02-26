package com.ericwathome.currencybuddy.feature_converter.presentation.util

import com.ericwathome.currencybuddy.feature_converter.domain.model.relations.CurrencyInfoWithCurrentRates
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.ConverterValues
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

internal fun mapResultData(
    result: CurrencyInfoWithCurrentRates?,
    amount: Double,
    quoteCurrencyCode: String
): ConverterValues {

    /**
     * map rates into Currency objects
     */
    val currencyList = result?.rates?.map {
        Currency(
            code = it.code,
            name = it.name,
            symbol = it.symbol
        )
    }

    val baseCode = result?.currencyInfo?.code ?: ""
    val baseSymbol = result?.currencyInfo?.symbol ?: ""
    val currentRateObject = result?.rates?.find { it.code == quoteCurrencyCode }
    val quoteCode = currentRateObject?.code ?: ""
    val baseConversionRate = currentRateObject?.rate ?: 0.0
    val quoteSymbol = currentRateObject?.symbol ?: ""
    val quotePrice = "%.2f".format((amount * baseConversionRate))

    /**
     * create account number using the current conversion rates
     */
    val accountNumber = buildString {
        /**
         * get the initial slots in the string
         */
        val price = "$quoteSymbol${"%.2f".format(baseConversionRate)}"
        val initialSlots = 14 - price.length
        var characterPosition = 0
        if (price.isNotBlank()) {
            /**
             * populate the initial slots with asterisks
             */
            for (i in 1 until initialSlots) {
                /**
                 * check for the current slot position and and check if it's divisible by 5
                 * if it is, then the slot should contain a space else the slot should contain an
                 * asterisk eg **** **** ****
                 */
                if (i % 5 == 0) append(" ") else append("*")
            }
            /**
             * populate the remaining slots with the current price including the quote currency's
             * symbol
             */
            for (i in initialSlots..14) {
                /**
                 * check for the current slot position and and check if it's divisible by 5
                 * if it is, then the slot should contain a space else the slot should contain an
                 * a character from the quotePrice eg **** *$13 3.70
                 */
                if (i % 5 == 0) {
                    append(" ")
                } else {
                    append("${price[characterPosition]}")
                    characterPosition++
                }
            }
        } else {
            /**
             * append the values based on the current position
             */
            for (i in 1..14) {
                if (i % 5 == 0) append(" ") else append("*")
            }
        }
    }

    /**
     * create a date string from the current date
     */
    val date = ZonedDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("MM/yy")
    val expiryDate = formatter.format(date)
    val baseCurrencyName = result?.rates?.find { it.code == baseCode }?.name ?: ""
    val quoteCurrencyName = result?.rates?.find { it.code == quoteCode }?.name ?: ""
    val currentBaseVsQuote = "$baseCurrencyName\nvs\n$quoteCurrencyName"

    return ConverterValues(
        currencies = currencyList,
        baseSymbol = baseSymbol,
        baseConversionRate = "1 $baseCode = $baseConversionRate $quoteCode",
        quoteSymbol = quoteSymbol,
        quotePrice = quotePrice,
        currencyName = result?.currencyInfo?.name,
        accountNumber = accountNumber,
        expiryDate = expiryDate,
        currentBaseVsQuote = currentBaseVsQuote
    )
}