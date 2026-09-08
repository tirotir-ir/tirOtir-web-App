package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.data.model.CurriculumRepository
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.components.TirOtirBottomNavigationBar
import com.example.ui.components.TirOtirTopAppBar
import com.example.ui.screens.AboutAcademyScreen
import com.example.ui.screens.CertificateScreen
import com.example.ui.screens.CurriculumScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PlaygroundScreen
import com.example.ui.screens.ProjectsShowcaseScreen
import com.example.ui.screens.QuizzesScreen
import com.example.ui.theme.TirOtirTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val themeMode by viewModel.themeMode.collectAsState()
            val currentScreen by viewModel.currentScreen.collectAsState()
            val selectedModuleId by viewModel.selectedModuleId.collectAsState()
            val selectedProject by viewModel.selectedProject.collectAsState()
            val sandboxCode by viewModel.sandboxCode.collectAsState()
            val consoleLogs by viewModel.consoleLogs.collectAsState()
            val savedSnippets by viewModel.savedSnippets.collectAsState()
            val lessonProgress by viewModel.lessonProgress.collectAsState()
            val quizScores by viewModel.quizScores.collectAsState()
            val userProfile by viewModel.userProfile.collectAsState()

            val totalLessons = CurriculumRepository.lessons.size
            val completedCount = lessonProgress.count { it.isCompleted }

            // Default RTL layout direction for Persian interface
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                TirOtirTheme(themeMode = themeMode) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TirOtirTopAppBar(
                                currentScreen = currentScreen,
                                themeMode = themeMode,
                                completedCount = completedCount,
                                totalCount = totalLessons,
                                onCycleTheme = { viewModel.cycleThemeMode() },
                                onNavigateAbout = { viewModel.navigateTo(AppScreen.ABOUT) }
                            )
                        },
                        bottomBar = {
                            TirOtirBottomNavigationBar(
                                currentScreen = currentScreen,
                                onSelectScreen = { viewModel.navigateTo(it) }
                            )
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            when (currentScreen) {
                                AppScreen.HOME -> {
                                    HomeScreen(
                                        completedLessonCount = completedCount,
                                        totalLessonCount = totalLessons,
                                        onNavigate = { viewModel.navigateTo(it) },
                                        onSelectModule = { viewModel.selectModule(it) }
                                    )
                                }
                                AppScreen.CURRICULUM -> {
                                    CurriculumScreen(
                                        selectedModuleId = selectedModuleId,
                                        onSelectModule = { viewModel.selectModule(it) },
                                        isLessonCompleted = { viewModel.isLessonCompleted(it) },
                                        onToggleLessonCompleted = { viewModel.toggleLessonProgress(it) },
                                        onOpenInSandbox = { viewModel.loadSnippetIntoSandbox(it) }
                                    )
                                }
                                AppScreen.PLAYGROUND -> {
                                    PlaygroundScreen(
                                        currentCode = sandboxCode,
                                        onCodeChange = { viewModel.updateSandboxCode(it) },
                                        consoleLogs = consoleLogs,
                                        onAddConsoleLog = { viewModel.addConsoleLog(it) },
                                        onClearLogs = { viewModel.clearConsoleLogs() },
                                        savedSnippets = savedSnippets,
                                        onSaveSnippet = { title, desc -> viewModel.saveCurrentSnippet(title, desc) },
                                        onDeleteSnippet = { viewModel.deleteSnippet(it) }
                                    )
                                }
                                AppScreen.PROJECTS -> {
                                    ProjectsShowcaseScreen(
                                        selectedProject = selectedProject,
                                        onSelectProject = { viewModel.selectProject(it) },
                                        onOpenInSandbox = { viewModel.loadSnippetIntoSandbox(it) }
                                    )
                                }
                                AppScreen.QUIZZES -> {
                                    QuizzesScreen(
                                        quizScores = quizScores,
                                        onSaveQuizScore = { qId, sc, tot -> viewModel.saveQuizScore(qId, sc, tot) }
                                    )
                                }
                                AppScreen.CERTIFICATE -> {
                                    CertificateScreen(
                                        userProfile = userProfile,
                                        completedLessonCount = completedCount,
                                        totalLessonCount = totalLessons,
                                        onUpdateStudentName = { viewModel.updateStudentName(it) }
                                    )
                                }
                                AppScreen.ABOUT -> {
                                    AboutAcademyScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
