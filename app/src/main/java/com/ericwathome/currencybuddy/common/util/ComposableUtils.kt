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
import com.ericwathome.currencybuddy.ui.theme.CurrencyBuddyTheme


/**
 * A list of common composable components to be used across the app
 */
@Composable
fun ErrorDialog(message: String, dismiss: () -> Unit) {
    Dialog(onDismissRequest = { }) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(Modifier.padding(Padding.dp_16)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_warning_svg),
                    contentDescription = stringResource(
                        id = R.string.warning_icon
                    ),
                    modifier = Modifier.size(Sizing.dp_24),
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.size(Sizing.dp_8))
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
        ErrorDialog(message = "Unable to connect. Check your connection") {

        }
    }
}

