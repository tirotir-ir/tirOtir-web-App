package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.QuizScoreRecord
import com.example.data.model.CodeBugPuzzle
import com.example.data.model.QuizData
import com.example.data.model.QuizQuestion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizzesScreen(
    quizScores: List<QuizScoreRecord>,
    onSaveQuizScore: (quizId: String, score: Int, total: Int) -> Unit
) {
    val context = LocalContext.current
    var activeSubTab by remember { mutableIntStateOf(0) } // 0: آزمون‌های ۴ گزینه‌ای, 1: چالش رفع باگ
    var selectedQuizKey by remember { mutableStateOf("html_css") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("quizzes_screen")
    ) {
        // Tab Header: آزمون‌های جامع | چالش باگ‌یابی
        SecondaryTabRow(
            selectedTabIndex = activeSubTab,
            modifier = Modifier.fillMaxWidth()
        ) {
            Tab(
                selected = activeSubTab == 0,
                onClick = { activeSubTab = 0 },
                text = { Text("آزمون‌های جامع ۴ گزینه‌ای", fontSize = 13.sp) },
                icon = { Icon(Icons.Default.Quiz, contentDescription = null, modifier = Modifier.size(16.dp)) }
            )
            Tab(
                selected = activeSubTab == 1,
                onClick = { activeSubTab = 1 },
                text = { Text("چالش باگ‌یابی کد (Bug Finder)", fontSize = 13.sp) },
                icon = { Icon(Icons.Default.BugReport, contentDescription = null, modifier = Modifier.size(16.dp)) }
            )
        }

        when (activeSubTab) {
            0 -> {
                QuizQuestionsView(
                    selectedCategory = selectedQuizKey,
                    onSelectCategory = { selectedQuizKey = it },
                    onCompleteQuiz = { score, total ->
                        onSaveQuizScore(selectedQuizKey, score, total)
                        Toast.makeText(context, "امتیاز شما ثبت شد: $score از $total", Toast.LENGTH_LONG).show()
                    }
                )
            }
            1 -> {
                BugPuzzlesView()
            }
        }
    }
}

@Composable
fun QuizQuestionsView(
    selectedCategory: String,
    onSelectCategory: (String) -> Unit,
    onCompleteQuiz: (score: Int, total: Int) -> Unit
) {
    val questions = QuizData.milestoneQuizzes[selectedCategory] ?: emptyList()
    val userAnswers = remember(selectedCategory) { mutableStateMapOf<String, Int>() }
    var isSubmitted by remember(selectedCategory) { mutableStateOf(false) }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedCategory == "html_css",
                    onClick = {
                        onSelectCategory("html_css")
                        isSubmitted = false
                    },
                    label = { Text("HTML5 و CSS3 مدرن") },
                    modifier = Modifier.testTag("chip_quiz_html_css")
                )
                FilterChip(
                    selected = selectedCategory == "js_dom",
                    onClick = {
                        onSelectCategory("js_dom")
                        isSubmitted = false
                    },
                    label = { Text("جاوااسکریپت و تعامل با DOM") },
                    modifier = Modifier.testTag("chip_quiz_js_dom")
                )
            }
        }

        itemsIndexed(questions) { index, q ->
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth().testTag("quiz_card_${q.id}")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "سوال ${index + 1}: ${q.questionFa}",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    q.optionsFa.forEachIndexed { optIndex, optText ->
                        val isSelected = userAnswers[q.id] == optIndex
                        val isCorrect = optIndex == q.correctIndex

                        val optionBgColor = when {
                            !isSubmitted -> if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                            isCorrect -> Color(0xFFD1FAE5) // Green
                            isSelected && !isCorrect -> Color(0xFFFEE2E2) // Red
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        }

                        val textColor = when {
                            !isSubmitted -> if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                            isCorrect -> Color(0xFF065F46)
                            isSelected && !isCorrect -> Color(0xFF991B1B)
                            else -> MaterialTheme.colorScheme.onSurfaceVariant
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = optionBgColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable(enabled = !isSubmitted) {
                                    userAnswers[q.id] = optIndex
                                }
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${optIndex + 1})",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = textColor
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = optText,
                                    fontSize = 13.sp,
                                    color = textColor,
                                    modifier = Modifier.weight(1f)
                                )
                                if (isSubmitted && isCorrect) {
                                    Icon(Icons.Default.Check, contentDescription = "درست", tint = Color(0xFF10B981))
                                } else if (isSubmitted && isSelected && !isCorrect) {
                                    Icon(Icons.Default.Close, contentDescription = "نادرست", tint = Color(0xFFEF4444))
                                }
                            }
                        }
                    }

                    if (isSubmitted) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "💡 تحلیل پاسخ آکادمی تیروتیر:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = q.explanationFa,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            if (!isSubmitted) {
                Button(
                    onClick = {
                        isSubmitted = true
                        var correctCount = 0
                        questions.forEach { q ->
                            if (userAnswers[q.id] == q.correctIndex) {
                                correctCount++
                            }
                        }
                        onCompleteQuiz(correctCount, questions.size)
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp).testTag("btn_submit_quiz"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("ثبت و تصحیح آزمون", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            } else {
                OutlinedButton(
                    onClick = {
                        userAnswers.clear()
                        isSubmitted = false
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("آزمون مجدد", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun BugPuzzlesView() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(QuizData.codeBugPuzzles) { puzzle ->
            BugPuzzleCard(puzzle = puzzle)
        }
    }
}

@Composable
fun BugPuzzleCard(puzzle: CodeBugPuzzle) {
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var showSolution by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.BugReport, contentDescription = null, tint = Color(0xFFEF4444), modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = puzzle.titleFa,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = puzzle.bugDescriptionFa,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Broken Code Box
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF0F172A),
                modifier = Modifier.fillMaxWidth()
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(10.dp)
                    ) {
                        Text(
                            text = puzzle.brokenCode,
                            color = Color(0xFFF43F5E),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Options
            puzzle.optionsFa.forEachIndexed { optIndex, optText ->
                val isSelected = selectedOption == optIndex
                val isCorrect = optIndex == puzzle.correctOptionIndex

                val bg = when {
                    !showSolution -> if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                    isCorrect -> Color(0xFFD1FAE5)
                    isSelected && !isCorrect -> Color(0xFFFEE2E2)
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = bg,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable(enabled = !showSolution) {
                            selectedOption = optIndex
                        }
                ) {
                    Text(
                        text = "${optIndex + 1}) $optText",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (!showSolution) {
                Button(
                    onClick = { showSolution = true },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = selectedOption != null
                ) {
                    Text("بررسی علت باگ و مشاهده راه حل")
                }
            } else {
                Column {
                    Text(
                        text = "کد اصلاح شده:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color(0xFF10B981)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF0F172A),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = puzzle.fixedCode,
                                    color = Color(0xFF10B981),
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 11.sp,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = puzzle.explanationFa,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
