package tech.ericwathome.presentation.onboarding_screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import tech.ericwathome.presentation.onboarding_screen.profile.components.Avatar
import tech.ericwathome.presentation.onboarding_screen.signup.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLogOut: () -> Unit,
    onBackPressed : () -> Unit
){

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressed()
                    }) {
                       Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = {
                    Text(text = "User Profile")
                },
            )
        })
    { paddingValues ->
        viewModel.currentUser?.let {
            UserInfo(
                viewModel = viewModel,
                name = it.displayName.toString(),
                email = it.email.toString(),
                modifier = Modifier.padding(paddingValues),
                onLogOut = onLogOut
            )
        }
    }
}

@Composable
fun UserInfo(
    viewModel: AuthViewModel, name: String, email: String, modifier: Modifier = Modifier,
    onLogOut : () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(16.dp)
    ) {

        Text(
            text = "Hi $name",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(12.dp)
        )
        Avatar()
        Text(
            text = "Welcome BACK",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(12.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Text(
                text = "NAME :",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(12.dp)
            )
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(12.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = "EMAIL :",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(12.dp)
            )
            Text(
                text = email,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(12.dp)
            )
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(32.dp)
        ) {
            Button(onClick = {
                viewModel.logout()
                onLogOut()
            },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth(2f)
            ) {
                Text(text = "Logout")
            }
        }
    }

}