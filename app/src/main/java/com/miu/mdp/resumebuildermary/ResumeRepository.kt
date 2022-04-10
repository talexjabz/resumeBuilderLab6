package com.miu.mdp.resumebuildermary

interface ResumeRepository {
    fun saveUser(fullName: String, email: String)
    fun getUser(email: String): User?
    fun getEducation(): Education
    fun getExperience(): WorkExperience
    fun getContactDetails(): List<Contact>
}
