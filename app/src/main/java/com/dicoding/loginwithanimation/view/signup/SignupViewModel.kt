package com.dicoding.loginwithanimation.view.signup

import androidx.lifecycle.ViewModel
import com.dicoding.loginwithanimation.data.StoryRepository

class SignupViewModel(private val repository: StoryRepository): ViewModel() {
     fun registerNew(name: String, email: String, password: String) =
        repository.registerStory(name,email,password)


}