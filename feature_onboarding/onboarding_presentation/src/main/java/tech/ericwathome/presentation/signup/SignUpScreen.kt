package tech.ericwathome.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.ericwathome.core_presentation.R
import tech.ericwathome.presentation.signin.GoogleButton
import tech.ericwathome.presentation.theme.CurrencyBuddyTheme

@Composable
fun SignUpScreen(
    onSignUp :() -> Unit,
    onLogin : () -> Unit

){

    var value by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
    ) {

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 42.dp
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.currency_buddy_logo),
                contentDescription = "currency_buddy_logo" )
        }

        OutlinedTextField(
            value = value,
            onValueChange = {value = it},
            label = {
                Text(text = "Name")
            },
            placeholder = {
                Text(text = "Name")
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 15.dp
                ),
            singleLine = true,
        )

        OutlinedTextField(
            value = value,
            onValueChange = {value = it},
            label = {
                Text(text = "Email")
            },
            placeholder = {
                Text(text = "Password")
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 15.dp
                ),
            singleLine = true,
        )

        OutlinedTextField(
            value = value,
            onValueChange = {value = it},
            label = {
                Text(text = "Password")
            },
            placeholder = {
                Text(text = "Password")
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 15.dp
                ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done),
            visualTransformation = if(isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (isPasswordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (isPasswordVisible) "Hide password" else "Show password"

                IconButton(onClick = { isPasswordVisible =! isPasswordVisible }) {
                    Icon(
                        imageVector = image,
                        contentDescription = description
                    )
                }
            },
            singleLine = true,
        )

        Button(
            onClick = { onSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 15.dp
                ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Sign Up",
                modifier = Modifier
                    .padding(6.dp)
            )
        }

        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 4.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,

                ) {

                Text(
                    text = "Already have an account?",
                    color = Color.LightGray
                )
                Text(
                    text = "Login",
                    modifier = Modifier
                        .clickable { onLogin() }
                )
            }
        }

        Text(
            text = "Or",
            color = Color.LightGray,
            modifier = Modifier
                .padding(vertical = 42.dp)
        )


        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            GoogleButton{

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    val darkTheme : Boolean = isSystemInDarkTheme()
    CurrencyBuddyTheme(darkTheme = true) {
//        SignUpScreen()
    }
}

