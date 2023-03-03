package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.*
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.common.util.Padding
import com.ericwathome.currencybuddy.common.util.Spacing
import com.ericwathome.currencybuddy.feature_converter.presentation.util.Currency
import com.ericwathome.currencybuddy.ui.theme.CurrencyBuddyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyPickerDialog(
    currencies: List<Currency>,
    currencyCode: (String) -> Unit,
    currencySymbol: (String) -> Unit,
    dismissDialog: () -> Unit
) {
    var textFieldState by remember { mutableStateOf("") }
    var filteredCurrencyList = remember { mutableStateOf(currencies) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.shake_empty_box))

    Dialog(onDismissRequest = { dismissDialog() }) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Spacing.dp_12),
                modifier = Modifier.padding(Padding.dp_32)
            ) {
                OutlinedTextField(
                    value = textFieldState,
                    onValueChange = {
                        textFieldState = it
                        filteredCurrencyList.value = currencies.filter { currency ->
                            currency.name?.contains(
                                it,
                                ignoreCase = true
                            ) == true || currency.code?.contains(it, ignoreCase = true) == true
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary
                    ),
                    placeholder = {
                        Text(text = stringResource(id = R.string.search))
                    }
                )
                if (filteredCurrencyList.value.isEmpty()) {
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    LazyColumn {
                        itemsIndexed(filteredCurrencyList.value) { index, currency ->
                            val (code, name, symbol) = currency
                            Card(
                                modifier = Modifier
                                    .clickable {
                                        code?.let { currencyCode(code) }
                                        symbol?.let { currencySymbol(symbol) }
                                        dismissDialog()
                                    }
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "$name, $code",
                                    modifier = Modifier
                                        .padding(vertical = Padding.dp_24)
                                )
                            }
                            if (index != filteredCurrencyList.value.lastIndex) {
                                Divider(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colorScheme.background
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CurrencyPickerDialog() {
    CurrencyBuddyTheme {
        CurrencyPickerDialog(
            currencies = buildList {
                add(Currency("EUR", "Euro", ""))
                add(Currency("USD", "United States Dollar", ""))
                add(Currency("CAD", "Canadian Dollar", ""))
                add(Currency("JPY", "Japanese Yen", ""))
                add(Currency("AUD", "Australian Dollar", ""))
                add(Currency("NZD", "New Zealand Dollar", ""))
            },
            currencyCode = { },
            currencySymbol = { }
        ) {

        }
    }
}