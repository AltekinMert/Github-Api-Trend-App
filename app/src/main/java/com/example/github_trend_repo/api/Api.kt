package com.example.github_trend_repo.api

import com.example.github_trend_repo.data.DetailRepoResponse
import com.example.github_trend_repo.data.RepositoryResponse
import com.example.github_trend_repo.data.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("search/repositories")
    @Headers("Authorization: token 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")
    fun getSearchRepositories(
        @Query("q") query: String
    ): Call<RepositoryResponse>
    @GET("repos/{full_name}")
    @Headers("Authorization: token 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")
    fun getDetailRepos(
        @Path("full_name") full_name : String
    ) : Call<DetailRepoResponse>
}