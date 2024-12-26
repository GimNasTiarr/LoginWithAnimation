package com.dicoding.loginwithanimation.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.loginwithanimation.R
import com.dicoding.loginwithanimation.databinding.ActivityMainBinding
import com.dicoding.loginwithanimation.data.Result
import com.dicoding.loginwithanimation.utils.StoryAdapter
import com.dicoding.loginwithanimation.view.ViewModelFactory
import com.dicoding.loginwithanimation.view.upload.UploadActivity
import com.dicoding.loginwithanimation.view.welcome.WelcomeActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(application)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = StoryAdapter()
        binding.rvStory.adapter = adapter
        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }

        viewModel.getStories().observe(this) { stories ->
            when (stories) {
                is Result.Error -> showSnackBar(stories.error)
                Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    adapter.submitList(stories.data)
                }
            }
        }

    }

    private fun showSnackBar(message: String) {
        showLoading(false)
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        supportActionBar.apply {
            menuInflater.inflate(R.menu.support_action_bar_menu, menu)
            title = getString(R.string.app_name)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                viewModel.logout()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar3.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    override fun onResume() {
        super.onResume()
        viewModel.getStories()
    }

}