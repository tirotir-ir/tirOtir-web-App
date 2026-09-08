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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.SavedSnippet
import com.example.data.model.CurriculumRepository
import com.example.ui.components.WebSandboxView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaygroundScreen(
    currentCode: String,
    onCodeChange: (String) -> Unit,
    consoleLogs: List<String>,
    onAddConsoleLog: (String) -> Unit,
    onClearLogs: () -> Unit,
    savedSnippets: List<SavedSnippet>,
    onSaveSnippet: (title: String, desc: String) -> Unit,
    onDeleteSnippet: (Long) -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var activeTab by remember { mutableIntStateOf(1) } // 0: Editor, 1: Live Preview, 2: Split View
    var showPresetSheet by remember { mutableStateOf(false) }
    var showSavedSheet by remember { mutableStateOf(false) }
    var showSaveDialog by remember { mutableStateOf(false) }
    var showConsoleDrawer by remember { mutableStateOf(false) }
    var snippetTitleInput by remember { mutableStateOf("") }

    val quickTags = listOf(
        "<div></div>",
        "<style></style>",
        "<script></script>",
        "<button></button>",
        "<h1></h1>",
        "<p></p>",
        "class=\"\"",
        "id=\"\"",
        "console.log();",
        "display: flex;",
        "border-radius: 8px;"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("playground_screen")
    ) {
        // Mode & Control Top Bar
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // View Mode Tabs: کد منبع | پیش‌نمایش | دوگانه
                    SecondaryTabRow(
                        selectedTabIndex = activeTab,
                        modifier = Modifier.width(260.dp)
                    ) {
                        Tab(
                            selected = activeTab == 0,
                            onClick = { activeTab = 0 },
                            text = { Text("کد منبع", fontSize = 12.sp) }
                        )
                        Tab(
                            selected = activeTab == 1,
                            onClick = { activeTab = 1 },
                            text = { Text("پیش‌نمایش", fontSize = 12.sp) }
                        )
                        Tab(
                            selected = activeTab == 2,
                            onClick = { activeTab = 2 },
                            text = { Text("دوگانه", fontSize = 12.sp) }
                        )
                    }

                    // Action Buttons
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Presets
                        IconButton(
                            onClick = { showPresetSheet = true },
                            modifier = Modifier.size(36.dp).testTag("btn_presets")
                        ) {
                            Icon(Icons.Default.Download, contentDescription = "نمونه کدهای آماده", tint = MaterialTheme.colorScheme.primary)
                        }

                        // Saved
                        IconButton(
                            onClick = { showSavedSheet = true },
                            modifier = Modifier.size(36.dp).testTag("btn_saved_snippets")
                        ) {
                            Icon(Icons.Default.Folder, contentDescription = "کدهای ذخیره شده", tint = MaterialTheme.colorScheme.secondary)
                        }

                        // Save
                        IconButton(
                            onClick = {
                                snippetTitleInput = "کد من #${savedSnippets.size + 1}"
                                showSaveDialog = true
                            },
                            modifier = Modifier.size(36.dp).testTag("btn_save_code")
                        ) {
                            Icon(Icons.Default.Save, contentDescription = "ذخیره در دیتابیس", tint = Color(0xFF10B981))
                        }

                        // Copy
                        IconButton(
                            onClick = {
                                clipboardManager.setText(AnnotatedString(currentCode))
                                Toast.makeText(context, "کد در حافظه کپی شد!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.size(36.dp).testTag("btn_copy_code")
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "کپی کردن کد", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }

                        // Console Logs toggle
                        IconButton(
                            onClick = { showConsoleDrawer = !showConsoleDrawer },
                            modifier = Modifier.size(36.dp).testTag("btn_console_toggle")
                        ) {
                            Icon(
                                Icons.Default.Terminal,
                                contentDescription = "کنسول لاگ",
                                tint = if (showConsoleDrawer) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Quick-Tags Insertion Bar (Horizontal Scroll)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    contentPadding = PaddingValues(vertical = 4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(quickTags) { tag ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.clickable {
                                onCodeChange(currentCode + "\n" + tag)
                            }
                        ) {
                            Text(
                                text = tag,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        // Main Editor / Preview Area
        Box(modifier = Modifier.weight(1f)) {
            when (activeTab) {
                0 -> {
                    // Full Code Editor
                    CodeEditorView(
                        code = currentCode,
                        onCodeChange = onCodeChange
                    )
                }
                1 -> {
                    // Full Live Preview
                    WebSandboxView(
                        htmlContent = currentCode,
                        onConsoleLog = onAddConsoleLog
                    )
                }
                2 -> {
                    // Split View (Top: Editor, Bottom: Live Sandbox)
                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(modifier = Modifier.weight(1f)) {
                            CodeEditorView(
                                code = currentCode,
                                onCodeChange = onCodeChange
                            )
                        }
                        HorizontalDivider(thickness = 2.dp, color = MaterialTheme.colorScheme.primary)
                        Box(modifier = Modifier.weight(1f)) {
                            WebSandboxView(
                                htmlContent = currentCode,
                                onConsoleLog = onAddConsoleLog
                            )
                        }
                    }
                }
            }
        }

        // Console Drawer (Bottom expansion)
        if (showConsoleDrawer) {
            Surface(
                color = Color(0xFF0F172A),
                modifier = Modifier.fillMaxWidth().height(140.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "💻 کنسول مرورگر و خروجی لاگ‌ها",
                            color = Color(0xFF38BDF8),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row {
                            TextButton(onClick = onClearLogs) {
                                Text("پاکسازی", color = Color(0xFFF43F5E), fontSize = 11.sp)
                            }
                            TextButton(onClick = { showConsoleDrawer = false }) {
                                Text("بستن", color = Color.White, fontSize = 11.sp)
                            }
                        }
                    }
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(consoleLogs) { log ->
                                Text(
                                    text = "› $log",
                                    color = Color(0xFF94A3B8),
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 11.sp,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Save Snippet Dialog
    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = { Text("ذخیره اسکریپت در دیتابیس لوکال") },
            text = {
                Column {
                    Text("یک عنوان برای دسترسی آسان در آینده انتخاب فرمایید:")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = snippetTitleInput,
                        onValueChange = { snippetTitleInput = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSaveSnippet(snippetTitleInput, "کد سفارشی کاربر در تیروتیر وب")
                        showSaveDialog = false
                        Toast.makeText(context, "کد با موفقیت ذخیره شد!", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("ذخیره")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveDialog = false }) {
                    Text("انصراف")
                }
            }
        )
    }

    // Preset Snippets Importer BottomSheet
    if (showPresetSheet) {
        ModalBottomSheet(
            onDismissRequest = { showPresetSheet = false },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "📥 بارگذاری نمونه کدهای مرجع دوره تیروتیر",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "انتخاب مستقیم از بیش از ۸۰ نمونه کد تدریس شده (hcj00 تا hcj79):",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn(
                    modifier = Modifier.height(350.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(CurriculumRepository.lessons) { l ->
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onCodeChange(l.codeSnippet)
                                    showPresetSheet = false
                                    Toast.makeText(context, "کد ${l.prefix} در سندباکس بارگذاری شد", Toast.LENGTH_SHORT).show()
                                }
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = "${l.prefix}: ${l.titleFa}", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text(text = l.titleEn, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                Icon(Icons.Default.Download, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }
        }
    }

    // Saved Snippets Manager BottomSheet
    if (showSavedSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSavedSheet = false },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "📁 اسکریپت‌های ذخیره شده در حافظه دیتابیس Room",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (savedSnippets.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("هنوز کدی را ذخیره نکرده‌اید. با فشردن آیکون دیسکت در نوار بالا اسکریپت خود را ذخیره کنید.")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.height(350.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(savedSnippets) { snippet ->
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable {
                                                onCodeChange(snippet.codeContent)
                                                showSavedSheet = false
                                                Toast.makeText(context, "کد '${snippet.title}' بارگذاری شد", Toast.LENGTH_SHORT).show()
                                            }
                                    ) {
                                        Text(text = snippet.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                        Text(text = "طول کد: ${snippet.codeContent.length} کاراکتر", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                    IconButton(onClick = { onDeleteSnippet(snippet.id) }) {
                                        Icon(Icons.Default.Delete, contentDescription = "حذف", tint = Color(0xFFEF4444))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CodeEditorView(
    code: String,
    onCodeChange: (String) -> Unit
) {
    Surface(
        color = Color(0xFF0F172A),
        modifier = Modifier.fillMaxSize()
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            OutlinedTextField(
                value = code,
                onValueChange = onCodeChange,
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = Color(0xFF38BDF8),
                    textAlign = TextAlign.Left
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .testTag("code_editor_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color(0xFF38BDF8)
                )
            )
        }
    }
}
