package com.example.data.local

import kotlinx.coroutines.flow.Flow

class AppRepository(private val db: AppDatabase) {
    val allSnippets: Flow<List<SavedSnippet>> = db.savedSnippetDao().getAllSnippets()
    val allProgress: Flow<List<LessonProgress>> = db.lessonProgressDao().getAllProgress()
    val allQuizScores: Flow<List<QuizScoreRecord>> = db.quizScoreDao().getAllScores()
    val userProfile: Flow<UserProfile?> = db.userProfileDao().getProfile()

    suspend fun saveSnippet(snippet: SavedSnippet): Long {
        return db.savedSnippetDao().insertSnippet(snippet)
    }

    suspend fun deleteSnippet(id: Long) {
        db.savedSnippetDao().deleteSnippet(id)
    }

    suspend fun toggleLessonProgress(lessonId: String, currentCompleted: Boolean) {
        if (currentCompleted) {
            db.lessonProgressDao().unmarkCompleted(lessonId)
        } else {
            db.lessonProgressDao().markCompleted(LessonProgress(lessonId = lessonId, isCompleted = true))
        }
    }

    suspend fun saveQuizScore(quizId: String, score: Int, total: Int) {
        db.quizScoreDao().saveQuizScore(QuizScoreRecord(quizId, score, total))
    }

    suspend fun updateStudentName(name: String) {
        db.userProfileDao().saveProfile(UserProfile(id = 1, studentName = name))
    }
}
