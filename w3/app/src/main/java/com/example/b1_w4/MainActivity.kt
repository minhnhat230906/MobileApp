package com.example.b1_w4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class AppScreen { ONBOARDING, LIST, DETAIL_TEXT, DETAIL_IMAGE, DETAIL_TEXTFIELD, DETAIL_ROW }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainAppNav()
            }
        }
    }
}

@Composable
fun MainAppNav() {
    var currentScreen by remember { mutableStateOf(AppScreen.ONBOARDING) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentScreen) {
            AppScreen.ONBOARDING -> OnboardingScreen(onNavigateToList = { currentScreen = AppScreen.LIST })
            AppScreen.LIST -> ComponentsListScreen(
                onNavigateToText = { currentScreen = AppScreen.DETAIL_TEXT },
                onNavigateToImage = { currentScreen = AppScreen.DETAIL_IMAGE },
                onNavigateToTextField = { currentScreen = AppScreen.DETAIL_TEXTFIELD },
                onNavigateToRow = { currentScreen = AppScreen.DETAIL_ROW }
            )
            AppScreen.DETAIL_TEXT -> TextDetailScreen(onBack = { currentScreen = AppScreen.LIST })
            AppScreen.DETAIL_IMAGE -> ImageDetailScreen(onBack = { currentScreen = AppScreen.LIST })
            AppScreen.DETAIL_TEXTFIELD -> TextFieldDetailScreen(onBack = { currentScreen = AppScreen.LIST })
            AppScreen.DETAIL_ROW -> RowLayoutDetailScreen(onBack = { currentScreen = AppScreen.LIST })
        }
    }
}

@Composable
fun OnboardingScreen(onNavigateToList: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF00C853)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "❖", fontSize = 40.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Jetpack Compose",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                fontSize = 13.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }

        Button(
            onClick = onNavigateToList,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00A0FF)),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(48.dp)
                .padding(bottom = 12.dp)
        ) {
            Text(text = "I'm ready", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsListScreen(
    onNavigateToText: () -> Unit,
    onNavigateToImage: () -> Unit,
    onNavigateToTextField: () -> Unit,
    onNavigateToRow: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("UI Components List", color = Color(0xFF00A0FF), fontWeight = FontWeight.Bold, fontSize = 17.sp) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SectionHeader(title = "Display")
            ComponentCard(title = "Text", subtitle = "Displays text", onClick = onNavigateToText)
            ComponentCard(title = "Image", subtitle = "Displays an image", onClick = onNavigateToImage)

            SectionHeader(title = "Input")
            ComponentCard(title = "TextField", subtitle = "Input field for text", onClick = onNavigateToTextField)
            ComponentCard(title = "PasswordField", subtitle = "Input field for passwords", onClick = {})

            SectionHeader(title = "Layout")
            ComponentCard(title = "Column", subtitle = "Arranges elements vertically", onClick = {})
            ComponentCard(title = "Row", subtitle = "Arranges elements horizontally", onClick = onNavigateToRow)

            // Thẻ màu đỏ "Tự tìm hiểu" ở cuối trang
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCDD2))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(text = "Tự tìm hiểu", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD32F2F))
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "Tìm ra tất cả các thành phần UI Cơ bản", fontSize = 12.sp, color = Color(0xFFC62828))
                }
            }
        }
        )
    }

    @Composable
    fun SectionHeader(title: String) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
        )
    }

    @Composable
    fun ComponentCard(title: String, subtitle: String, onClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F3FF))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(text = title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E88E5))
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TextDetailScreen(onBack: () -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Text Detail", color = Color(0xFF00A0FF), fontWeight = FontWeight.Bold, fontSize = 17.sp) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Text(text = "❮", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00A0FF))
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                val annotatedText = buildAnnotatedString {
                    append("The ")
                    withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough, color = Color.DarkGray)) {
                        append("quick ")
                    }
                    withStyle(SpanStyle(color = Color(0xFF8B4513), fontSize = 30.sp, fontWeight = FontWeight.Bold)) {
                        append("Brown\n")
                    }
                    withStyle(SpanStyle(fontFamily = FontFamily.Serif, fontSize = 24.sp)) {
                        append("fox ")
                    }
                    withStyle(SpanStyle(letterSpacing = 5.sp, fontWeight = FontWeight.Bold, fontSize = 22.sp)) {
                        append("jumps ")
                    }
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)) {
                        append("over\n")
                    }
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline, fontSize = 22.sp)) {
                        append("the ")
                    }
                    withStyle(SpanStyle(fontFamily = FontFamily.Cursive, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4A3728))) {
                        append("lazy ")
                    }
                    withStyle(SpanStyle(fontFamily = FontFamily.Monospace, fontSize = 24.sp, fontWeight = FontWeight.Bold)) {
                        append("dog.")
                    }
                }

                Text(
                    text = annotatedText,
                    fontSize = 22.sp,
                    lineHeight = 44.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ImageDetailScreen(onBack: () -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Images", color = Color(0xFF00A0FF), fontWeight = FontWeight.Bold, fontSize = 17.sp) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Text(text = "❮", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00A0FF))
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                    modifier = Modifier.fillMaxWidth().height(180.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "🏛️ UTH Campus Image", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                    }
                }
                Text(
                    text = "https://giaothongvantaihcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFECEFF1)),
                    modifier = Modifier.fillMaxWidth().height(180.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "🏢 In App Resource Image", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF37474F))
                    }
                }
                Text(
                    text = "In app",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TextFieldDetailScreen(onBack: () -> Unit) {
        var textInput by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("TextField", color = Color(0xFF00A0FF), fontWeight = FontWeight.Bold, fontSize = 17.sp) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Text(text = "❮", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00A0FF))
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = textInput,
                    onValueChange = { textInput = it },
                    placeholder = { Text("Thông tin nhập", color = Color.Gray) },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = if (textInput.isEmpty()) "Tự động cập nhật dữ liệu theo textfield" else textInput,
                    fontSize = 14.sp,
                    color = Color(0xFFD81B60),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RowLayoutDetailScreen(onBack: () -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Row Layout", color = Color(0xFF00A0FF), fontWeight = FontWeight.Bold, fontSize = 17.sp) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Text(text = "❮", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00A0FF))
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(4) { rowIndex ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        repeat(3) { colIndex ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(60.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if ((rowIndex + colIndex) % 2 == 0) Color(0xFF42A5F5) else Color(0xFF90CAF9)
                                    )
                            )
                        }
                    }
                }
            }
            )
        }