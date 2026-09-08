package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.UserProfile
import com.example.data.model.AcademyInfo

@Composable
fun CertificateScreen(
    userProfile: UserProfile?,
    completedLessonCount: Int,
    totalLessonCount: Int,
    onUpdateStudentName: (String) -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var showEditNameDialog by remember { mutableStateOf(false) }
    var nameInput by remember { mutableStateOf(userProfile?.studentName ?: "مهندس محترم") }

    val studentName = userProfile?.studentName?.ifBlank { "مهندس فرانت‌اند" } ?: "مهندس فرانت‌اند"
    val certificateId = "TIR-WEB-${(userProfile?.id ?: 1) + 1024}-${(completedLessonCount * 13) % 997 + 100}"
    val completionPercent = if (totalLessonCount > 0) (completedLessonCount * 100) / totalLessonCount else 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("certificate_screen")
    ) {
        // Certificate Container Card (Ornate Border with Parchment/Golden Accents)
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFBF7)),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color(0xFFD97706), RoundedCornerShape(16.dp))
                .padding(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFFDE68A), RoundedCornerShape(12.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Academy Header Badge
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFEF3C7)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = "آرم آکادمی",
                        tint = Color(0xFFB45309),
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = AcademyInfo.ACADEMY_NAME_FA,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = AcademyInfo.INSTITUTE_LEGAL_NAME,
                    fontSize = 11.sp,
                    color = Color(0xFF64748B),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    color = Color(0xFFFEF3C7),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "گواهی‌نامه رسمی اتمام دوره مهارت‌های وب",
                        color = Color(0xFF92400E),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "بدین‌وسیله گواهی می‌شود دانش‌پژوه گرامی:",
                    fontSize = 12.sp,
                    color = Color(0xFF475569)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = studentName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    IconButton(
                        onClick = {
                            nameInput = studentName
                            showEditNameDialog = true
                        },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "ویرایش نام", tint = Color(0xFF0284C7), modifier = Modifier.size(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "دوره جامع «توسعه‌دهنده وب و فرانت‌اند مدرن» شامل سرفصل‌های استاندارد HTML5، تکنیک‌های پیشرفته CSS3 (Flexbox/Grid)، زبان جاوااسکریپت و ساخت پروژه‌های تعاملی (بر اساس مراجع آموزشی hcj00 تا hcj79+) را با موفقیت سپری نموده و شایسته دریافت این گواهی‌نامه می‌باشد.",
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    color = Color(0xFF334155),
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Metadata Footer with QR and Stamps
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // QR preview placeholder
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.QrCode2, contentDescription = "QR استعلام", modifier = Modifier.size(46.dp), tint = Color(0xFF0F172A))
                            Text(text = "استعلام اصالت", fontSize = 8.sp, color = Color(0xFF64748B))
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Surface(
                            color = Color(0xFFE0F2FE),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "کد رهگیری: $certificateId",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0369A1),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "پیشرفت دوره: $completionPercent%",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (completionPercent >= 80) Color(0xFF16A34A) else Color(0xFFD97706)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "مهر و امضای الکترونیک تیروتیر ✓",
                            fontSize = 10.sp,
                            color = Color(0xFF16A34A)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    val shareText = "گواهی‌نامه رسمی توسعه وب تیروتیر\nدانش‌پژوه: $studentName\nکد رهگیری: $certificateId\nموسسه اندیشه پردازان آنزان (تاسیس ۱۳۷۹)"
                    clipboardManager.setText(AnnotatedString(shareText))
                    Toast.makeText(context, "اطلاعات گواهینامه در حافظه کپی شد!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("اشتراک و کپی مدرک")
            }

            OutlinedButton(
                onClick = {
                    nameInput = studentName
                    showEditNameDialog = true
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("تغییر نام مدرک")
            }
        }
    }

    // Edit Name Dialog
    if (showEditNameDialog) {
        AlertDialog(
            onDismissRequest = { showEditNameDialog = false },
            title = { Text("ویرایش نام صاحب گواهینامه") },
            text = {
                Column {
                    Text("نام و نام‌خانوادگی خود را جهت درج روی گواهی رسمی وارد فرمایید:")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = nameInput,
                        onValueChange = { nameInput = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onUpdateStudentName(nameInput)
                        showEditNameDialog = false
                        Toast.makeText(context, "نام به روز رسانی شد.", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("تایید")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditNameDialog = false }) {
                    Text("انصراف")
                }
            }
        )
    }
}
