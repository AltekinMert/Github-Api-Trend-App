package com.example.github_trend_repo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.github_trend_repo.api.RetrofitClient
import com.example.github_trend_repo.data.Repository
import com.example.github_trend_repo.data.RepositoryResponse
import retrofit2.Call
import retrofit2.Response

//PROBLEM HERE PROBLEM HERE
class DetailRepoViewModel  : ViewModel(){
    val listRepos = MutableLiveData<ArrayList<Repository>>()
    fun setDetailRepos(full_name : String){
        RetrofitClient.apiInstance
            .getSearchRepositories(full_name)
            .enqueue(object : retrofit2.Callback<RepositoryResponse> {//problem might be here
            override fun onResponse(
                call: Call<RepositoryResponse>,
                response: Response<RepositoryResponse>
            ) {
                if(response.isSuccessful){
                    listRepos.postValue(response.body()?.items)
                }
            }

                override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                    Log.d("Failure", "Failed")
                }

            })
    }
    fun getDetailRepos() : LiveData<ArrayList<Repository>>{
        return listRepos
    }
}