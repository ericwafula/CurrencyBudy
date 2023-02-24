package com.ericwathome.currencybuddy.common.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.CreditCardData
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.theme.CurrencyBuddyTheme

@Composable
fun ErrorDialog(message: String) {
    Dialog(onDismissRequest = { }) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(Modifier.padding(Padding.p_16)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_warning_svg),
                    contentDescription = stringResource(
                        id = R.string.warning_icon
                    ),
                    modifier = Modifier.size(Sizing.p_24),
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.size(Sizing.p_8))
                Text(text = message)
            }
        }
    }
}

@Composable
fun ConverterCard(content: @Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.large
    ) {
        content()
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AlertDialogTemplatePreview() {
    CurrencyBuddyTheme {
        ErrorDialog(message = "Unable to connect. Check your connection")
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CurrencyConverterCardPreview() {
    CurrencyBuddyTheme {
        ConverterCard {
            CreditCardData(baseCurrency = "Currency Converter", date = "02/23")
        }
    }
}