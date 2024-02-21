package com.example.github_trend_repo.api

import com.example.github_trend_repo.data.Repository

interface onItemClickCallback{
    fun onItemClicked(data : Repository)
}