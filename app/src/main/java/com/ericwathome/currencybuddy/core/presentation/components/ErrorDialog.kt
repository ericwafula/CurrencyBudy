package com.ericwathome.currencybuddy.core.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.core.presentation.Padding
import com.ericwathome.currencybuddy.core.presentation.Sizing
import com.ericwathome.currencybuddy.core.presentation.theme.CurrencyBuddyTheme

@Composable
fun ErrorDialog(message: String, dismiss: () -> Unit) {
    Dialog(onDismissRequest = { dismiss() }) {
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AlertDialogTemplatePreview() {
    CurrencyBuddyTheme {
        ErrorDialog(message = "Unable to connect. Check your connection") {

        }
    }
}