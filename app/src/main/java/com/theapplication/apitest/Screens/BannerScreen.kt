package com.theapplication.apitest.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.theapplication.apitest.R
import com.theapplication.apitest.navigation.Route

@Composable
fun BannerScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.banner), contentDescription = null,
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(color = Color.White.copy(0.4f), RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                .border(0.5.dp, Color.White, RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val offser = Offset(2.0f, 5f)
            Text(
                text = "Check out Your Best Movies Collection",
                modifier = Modifier.padding(vertical = 25.dp),
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 24.sp,
                    shadow = Shadow(
                        color = Color(0xFFFC5A03),
                        offset = offser
                    ),
                    fontFamily = FontFamily(Font(R.font.aclonica)),
                    textAlign = TextAlign.Center
                )
            )

            val linearGradientBrush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFB226E1),
                    Color(0xFFE64514),
                    Color(0xFF1565C0),
                    Color(0xFF3B3A3C)

                ),
                start = Offset(Float.POSITIVE_INFINITY,0f),
                end = Offset(0f, Float.POSITIVE_INFINITY)
            )

            Button(
                onClick = {
                          navController.navigate(Route.HomeScreen.route)
                }, modifier = Modifier
                    .padding(bottom = 55.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .background(color = Color.Gray.copy(0.8f), RoundedCornerShape(16.dp))
                    .border(
                        BorderStroke(3.dp, linearGradientBrush),
                        shape = RoundedCornerShape(16.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {

                Text(text = "Explore more..", style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.atomic_age)),
                    textAlign = TextAlign.Center
                ))
            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun bnnprev() {
//    BannerScreen()
//}