package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.common.util.*
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.theme.CurrencyBuddyTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ConverterScreen() {
    val viewModel: ConverterViewModel = hiltViewModel()
    val state by viewModel.converterState
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    var dialogMessage by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(top = Padding.p_24)
    ) {

        /**
         * set error dialog state and message when an error occurs
         */
        LaunchedEffect(key1 = true) {
            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is ConverterViewModel.UiEvent.ShowDialog -> {
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
            ErrorDialog(message = dialogMessage)
        }


    }
}

@Composable
fun CreditCardData(baseCurrency: String, date: String) {
    Column(
        modifier = Modifier
            .padding(Padding.p_24)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spacing.p_24)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Currency Converter")
            Image(
                painter = painterResource(id = R.drawable.credit_card_chip),
                contentDescription = stringResource(
                    id = R.string.credit_card_chip
                )
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            repeat(3) {
                Text(
                    text = "* * * *",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = TextSizing.p_24
                    )
                )
            }
            Text(
                text = "$1234",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = TextSizing.p_24
                )
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = "02/23")
            Image(
                painter = painterResource(id = R.drawable.master_card_logo),
                contentDescription = stringResource(
                    id = R.string.master_card_logo
                )
            )
        }
    }
}