package com.example.data.model

data class ModuleInfo(
    val id: Int,
    val titleFa: String,
    val subtitleFa: String,
    val iconName: String,
    val snippetRange: String,
    val colorHex: Long
)

data class Lesson(
    val id: String,
    val moduleId: Int,
    val prefix: String, // e.g. hcj00, hcj26
    val titleFa: String,
    val titleEn: String,
    val descriptionFa: String,
    val codeSnippet: String,
    val exerciseFa: String = "",
    val solutionFa: String = "",
    val tags: List<String> = emptyList()
)
