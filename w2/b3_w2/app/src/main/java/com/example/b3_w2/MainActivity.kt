package com.example.b3_w2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.b3_w2.ui.theme.B3_w2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            B3_w2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    XepLoaiHocLucScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

fun getXepLoai(diem: Double): String {
    return if (diem < 0 || diem > 10) {
        "Không hợp lệ"
    } else if (diem >= 8.0) {
        "Giỏi"
    } else if (diem >= 6.5) {
        "Khá"
    } else if (diem >= 5.0) {
        "Trung bình"
    } else {
        "Yếu"
    }
}

fun getTextColor(xepLoai: String): Color {
    return if (xepLoai == "Giỏi") {
        Color(0xFF15803D)
    } else if (xepLoai == "Khá") {
        Color(0xFF1D4ED8)
    } else if (xepLoai == "Trung bình") {
        Color(0xFFB45309)
    } else if (xepLoai == "Yếu") {
        Color(0xFFB91C1C)
    } else {
        Color(0xFF6B7280)
    }
}


fun getBgColor(xepLoai: String): Color {
    return if (xepLoai == "Giỏi") {
        Color(0xFFDCFCE7)
    } else if (xepLoai == "Khá") {
        Color(0xFFDBEAFE)
    } else if (xepLoai == "Trung bình") {
        Color(0xFFFEF3C7)
    } else if (xepLoai == "Yếu") {
        Color(0xFFFEE2E2)
    } else {
        Color(0xFFE5E7EB)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XepLoaiHocLucScreen(modifier: Modifier = Modifier) {
    val diemChinh = 8.5
    val xepLoaiChinh = getXepLoai(diemChinh)
    val dsDiemKiemTra = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Xếp loại học lực",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF1E3A8A)
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Điểm trung bình",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = diemChinh.toString(),
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = getBgColor(xepLoaiChinh),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = xepLoaiChinh,
                            color = getTextColor(xepLoaiChinh),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Text(
                text = "KIỂM TRA THÊM",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp, bottom = 2.dp)
            )

            dsDiemKiemTra.forEach { diem ->
                val xepLoai = getXepLoai(diem)
                val textDiem = if (diem % 1.0 == 0.0) diem.toInt().toString() else diem.toString()

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Điểm $textDiem",
                            fontSize = 15.sp,
                            color = Color.Black
                        )

                        Box(
                            modifier = Modifier
                                .background(
                                    color = getBgColor(xepLoai),
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = xepLoai,
                                color = getTextColor(xepLoai),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun XepLoaiHocLucPreview() {
    B3_w2Theme {
        XepLoaiHocLucScreen()
    }
}