package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.core.util.Padding
import com.ericwathome.currencybuddy.core.util.Spacing
import com.ericwathome.currencybuddy.core.util.TextSizing
import com.ericwathome.currencybuddy.core.presentation.components.ConverterCard
import com.ericwathome.currencybuddy.core.presentation.components.ErrorDialog
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.components.CurrencyPicker
import com.ericwathome.currencybuddy.core.presentation.theme.CurrencyBuddyTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ConverterScreen() {
    val viewModel: ConverterViewModel = hiltViewModel()
    val state by viewModel.converterState
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    var dialogMessage by rememberSaveable { mutableStateOf("") }

    /**
     * set error dialog state and message when an error occurs
     */
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ConverterEvent.ShowDialog -> {
                    showErrorDialog = true
                    dialogMessage = event.message
                }
            }
        }
    }

    /**
     * show alert dialog based on the current error state
     */
    if (showErrorDialog) {
        ErrorDialog(message = dialogMessage) {
            showErrorDialog = false
        }
    }

    /**
     * show progress bar when loading
     */
    if (state.loading) {
        Dialog(onDismissRequest = { state.loading }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
    }
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = Padding.dp_32, horizontal = Padding.dp_24),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ConverterCard {
            CreditCardData(
                currentBaseVsQuote = "${state.data?.currentBaseVsQuote}" ?: "",
                accountNumber = state.data?.accountNumber ?: "",
                expiryDate = state.data?.expiryDate ?: ""
            )
        }

        CurrencyPicker(
            receivedCurrencies = state.data?.currencies ?: emptyList(),
            currencyCode = { viewModel.updateSelectedBaseCurrency(it) },
            currentPrice = { viewModel.changeSelectedBaseCurrencyPrice(it) },
            currentSymbol = { viewModel.updateBaseSymbol(it) },
            receivedCurrencyCode = viewModel.selectedBase.value,
            receivedPrice = viewModel?.selectedBaseAmount?.value ?: "",
            receivedSymbol = state?.data?.baseSymbol ?: "",
            enabled = true
        )
        FloatingActionButton(
            onClick = { viewModel.convert() },
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(0.3f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_convert),
                contentDescription = stringResource(
                    id = R.string.convert_icon
                )
            )
        }
        CurrencyPicker(
            receivedCurrencies = state.data?.currencies ?: emptyList(),
            currencyCode = { viewModel.updateSelectedQuoteCurrency(it) },
            currentPrice = { },
            currentSymbol = { viewModel.updateQuoteSymbol(it) },
            receivedCurrencyCode = viewModel.selectedQuote.value,
            receivedPrice = state?.data?.quotePrice ?: "",
            receivedSymbol = state?.data?.quoteSymbol ?: "",
            enabled = false
        )
    }
}

/**
 * shows the data in the credit card
 */
@Composable
fun CreditCardData(currentBaseVsQuote: String, accountNumber: String, expiryDate: String) {
    Column(
        modifier = Modifier
            .padding(Padding.dp_24)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spacing.dp_24)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = currentBaseVsQuote, style = TextStyle(
                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                )
            )
            Image(
                painter = painterResource(id = R.drawable.credit_card_chip),
                contentDescription = stringResource(
                    id = R.string.credit_card_chip
                )
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            for (character in accountNumber) {
                Text(
                    text = "$character",
                    style = TextStyle(fontSize = TextSizing.sp_24, fontWeight = FontWeight.Bold)
                )
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = expiryDate)
            Image(
                painter = painterResource(id = R.drawable.master_card_logo),
                contentDescription = stringResource(
                    id = R.string.master_card_logo
                )
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CurrencyConverterCardPreview() {
    CurrencyBuddyTheme {
        ConverterCard {
            CreditCardData(
                currentBaseVsQuote = "Euro\nvs\nUnited States Dollar",
                accountNumber = "**** *$13 3.70",
                expiryDate = "02/23"
            )
        }
    }
}