@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.androidtechguru.tigihr

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
                        Scaffold(
                            // AppBar content
                            topBar = {
                                TopAppBar(title = {
                                    Text(
                                        text = "Checkout",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.fillMaxWidth(),
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
                            // Screen content
                            content = {
                                LazyColumn(
                                    modifier = Modifier
                                        .padding(it)
                                        .fillMaxSize()
                                        .background(Color.White)
                                ) {
                                    item {
                                        CheckoutScreen()
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CheckoutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "When", style = HeaderTextStyle)
        Spacer(modifier = Modifier.height(20.dp))
        TextWithIcon(
            icon = painterResource(id = R.drawable.rocket_launch_64),
            text = "As Soon As Possible"
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextWithIconCheckbox(
            icon = Icons.Default.Person,
            text = "Contact less delivery (only for card payment)"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider()

        DeliveryDetailsUI()
        Divider()

        OffersUI()
        Divider()

        TipUI()
        Divider()

        InvoiceUI()

        SignInButton()
    }
}

@Composable
fun TextWithIcon(icon: Painter, text: String) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = icon,
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
fun TextWithIconCheckbox(icon: ImageVector, text: String) {
    var checkedState by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            tint = AppColor,
            contentDescription = null,
            modifier = Modifier
                .width(24.dp)
        )
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 12.dp),
        )
        Spacer(modifier = Modifier.width(24.dp))
        Checkbox(
            checked = checkedState,
            onCheckedChange = { checkedState = it },
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}

@Composable
fun DeliveryDetailsUI() {
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
fun DeliveryDetailsEditTextUI() {
    var nameEt by remember { mutableStateOf("") }
    var codeEt by remember { mutableStateOf("") }
    var mobileNoEt by remember { mutableStateOf("") }
    var deliveryAddressEt by remember { mutableStateOf("") }
    var addDeliveryNoteEt by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val maxMobileNo = 10
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        EditTextField("Name", "", nameEt) {
            nameEt = it
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = codeEt,
                onValueChange = {
                    codeEt = it
                    showError = false
                },
                label = { Text("Code", color = Color.Gray, maxLines = 1) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                placeholder = { Text("+91", color = Color.Gray, maxLines = 1) },
                isError = showError,
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                modifier = Modifier
                    .width(72.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = mobileNoEt,
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                onValueChange = {
                    mobileNoEt = it
                    if (it.length <= maxMobileNo) mobileNoEt = it
                },
                label = { Text("Mobile No.", color = Color.Gray, maxLines = 1) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        EditTextField(
            "Delivery Address",
            "2nd Floor Aditya Complex Jalaram-2, University Rd...",
            deliveryAddressEt
        ) {
            deliveryAddressEt = it
        }
        Spacer(modifier = Modifier.height(12.dp))

        EditTextField("Add Delivery Note", "", addDeliveryNoteEt) {
            addDeliveryNoteEt = it
        }
    }
}

@Composable
fun EditTextField(
    label: String,
    hint: String = "",
    textValue: String = "",
    onValueChanged: (String) -> Unit
) {
    TextField(
        value = textValue,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Gray) },
        placeholder = { Text(text = hint, color = Color.Gray, maxLines = 1) },
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
    )
}

@Composable
fun OffersUI() {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Offers", style = HeaderTextStyle,
            modifier = Modifier.weight(1f)
        )

        Text(text = "View Offer",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
            color = AppColor,
            modifier = Modifier.clickable { showToast(context, "View Offer Banner...") })
    }
    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var promoCodeEt by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }


        TextField(
            value = "",
            onValueChange = {
                promoCodeEt = it
                isError = false
            },
            label = { Text("Promo Code") },
            placeholder = { Text("") },
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        text = "Field cannot be empty",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            singleLine = true,
            modifier = Modifier
                .background(color = Color.White)
                .wrapContentWidth()
                .weight(1f)
        )
        Button(
            onClick = {
                if (promoCodeEt.isEmpty()) {
                    isError = true // Show error if text is empty on button click
                } else {
                }
            },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .wrapContentWidth()
        ) {
            Text(text = "Apply", fontSize = 18.sp)
        }


    }
}

@Composable
fun TipUI() {
    Text(text = "Tip", style = HeaderTextStyle)
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Would you like to offer a Tip?", color = Color.Gray)
    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TipSelectionButtons()
    }
}

@Composable
fun InvoiceUI() {
    Text(text = "Invoice", style = HeaderTextStyle)
    Spacer(modifier = Modifier.height(16.dp))
    var servicePrice = 195.40
    var totalServicePrice = 195.40
    var itemPrice = 14780.00
    var totalItemPrice = 14780.00
    var total = 14975.40

    Column(modifier = Modifier.padding(start = 8.dp)) {
        InvoiceText(label = "Service Price", value = servicePrice)
        InvoiceText(label = "TOTAL SERVICE PRICE", value = totalServicePrice, isBoldStyle = true)
        InvoiceText(label = "Item Price", value = itemPrice)
        InvoiceText(label = "TOTAL ITEM PRICE", value = totalItemPrice, isBoldStyle = true)

        Spacer(modifier = Modifier.height(26.dp))
        Text(text = "Total", fontSize = 20.sp, color = Color.Gray)
        Text(text = "₹ $total", fontSize = 32.sp, style = HeaderTextStyle)

    }
}

@Composable
fun InvoiceText(label: String, value: Double, isBoldStyle: Boolean = false) {
    val style = if (isBoldStyle) HeaderTextStyle else TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    )

    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, modifier = Modifier.weight(1f), style = style)
        Text(text = "₹ $value", style = style)
    }
}

@Composable
fun SignInButton() {
    Spacer(modifier = Modifier.height(24.dp))

    Button(
        onClick = { },
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Sign In", textAlign = TextAlign.Center, fontSize = 24.sp)
    }
}


fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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