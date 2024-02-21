package com.example.github_trend_repo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.github_trend_repo.api.RetrofitClient
import com.example.github_trend_repo.data.User
import com.example.github_trend_repo.data.UserResponse
import retrofit2.Call
import retrofit2.Response

class MainViewModel :ViewModel(){

    val listUsers = MutableLiveData<ArrayList<User>>()
    fun setSearchUsers(query : String){
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : retrofit2.Callback<UserResponse> {//problem might be here
                override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
                ) {
                    if(response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", "Failed")
                }

            })
    }
    fun getSearchUsers() : LiveData<ArrayList<User>>{
        return listUsers
    }

}


