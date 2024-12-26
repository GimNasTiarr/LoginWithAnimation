package com.dicoding.loginwithanimation.view.login

import androidx.lifecycle.ViewModel
import com.dicoding.loginwithanimation.data.StoryRepository

class LoginViewModel(private val repository: StoryRepository) : ViewModel() {
    fun loginStory(email: String, password: String) = repository.loginStory(email, password)
}