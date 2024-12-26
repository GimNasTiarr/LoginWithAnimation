package com.dicoding.loginwithanimation.view.widget

import android.content.Intent
import android.widget.RemoteViewsService
import com.dicoding.loginwithanimation.data.pref.UserPreference
import com.dicoding.loginwithanimation.data.pref.dataStore
import com.dicoding.loginwithanimation.data.remote.retrofit.ApiConfig
import com.dicoding.loginwithanimation.view.widget.StackRemoteViewFactory

class StackWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        val pref = UserPreference.getInstance(this.applicationContext.dataStore)
        val apiService = ApiConfig.getApiService()
        return StackRemoteViewFactory(this.applicationContext, apiService,pref)
    }
}