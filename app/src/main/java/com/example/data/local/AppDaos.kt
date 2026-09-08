package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedSnippetDao {
    @Query("SELECT * FROM saved_snippets ORDER BY timestamp DESC")
    fun getAllSnippets(): Flow<List<SavedSnippet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSnippet(snippet: SavedSnippet): Long

    @Update
    suspend fun updateSnippet(snippet: SavedSnippet)

    @Query("DELETE FROM saved_snippets WHERE id = :id")
    suspend fun deleteSnippet(id: Long)
}

@Dao
interface LessonProgressDao {
    @Query("SELECT * FROM lesson_progress")
    fun getAllProgress(): Flow<List<LessonProgress>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markCompleted(progress: LessonProgress)

    @Query("DELETE FROM lesson_progress WHERE lessonId = :lessonId")
    suspend fun unmarkCompleted(lessonId: String)
}

@Dao
interface QuizScoreDao {
    @Query("SELECT * FROM quiz_scores")
    fun getAllScores(): Flow<List<QuizScoreRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuizScore(record: QuizScoreRecord)
}

@Dao
interface UserProfileDao {
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getProfile(): Flow<UserProfile?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profile: UserProfile)
}
