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
import com.ericwathome.currencybuddy.common.util.*
import com.ericwathome.currencybuddy.ui.theme.CurrencyBuddyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyPicker(
    currencyCode: (String) -> Unit,
    currentPrice: (String) -> Unit,
    receivedCurrencyCode: String,
    receivedPrice: String,
    receivedSymbol: String,
    enabled: Boolean,
    onButtonClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
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
                    showDialog = !showDialog
                    onButtonClick()
                },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = Padding.dp_24, vertical = Padding.dp_24),
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
        TextField(
            value = receivedPrice,
            onValueChange = { currentPrice(it) },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = Sizing.dp_55),
            shape = RoundedCornerShape(Radius.dp_8),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                textColor = MaterialTheme.colorScheme.onTertiary,
            ),
            textStyle = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal,
                fontSize = TextSizing.sp_30
            ),
            enabled = enabled
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
            receivedCurrencyCode = "EUR",
            receivedPrice = "120.00",
            receivedSymbol = "€",
            enabled = true
        ) {

        }
    }
}