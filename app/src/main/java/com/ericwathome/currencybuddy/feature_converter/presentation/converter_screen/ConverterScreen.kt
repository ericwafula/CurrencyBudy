package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.theme.CurrencyBuddyTheme
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen() {
    val viewModel: ConverterViewModel = hiltViewModel()
    val state by viewModel.converterState
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    var dialogMessage by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
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
        if (showErrorDialog) {
            AlertDialog(onDismissRequest = { showErrorDialog = false }) {
                Surface {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_warning),
                            contentDescription = stringResource(
                                id = R.string.warning_icon
                            ),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = dialogMessage)
                    }
                }
            }
        }
        if (state.loading) {
            AlertDialog(onDismissRequest = { }) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(60.dp),
                    progress = 0.5f
                )
            }
        }
        ImageCard()
        Spacer(modifier = Modifier.height(48.dp))
        ConverterCard(
            state.data?.currencies ?: emptyList(),
            viewModel.selectedBase.value,
            viewModel.selectedQuote.value,
            state.data?.baseSymbol ?: "",
            state.data?.quoteSymbol ?: "",
            viewModel.selectedBaseAmount.value,
            updateSelectedBaseCurrency = {
                viewModel.updateSelectedBaseCurrency(it)
            },
            updateSelectedQuoteCurrency = {
                viewModel.updateSelectedQuoteCurrency(it)
            },
            changeSelectedBaseCurrencyPrice = {
                viewModel.changeSelectedBaseCurrencyPrice(it)
            },
            baseConversionRate = state.data?.baseConversionRate ?: "",
            selectedQuotePrice = state.data?.quotePrice ?: ""
        ) {
            viewModel.convert()
        }
    }
}

@Composable
fun ImageCard() {
    Image(
        painter = painterResource(id = R.drawable.credit_card),
        contentDescription = stringResource(
            id = R.string.credit_card
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 24.dp)
    )
}

@Composable
fun ConverterCard(
    currencies: List<String>,
    selectedBase: String,
    selectedQuote: String,
    selectedBaseSymbol: String,
    selectedQuoteSymbol: String,
    selectedBasePrice: String,
    selectedQuotePrice: String = "0",
    baseConversionRate: String,
    updateSelectedBaseCurrency: (String) -> Unit,
    updateSelectedQuoteCurrency: (String) -> Unit,
    changeSelectedBaseCurrencyPrice: (String) -> Unit,
    convert: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(
            topStart = 40.dp,
            topEnd = 40.dp
        ),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 24.dp)) {
            CurrencyDetails(
                currencies = currencies,
                selectedCurrency = selectedBase,
                changeSelectedCurrency = {
                    updateSelectedBaseCurrency(it)
                },
                selectedSymbol = selectedBaseSymbol,
                selectedCurrencyPrice = selectedBasePrice,
                changeSelectedCurrencyPrice = {
                    changeSelectedBaseCurrencyPrice(it)
                },
                conversionRate = baseConversionRate
            )
            Spacer(modifier = Modifier.size(30.dp))
            ExtendedFloatingActionButton(
                onClick = convert,
                modifier = Modifier.widthIn(60.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_convert),
                    contentDescription = stringResource(
                        id = R.string.convert_icon
                    )
                )
            }
            Spacer(modifier = Modifier.size(30.dp))
            CurrencyDetails(
                currencies = currencies,
                selectedCurrency = selectedQuote,
                changeSelectedCurrency = {
                    updateSelectedQuoteCurrency(it)
                },
                selectedSymbol = selectedQuoteSymbol,
                selectedCurrencyPrice = selectedQuotePrice,
                conversionRate = "",
                changeSelectedCurrencyPrice = { }
            )
        }
    }
}

@Composable
fun CurrencyDetails(
    currencies: List<String>,
    selectedCurrency: String,
    selectedSymbol: String,
    selectedCurrencyPrice: String,
    conversionRate: String,
    changeSelectedCurrency: (String) -> Unit,
    changeSelectedCurrencyPrice: (String) -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
        Column {
            CurrencySpinner(currencies = currencies, selected = selectedCurrency) {
                changeSelectedCurrency(it)
            }
            Row {
                Text(text = selectedSymbol)
                Row {
                    BasicTextField(
                        value = selectedCurrencyPrice,
                        onValueChange = { changeSelectedCurrencyPrice(it) },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 42.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface)
                    )
                }
            }
            Text(
                text = conversionRate, style = TextStyle(
                    fontSize = 10.sp
                ), modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun CurrencySpinner(
    currencies: List<String>,
    selected: String,
    onSelect: (selected: String) -> Unit
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Button(
        onClick = { expanded = true },
        contentPadding = PaddingValues(horizontal = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(text = selected, style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant))
        Spacer(modifier = Modifier.size(8.dp))
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(id = R.string.dropdown_arrow),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        currencies.forEach { currency ->
            DropdownMenuItem(text = {
                Text(text = currency)
            }, onClick = {
                expanded = false
                onSelect(currency)
            })
        }
    }

}

@Preview
@Composable
fun ConverterScreenPreview() {
    CurrencyBuddyTheme {
        ConverterScreen()
    }
}

@Preview
@Composable
fun CurrencyDetailsPreview() {
    CurrencyBuddyTheme {
        CurrencyDetails(
            currencies = remember { mutableStateListOf("EUR", "USD", "CAD", "JPY") },
            selectedCurrency = "EUR",
            changeSelectedCurrency = { },
            selectedSymbol = "€",
            selectedCurrencyPrice = "120.00",
            changeSelectedCurrencyPrice = { },
            conversionRate = "1 USD = 0.90 EUR"
        )
    }
}

@Preview
@Composable
fun SpinnerPreview() {
    CurrencyBuddyTheme {
        CurrencySpinner(
            selected = "EUR", currencies = listOf(
                "EUR", "USD", "CAD", "JPY"
            )
        ) {

        }
    }
}