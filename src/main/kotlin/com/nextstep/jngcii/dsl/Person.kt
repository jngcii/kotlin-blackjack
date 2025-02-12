package com.nextstep.jngcii.dsl

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(
    val name: String,
    val company: String,
    val softSkills: List<SoftSkill>,
    val hardSkills: List<HardSkill>,
    val languages: List<Language>
)

class PersonBuilder {
    private var name: String = ""
    private var company: String? = null
    private var softSkills: List<SoftSkill> = emptyList()
    private var hardSkills: List<HardSkill> = emptyList()
    private var languages: List<Language> = emptyList()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        val skills = SkillBuilder().apply(block).build()
        softSkills = skills.softs
        hardSkills = skills.hards
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build() = Person(
        name = name,
        company = company ?: "무직",
        softSkills = softSkills,
        hardSkills = hardSkills,
        languages = languages
    )
}
