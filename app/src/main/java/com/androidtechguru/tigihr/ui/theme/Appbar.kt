@file:OptIn(ExperimentalMaterial3Api::class)

package com.androidtechguru.tigihr.ui.theme


import android.graphics.Paint.Align
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BillingAppBar(title: String, onBackPressed: () -> Unit = {}) {
    Scaffold(
        // AppBar content
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Checkout",
                    modifier = Modifier.fillMaxSize(),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {

        }
    )
}

//private val AppBarHeight = with(LocalDensity.current) { 56.dp.toPx().dp }

@Composable
fun Divider(
    color: Color = Color.Gray,
    thickness: Dp = 2.dp,
) {
    Spacer(modifier = Modifier.height(16.dp))
    Surface(
        modifier = Modifier
            .height(thickness)
            .fillMaxWidth(),
        color = color.copy(alpha = 0.2f),
    ) {}
    Spacer(modifier = Modifier.height(20.dp))

}

val AppColors = lightColorScheme(
    primary = AppColor,
    secondary = Color.Green,
    background = Color.White,
)


val HeaderTextStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp
)
