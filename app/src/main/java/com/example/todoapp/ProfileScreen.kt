import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(
    userProfile: UserProfile, // Profile data model
    navController: NavHostController? = null // Navigation controller if needed
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Profile Picture
        if (userProfile.profilePicture != null) {
            Image(
                painter = painterResource(id = userProfile.profilePicture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape) // Circular shape
                    .padding(8.dp),
            )
        } else {
            // Default placeholder for profile picture
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray), // Default gray color
                contentAlignment = Alignment.Center
            ) {
                Text("N/A", fontSize = 32.sp, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User Information
        Text(text = "Name: ${userProfile.name}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "Email: ${userProfile.email}", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Edit Profile Button
        Button(
            onClick = {
                // Handle profile editing logic here
            }
        ) {
            Text("Edit Profile")
        }
    }
}

data class UserProfile (
    val name: String,
    val email: String,
    val profilePicture: Int? = null
)
