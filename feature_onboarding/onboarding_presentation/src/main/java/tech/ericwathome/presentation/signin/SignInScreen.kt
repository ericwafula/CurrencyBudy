package tech.ericwathome.presentation.signin

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
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
import tech.ericwathome.presentation.theme.CurrencyBuddyTheme

@Composable
fun SignInScreen(
    onLogin : () -> Unit,
    onSignUp : () -> Unit,
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
                    vertical = 62.dp
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
            onClick = { onLogin() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 15.dp
                ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Login",
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
                    text = "Don't have an account?",
                    color = Color.LightGray
                )
                Text(
                    text = "Create One",
                    modifier = Modifier
                        .clickable { onSignUp() }
                )
            }
        }

        Text(
            text = "Or",
            color = Color.LightGray,
            modifier = Modifier
                .padding(vertical = 62.dp)
        )


        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            GoogleButton{

            }
        }
    }
}


@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    text: String = "Sign Up with Google",
    loadingText: String = "Creating Account...",
    icon: Int = R.drawable.ic_google_logo,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    progressIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    onClicked: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.clickable { clicked = !clicked },
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor

    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Google Button",
                tint = Color.Unspecified,

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (clicked) loadingText else text,
                modifier.padding(6.dp)

            )
            if (clicked) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
                onClicked()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    val darkTheme : Boolean = isSystemInDarkTheme()
    CurrencyBuddyTheme(darkTheme = true) {
//        SignInScreen()
    }
}

