package com.example.b4_w2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.b4_w2.ui.theme.B4_w2Theme
import java.util.Locale

data class Student(
    val name: String,
    val score: Double?
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            B4_w2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BangDiemLopScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

fun getXepLoai(score: Double?): String {
    if (score == null) return "Chưa có điểm"
    return if (score < 0 || score > 10) {
        "Không hợp lệ"
    } else if (score >= 8.0) {
        "Giỏi"
    } else if (score >= 6.5) {
        "Khá"
    } else if (score >= 5.0) {
        "Trung bình"
    } else {
        "Yếu"
    }
}

fun getTextColor(xepLoai: String): Color {
    return when (xepLoai) {
        "Giỏi" -> Color(0xFF166534)
        "Khá" -> Color(0xFF1D4ED8)
        "Trung bình" -> Color(0xFFB45309)
        "Yếu" -> Color(0xFFB91C1C)
        else -> Color(0xFF6B7280)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BangDiemLopScreen(modifier: Modifier = Modifier) {
    val studentList = listOf(
        Student("Nguyễn Văn An", 9.0),
        Student("Trần Thị Bình", 7.2),
        Student("Lê Minh Chi", null),
        Student("Phạm Quốc Dũng", 4.5),
        Student("Võ Thanh Em", 5.8)
    )

    var sumScore = 0.0
    var countWithScore = 0
    var gioiCount = 0
    var noScoreCount = 0
    var maxScore: Double? = null

    for (student in studentList) {
        if (student.score == null) {
            noScoreCount++
        } else {
            sumScore += student.score
            countWithScore++

            if (student.score >= 8.0 && student.score <= 10.0) {
                gioiCount++
            }

            if (maxScore == null || student.score > maxScore) {
                maxScore = student.score
            }
        }
    }

    val avgScore = if (countWithScore > 0) sumScore / countWithScore else 0.0

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
    ) {

        TopAppBar(
            title = {
                Text(
                    text = "Bảng điểm lớp",
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3A8A))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Điểm trung bình lớp",
                        color = Color(0xFF93C5FD),
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = String.format(Locale.US, "%.2f", avgScore),
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color(0xFF3B82F6).copy(alpha = 0.4f))
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Giỏi", color = Color(0xFF93C5FD), fontSize = 11.sp)
                            Text("$gioiCount", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("Chưa có điểm", color = Color(0xFF93C5FD), fontSize = 11.sp)
                            Text("$noScoreCount", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("Cao nhất", color = Color(0xFF93C5FD), fontSize = 11.sp)
                            Text(
                                text = maxScore?.let { String.format(Locale.US, "%.1f", it) } ?: "---",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Text(
                text = "DANH SÁCH - ${studentList.size} SINH VIÊN",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )

            for (student in studentList) {
                val xepLoai = getXepLoai(student.score)

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = student.name,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = xepLoai,
                                fontSize = 12.sp,
                                color = getTextColor(xepLoai)
                            )
                        }

                        Text(
                            text = student.score?.let { String.format(Locale.US, "%.1f", it) } ?: "---",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BangDiemLopPreview() {
    B4_w2Theme {
        BangDiemLopScreen()
    }
}