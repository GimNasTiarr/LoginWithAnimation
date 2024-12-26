package com.dicoding.loginwithanimation.di

import android.content.Context
import com.dicoding.loginwithanimation.data.StoryRepository
import com.dicoding.loginwithanimation.data.pref.UserPreference
import com.dicoding.loginwithanimation.data.pref.dataStore
import com.dicoding.loginwithanimation.data.remote.retrofit.ApiConfig

object Injection {   fun provideRepository(context: Context): StoryRepository {
    val pref = UserPreference.getInstance(context.dataStore)
    val apiService = ApiConfig.getApiService()
    return StoryRepository.getInstance(apiService, pref)
}
}