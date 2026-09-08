package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.AppRepository
import com.example.data.local.LessonProgress
import com.example.data.local.QuizScoreRecord
import com.example.data.local.SavedSnippet
import com.example.data.local.UserProfile
import com.example.data.model.CurriculumRepository
import com.example.data.model.Lesson
import com.example.data.model.ShowcaseProjects
import com.example.data.model.WebProject
import com.example.ui.theme.AppThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen(val titleFa: String, val icon: String) {
    HOME("خانه", "home"),
    CURRICULUM("سرفصل‌ها", "book"),
    PLAYGROUND("سندباکس زنده", "code"),
    PROJECTS("پروژه‌ها", "rocket"),
    QUIZZES("آزمون و باگ", "quiz"),
    CERTIFICATE("گواهینامه", "award"),
    ABOUT("درباره تیروتیر", "info")
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = AppRepository(db)
    }

    // Theme Mode
    private val _themeMode = MutableStateFlow(AppThemeMode.DARK)
    val themeMode: StateFlow<AppThemeMode> = _themeMode.asStateFlow()

    fun setThemeMode(mode: AppThemeMode) {
        _themeMode.value = mode
    }

    fun cycleThemeMode() {
        _themeMode.value = when (_themeMode.value) {
            AppThemeMode.DARK -> AppThemeMode.LIGHT
            AppThemeMode.LIGHT -> AppThemeMode.SEPIA
            AppThemeMode.SEPIA -> AppThemeMode.DARK
        }
    }

    // Navigation Screen
    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    // Selected Module in Curriculum
    private val _selectedModuleId = MutableStateFlow(1)
    val selectedModuleId: StateFlow<Int> = _selectedModuleId.asStateFlow()

    fun selectModule(id: Int) {
        _selectedModuleId.value = id
    }

    // Selected Lesson Detail Dialog
    private val _selectedLesson = MutableStateFlow<Lesson?>(null)
    val selectedLesson: StateFlow<Lesson?> = _selectedLesson.asStateFlow()

    fun viewLessonDetail(lesson: Lesson?) {
        _selectedLesson.value = lesson
    }

    // Selected Project in Showcase
    private val _selectedProject = MutableStateFlow<WebProject>(ShowcaseProjects.projects.first())
    val selectedProject: StateFlow<WebProject> = _selectedProject.asStateFlow()

    fun selectProject(project: WebProject) {
        _selectedProject.value = project
    }

    // Live Sandbox State
    private val _sandboxCode = MutableStateFlow(
        """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>تست سندباکس تیروتیر</title>
    <style>
        body {
            font-family: Tahoma, sans-serif;
            background: #0f172a;
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 90vh;
            margin: 0;
            text-align: center;
        }
        h1 { color: #38bdf8; font-size: 24px; }
        p { color: #94a3b8; font-size: 14px; max-width: 320px; line-height: 1.6; }
        button {
            background: #0284c7;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            font-size: 14px;
            cursor: pointer;
            font-weight: bold;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <h1>🚀 سندباکس زنده تیروتیر وب</h1>
    <p>کدهای HTML، CSS و JS خود را در برگه کد ویرایش کنید و نتیجه را بلادرنگ اینجا مشاهده فرمایید!</p>
    <button onclick="testLog()">تست کنسول و پیام</button>
    <script>
        function testLog() {
            console.log("خوش‌آمدید به محیط اجرای وب آکادمی هوش مصنوعی تیروتیر!");
            alert("اسکریپت با موفقیت در محیط ایزوله اجرا شد!");
        }
    </script>
</body>
</html>"""
    )
    val sandboxCode: StateFlow<String> = _sandboxCode.asStateFlow()

    fun updateSandboxCode(newCode: String) {
        _sandboxCode.value = newCode
    }

    fun loadSnippetIntoSandbox(code: String) {
        _sandboxCode.value = code
        _currentScreen.value = AppScreen.PLAYGROUND
    }

    // Console logs interception
    private val _consoleLogs = MutableStateFlow<List<String>>(listOf("کنسول آماده دریافت پیام‌های console.log است."))
    val consoleLogs: StateFlow<List<String>> = _consoleLogs.asStateFlow()

    fun addConsoleLog(message: String) {
        val current = _consoleLogs.value.toMutableList()
        current.add("[${System.currentTimeMillis() % 100000}] $message")
        if (current.size > 50) current.removeAt(0)
        _consoleLogs.value = current
    }

    fun clearConsoleLogs() {
        _consoleLogs.value = emptyList()
    }

    // Room Database Observables
    val savedSnippets: StateFlow<List<SavedSnippet>> = repository.allSnippets
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val lessonProgress: StateFlow<List<LessonProgress>> = repository.allProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val quizScores: StateFlow<List<QuizScoreRecord>> = repository.allQuizScores
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userProfile: StateFlow<UserProfile?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserProfile())

    fun isLessonCompleted(lessonId: String): Boolean {
        return lessonProgress.value.any { it.lessonId == lessonId && it.isCompleted }
    }

    fun toggleLessonProgress(lessonId: String) {
        viewModelScope.launch {
            val completed = isLessonCompleted(lessonId)
            repository.toggleLessonProgress(lessonId, completed)
        }
    }

    fun saveCurrentSnippet(title: String, description: String = "") {
        viewModelScope.launch {
            repository.saveSnippet(
                SavedSnippet(
                    title = title.ifBlank { "اسکریپت تیروتیر #" + (savedSnippets.value.size + 1) },
                    codeContent = _sandboxCode.value,
                    description = description
                )
            )
        }
    }

    fun deleteSnippet(id: Long) {
        viewModelScope.launch {
            repository.deleteSnippet(id)
        }
    }

    fun saveQuizScore(quizId: String, score: Int, total: Int) {
        viewModelScope.launch {
            repository.saveQuizScore(quizId, score, total)
        }
    }

    fun updateStudentName(name: String) {
        viewModelScope.launch {
            repository.updateStudentName(name)
        }
    }
}
