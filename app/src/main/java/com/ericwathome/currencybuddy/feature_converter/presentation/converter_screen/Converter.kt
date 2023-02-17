package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.theme.CurrencyBuddyTheme

@Composable
fun ConverterScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        ImageCard()
        Spacer(modifier = Modifier.height(48.dp))
        ConverterCard {
            CardContent()
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
            .width(400.dp)
            .height(200.dp)
            .padding(horizontal = 24.dp)
    )
}

@Composable
fun ConverterCard(
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(
            topStart = 40.dp,
            topEnd = 40.dp
        )
    ) {
        content()
    }
}

@Composable
fun CardContent() {
    val currencies = remember {
        mutableStateListOf("EUR", "USD", "CAD", "JPY")
    }
    var selectedBaseSymbol by remember { mutableStateOf("€") }
    var selectedBase by remember {
        mutableStateOf("EUR")
    }
    var conversionRate by remember { mutableStateOf("1 USD = 0.90 EUR") }
    var selectedQuoteSymbol by remember { mutableStateOf("€") }
    var selectedQuote by remember {
        mutableStateOf("EUR")
    }
    var selectedBasePrice by remember { mutableStateOf("0.90") }
    Column(modifier = Modifier.padding(vertical = 30.dp, horizontal = 24.dp)) {
        CurrencyDetails(
            currencies = currencies,
            selectedBase = selectedBase,
            changeSelectedBase = {
                selectedBase = it
            },
            selectedBaseSymbol = selectedBaseSymbol,
            selectedBasePrice = selectedBasePrice,
            changeSelectedBasePrice = {
                selectedBasePrice = it
            },
            conversionRate = conversionRate
        )

    }
}

@Composable
fun CurrencyDetails(
    currencies: SnapshotStateList<String>,
    selectedBase: String,
    selectedBaseSymbol: String,
    selectedBasePrice: String,
    conversionRate: String,
    changeSelectedBase: (String) -> Unit,
    changeSelectedBasePrice: (String) -> Unit
) {
    Surface {
        Column {
            CurrencySpinner(currencies = currencies, selected = selectedBase) {
                changeSelectedBase(it)
            }
            Row {
                Text(text = selectedBaseSymbol)
                Row {
                    BasicTextField(
                        value = selectedBasePrice,
                        onValueChange = { changeSelectedBasePrice(it) },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 42.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface)
                    )
                }
            }
            Text(text = conversionRate, style = TextStyle(
                fontSize = 10.sp
            ), modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
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
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Text(text = selected)
        Spacer(modifier = Modifier.size(8.dp))
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(id = R.string.dropdown_arrow)
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
            currencies = remember { mutableStateListOf() },
            selectedBase = "EUR",
            changeSelectedBase = { },
            selectedBaseSymbol = "€",
            selectedBasePrice = "120.00",
            changeSelectedBasePrice = { },
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