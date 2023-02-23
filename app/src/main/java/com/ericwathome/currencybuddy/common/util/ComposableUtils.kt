package com.ericwathome.currencybuddy.common.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.common.PaddingValues
import com.ericwathome.currencybuddy.common.SizingValues
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.theme.CurrencyBuddyTheme
import com.ericwathome.currencybuddy.feature_converter.presentation.util.Currency

@Composable
fun AlertDialogTemplate(message: String) {
    Dialog(onDismissRequest = { }) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(Modifier.padding(PaddingValues.p_16)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_warning_svg),
                    contentDescription = stringResource(
                        id = R.string.warning_icon
                    ),
                    modifier = Modifier.size(SizingValues.p_24),
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.size(SizingValues.p_8))
                Text(text = message)
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AlertDialogTemplatePreview() {
    CurrencyBuddyTheme {
        AlertDialogTemplate(message = "Unable to connect. Check your connection")
    }
}