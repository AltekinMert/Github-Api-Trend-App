package com.example.github_trend_repo.data

data class Repository(
    val id : Int,
    val full_name : String,
    val owner: Owner,
    val stargazers_count : String,
    val description : String,
    val watchers_count : String,
    val forks : String,
    val size : String,
    val created_at : String,
    val updated_at : String,
    val open_issues : String
)
data class Owner(val avatar_url : String,val login : String)