package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.CurriculumRepository
import com.example.data.model.QuizData
import com.example.data.model.ShowcaseProjects
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("تیروتیر وب", appName)
  }

  @Test
  fun `verify curriculum modules and lessons`() {
    val modules = CurriculumRepository.modules
    assertEquals(6, modules.size)

    val lessons = CurriculumRepository.lessons
    assertTrue(lessons.size >= 8)

    val module1Lessons = CurriculumRepository.getLessonsForModule(1)
    assertTrue(module1Lessons.isNotEmpty())
    assertNotNull(CurriculumRepository.getLessonByPrefix("hcj00"))
  }

  @Test
  fun `verify all 14 showcase projects exist`() {
    val projects = ShowcaseProjects.projects
    assertEquals(14, projects.size)

    val musicPlayer = ShowcaseProjects.getProjectById("proj_music_player")
    assertNotNull(musicPlayer)
    assertTrue(musicPlayer!!.htmlCode.contains("audio") || musicPlayer.htmlCode.contains("player"))
  }

  @Test
  fun `verify quizzes and bug puzzles`() {
    assertTrue(QuizData.milestoneQuizzes.containsKey("html_css"))
    assertTrue(QuizData.milestoneQuizzes.containsKey("js_dom"))
    assertTrue(QuizData.codeBugPuzzles.size >= 2)
  }
}
