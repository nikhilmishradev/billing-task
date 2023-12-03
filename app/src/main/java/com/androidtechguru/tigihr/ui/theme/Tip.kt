@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.androidtechguru.tigihr.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class TipOption(val amount: Int) {
    NO_TIP(0),
    TIP_5(5),
    TIP_10(10),
    TIP_15(15)
}

@Composable
fun TipSelectionButtons() {
    var selectedTipOption by remember { mutableStateOf(TipOption.NO_TIP) }

    Row(
//        modifier = Modifier.padding(4.dp)
    ) {
        TipOption.values().forEach { tipOption ->

            val textColor = if (tipOption == selectedTipOption) Color.White else Color.Black
            val backgroundColor = if (tipOption == selectedTipOption) MaterialTheme.colorScheme.primary else Color.LightGray

            Button(
                onClick = { selectedTipOption = tipOption },
                modifier = Modifier.weight(1f)
                    .wrapContentHeight()
                    .wrapContentWidth(),
//                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor,
                    contentColor = Color.White
                )

            ) {
                val buttonText = when (tipOption) {
                    TipOption.NO_TIP -> "No tip"
                    TipOption.TIP_5 -> "5₹"
                    TipOption.TIP_10 -> "10₹"
                    TipOption.TIP_15 -> "15₹"
                }
                ButtonContent(text = buttonText, textColor)
            }
            Spacer(modifier = Modifier.width(4.dp))
        }

        var customTip by remember { mutableStateOf("") }
        // Text field for custom tip
        TextField(
            value = customTip,
            onValueChange = { customTip = it },
            label = { Text("₹") },
            maxLines = 1,
            modifier = Modifier
                .weight(1f)
        )

    }
}

@Composable
fun ButtonContent(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        softWrap = true
//        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
    )
}