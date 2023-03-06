package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.core.presentation.*
import com.ericwathome.currencybuddy.feature_converter.presentation.util.Currency
import com.ericwathome.currencybuddy.core.util.*
import tech.ericwathome.presentation.theme.CurrencyBuddyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyPicker(
    currencyCode: (String) -> Unit,
    currentPrice: (String) -> Unit,
    currentSymbol: (String) -> Unit,
    receivedCurrencyCode: String,
    receivedPrice: String,
    receivedSymbol: String,
    receivedCurrencies: List<Currency>,
    enabled: Boolean
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CurrencyPickerDialog(
            currencies = receivedCurrencies,
            currencyCode = {
                currencyCode(it)
            },
            currencySymbol = {
                if (it.isNotEmpty()) {
                    currentSymbol(it)
                }
            }
        ) {
            showDialog = false
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spacing.dp_24)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Padding.dp_12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.clickable {
                    showDialog = true
                },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = Padding.dp_24, vertical = Padding.dp_16),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.dp_24)
                ) {
                    Text(text = receivedCurrencyCode)
                    Icon(
                        imageVector = if (showDialog) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = stringResource(id = if (showDialog) R.string.show_less else R.string.show_more)
                    )
                }
            }
            Text(
                text = receivedSymbol, style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = TextSizing.sp_30
                )
            )
        }
        OutlinedTextField(
            value = receivedPrice,
            onValueChange = { currentPrice(it) },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = Sizing.dp_55),
            shape = RoundedCornerShape(Radius.dp_8),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.onTertiary,
                unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
                cursorColor = MaterialTheme.colorScheme.onTertiary,
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                disabledTextColor = MaterialTheme.colorScheme.onTertiary
            ),
            textStyle = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal,
                fontSize = TextSizing.sp_24
            ),
            enabled = enabled,
        )
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CurrencyPicker() {
    CurrencyBuddyTheme {
        CurrencyPicker(
            currencyCode = { },
            currentPrice = { },
            currentSymbol = { },
            receivedCurrencyCode = "EUR",
            receivedPrice = "120.00",
            receivedSymbol = "€",
            receivedCurrencies = buildList {
                add(Currency("EUR", "Euro", ""))
                add(Currency("USD", "United States Dollar", ""))
                add(Currency("CAD", "Canadian Dollar", ""))
                add(Currency("JPY", "Japanese Yen", ""))
                add(Currency("AUD", "Australian Dollar", ""))
                add(Currency("NZD", "New Zealand Dollar", ""))
            },
            enabled = true
        )
    }
}