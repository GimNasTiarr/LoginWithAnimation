package com.dicoding.loginwithanimation.view.upload

import androidx.lifecycle.ViewModel
import com.dicoding.loginwithanimation.data.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UploadViewModel(private val repository: StoryRepository) :ViewModel() {
    fun uploadStory(file : MultipartBody.Part, description: RequestBody) = repository.uploadStory(file,description)
}