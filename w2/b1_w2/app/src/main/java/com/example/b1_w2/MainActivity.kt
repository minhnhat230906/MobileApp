package com.example.b1_w2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ThucHanh03Screen()
                }
            }
        }
    }
}

@Composable
fun ThucHanh03Screen() {
    var num1Text by remember { mutableStateOf("") }
    var num2Text by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    fun calculate(operator: String) {
        val n1 = num1Text.toDoubleOrNull()
        val n2 = num2Text.toDoubleOrNull()

        if (n1 == null || n2 == null) {
            resultText = "Vui lòng nhập số hợp lệ"
            return
        }

        val res = when (operator) {
            "+" -> n1 + n2
            "-" -> n1 - n2
            "*" -> n1 * n2
            "/" -> if (n2 != 0.0) n1 / n2 else null
            else -> null
        }

        resultText = when {
            res == null -> "Không thể chia cho 0"
            res % 1.0 == 0.0 -> res.toLong().toString()
            else -> res.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Thực hành 03",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = num1Text,
            onValueChange = { num1Text = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CalcButton(symbol = "+", color = Color(0xFFEF5350)) { calculate("+") }
            CalcButton(symbol = "-", color = Color(0xFFFFA726)) { calculate("-") }
            CalcButton(symbol = "*", color = Color(0xFF7E57C2)) { calculate("*") }
            CalcButton(symbol = "/", color = Color(0xFF424242)) { calculate("/") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = num2Text,
            onValueChange = { num2Text = it },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = if (resultText.isEmpty()) "Kết quả:" else "Kết quả: $resultText",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CalcButton(
    symbol: String,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.size(60.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = symbol,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}