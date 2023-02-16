package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var selected by remember {
        mutableStateOf("EUR")
    }
    Column(modifier = Modifier.padding(vertical = 48.dp, horizontal = 16.dp)) {
        CurrencySpinner(currencies = currencies, selected = selected) {
            selected = it
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencySpinner(
    currencies: List<String>,
    selected: String,
    onSelect: (selected: String) -> Unit
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Button(onClick = { expanded = true }) {
        Text(text = selected)
        Spacer(modifier = Modifier.size(8.dp))
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(id = R.string.dropdown_arrow),
            modifier = Modifier.clickable {
                expanded = true
            }
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