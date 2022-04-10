package com.miu.mdp.resumebuildermary

import java.io.Serializable

data class User(
    val profilePic: String? = null,
    val fullName: String,
    val profession: String,
    val careerNote: String,
    val description: String,
    val tools: Map<String, String>
):Serializable

data class Education(
    val schools: List<School>
):Serializable

data class School(
    val name: String,
    val courseTaken: String,
    val yearOfStudy: String,
    val logo: String
):Serializable

data class WorkExperience(
    val experience: List<Experience>
):Serializable

data class Experience(
    val company: String,
    val companyLogo: String,
    val role: String,
    val duration: String,
    val location: String,
    val description: String
):Serializable

data class Contact(
    val contactType: ContactType,
    val contactData: String
):Serializable

enum class ContactType {
    PHONE, EMAIL, LINK
}
