package com.theapplication.apitest

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.theapplication.apitest.Screens.BannerScreen
import com.theapplication.apitest.Screens.HomeScreen
import com.theapplication.apitest.navigation.Navigation
import com.theapplication.apitest.ui.theme.ApiTestTheme
import com.theapplication.apitest.viewmodels.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiTestTheme {
                WindowCompat.setDecorFitsSystemWindows(window,false)
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,

                    )


                val linearGradientBrush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFAD1457),
                        Color(0xFFE64514),
                        Color(0xFF1565C0),
                        Color(0xFF3B3A3C)

                    ),
                    start = Offset(Float.POSITIVE_INFINITY,0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val movieViewModel= viewModel<MovieViewModel>()
                    val state = movieViewModel.state
                    //Navigation()
                    Box(modifier = Modifier.fillMaxSize().background(linearGradientBrush)){
                        Navigation()

                    }

                }
            }
        }
    }
}

