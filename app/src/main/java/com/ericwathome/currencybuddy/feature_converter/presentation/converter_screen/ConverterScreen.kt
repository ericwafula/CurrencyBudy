package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ericwathome.currencybuddy.common.util.Padding
import com.ericwathome.currencybuddy.common.util.ErrorDialog
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ConverterScreen() {
    val viewModel: ConverterViewModel = hiltViewModel()
    val state by viewModel.converterState
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    var dialogMessage by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(top = Padding.p_24)
    ) {

        /**
         * set error dialog state and message when an error occurs
         */
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

        /**
         * show alert dialog based on the current error state
         */
        if (showErrorDialog) {
            ErrorDialog(message = dialogMessage)
        }


    }
}

