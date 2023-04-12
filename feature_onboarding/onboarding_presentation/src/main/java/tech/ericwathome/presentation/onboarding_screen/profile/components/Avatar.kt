package tech.ericwathome.presentation.onboarding_screen.profile.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import tech.ericwathome.core_presentation.R

@Composable
fun Avatar(){

    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(12.dp)
                .clickable {
                    Toast.makeText(context, "Change Profile Photo", Toast.LENGTH_SHORT).show()
                },

        )
        {
            AvatarImage()
        }
    }

}

@Composable
fun AvatarImage(){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar_img),
                contentDescription = "avatar_image"
            )
        }
    }
}