@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.androidtechguru.tigihr

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidtechguru.tigihr.ui.theme.AppColor
import com.androidtechguru.tigihr.ui.theme.AppColors
import com.androidtechguru.tigihr.ui.theme.BillingAppBar
import com.androidtechguru.tigihr.ui.theme.Divider
import com.androidtechguru.tigihr.ui.theme.HeaderTextStyle
import com.androidtechguru.tigihr.ui.theme.TigiHRBillingTaskTheme
import com.androidtechguru.tigihr.ui.theme.TipSelectionButtons

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TigiHRBillingTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MaterialTheme(colorScheme = AppColors) {
//                        BillingScreen()
                        LazyColumn(){
                            item {
                                CheckoutScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CheckoutScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(text = "When", style = HeaderTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        TextWithIcon(icon = Icons.Default.Send, text = "As Soon As Possible")
        Spacer(modifier = Modifier.height(16.dp))
        TextWithIconCheckbox(icon = Icons.Default.Person, text = "Contact less delivery (only for card payment)")
        Spacer(modifier = Modifier.height(16.dp))
        Divider()

        DeliveryDetailsUI()
        Divider()

        OffersUI()
        Divider()

        TipUI()
        Divider()

        InvoiceUI()
        Divider()
    }
}

@Composable
fun TextWithIcon(icon: ImageVector, text: String) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            tint = AppColor,
            contentDescription = null, // Add content description as needed
            modifier = Modifier
                .width(24.dp) // Set the width of the icon as needed
        )
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = AppColor,
            modifier = Modifier.padding(start = 8.dp), // Add padding between icon and text
        )
    }
}

@Composable
fun TextWithIconCheckbox(icon: ImageVector, text: String,
                 checked: Boolean = false,
                 onCheckedChange: (Boolean) -> Unit = {}) {
    var checkedState by remember { mutableStateOf(false) }

    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            tint = AppColor,
            contentDescription = null, // Add content description as needed
            modifier = Modifier
                .width(24.dp) // Set the width of the icon as needed
        )
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp), // Add padding between icon and text
            // Add your preferred text style here
        )
        Spacer(modifier = Modifier.width(12.dp))
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(end = 8.dp) // Add padding between checkbox and icon
        )
    }
}

@Composable
fun DeliveryDetailsUI(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Delivery Details", style = HeaderTextStyle,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { },
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            ) {
                Text(text = "Edit")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        DeliveryDetailsEditTextUI()
    }
}

@Composable
fun DeliveryDetailsEditTextUI(){
    var nameEt by remember { mutableStateOf("") }
    var codeEt by remember { mutableStateOf("") }
    var mobileNoEt by remember { mutableStateOf("") }
    var deliveryAddressEt by remember { mutableStateOf("") }
    var addDeliveryNoteEt by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            EditTextField("Name", "", nameEt) {
                nameEt = it
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = codeEt,
                    onValueChange = { codeEt = it },
                    label = { Text("Code") },
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .wrapContentWidth()
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = mobileNoEt,
                    onValueChange = { mobileNoEt = it },
                    label = { Text("Mobile No.") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            EditTextField("Delivery Address", "2nd Floor Aditya Complex Jalaram-2, University Rd...", deliveryAddressEt) {
                deliveryAddressEt = it
            }
            Spacer(modifier = Modifier.height(12.dp))

            EditTextField("Add Delivery Note", "", addDeliveryNoteEt) {
                addDeliveryNoteEt = it
            }
        }
}

@Composable
fun EditTextField(label: String, hint: String="", textValue: String="", onValueChanged: (String) -> Unit) {
        TextField(
            value = textValue,
            onValueChange = onValueChanged,
            label= { Text(label)},
            placeholder = { Text(hint) },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
        )
}

@Composable
fun OffersUI(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Offers", style = HeaderTextStyle,
            modifier = Modifier.weight(1f))
        Text(text = "View Offer",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = AppColor)
    }
    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        var promoCodeEt by remember { mutableStateOf("") }

        TextField(
            value = "",
            onValueChange = { promoCodeEt },
            label= { Text("Promo Code")},
            placeholder = { Text("") },
            singleLine = true,
            modifier = Modifier
                .background(color = Color.White)
                .wrapContentWidth()
                .weight(1f)
        )
        Button(
            onClick = { },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .wrapContentWidth()
        ) {
            Text(text = "Apply")
        }
    }
}

@Composable
fun TipUI() {
    Text(text = "Tip", style = HeaderTextStyle)
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = "Would you like to offer a Tip?", color = Color.Gray)
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TipSelectionButtons()


    }
}

@Composable
fun InvoiceUI(){
    Text(text = "Invoice", style = HeaderTextStyle)
    Spacer(modifier = Modifier.height(16.dp))

    Column(modifier = Modifier.padding(start = 8.dp)) {
        InvoiceText(label = "Service Price", value = "195.40")
        InvoiceText(label = "TOTAL SERVICE PRICE", value = "",isBoldStyle = true )
        InvoiceText(label = "Item Price", value = "14780.00")
        InvoiceText(label = "TOTAL ITEM PRICE", value = "",isBoldStyle = true)

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Total", fontSize = 20.sp)
        Text(text = "₹ 14975.40", fontSize = 32.sp, style = HeaderTextStyle)

    }
}

@Composable
fun InvoiceText(label: String, value: String, isBoldStyle: Boolean= false){
    val style = if(isBoldStyle) HeaderTextStyle else TextStyle(fontWeight = FontWeight.Medium, fontSize = 18.sp)

    Spacer(modifier = Modifier.height(8.dp))
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Text(text = label, modifier = Modifier.weight(1f), style = style)
        Text(text = "₹ $value", style=style)
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillingScreen(onBackPressed: () -> Unit={}) {
    val billingItems = listOf(
        "Item 1" to 20,
        "Item 2" to 30,
        "Item 3" to 15,
        "Item 4" to 25
    )
    Column {
        Scaffold(
            topBar = {
                BillingAppBar(title = "Checkout", onBackPressed = onBackPressed)
            }){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
//                    item {
//                        Column(modifier = Modifier
//                            .fillMaxSize()
//                            .background(Color.White)
//                            .padding(12.dp)) {
//                            Text(text = "Invoice")
//                        }
//                    }
                    items(billingItems) { (itemName, itemPrice) ->
                        BillingItemRow(itemName, itemPrice)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
    }
}

@Composable
fun BillingItemRow(itemName: String, itemPrice: Int) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = itemName)
            Text(text = "₹ $itemPrice")
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TigiHRBillingTaskTheme {
        Greeting("TigiHR")
    }
}