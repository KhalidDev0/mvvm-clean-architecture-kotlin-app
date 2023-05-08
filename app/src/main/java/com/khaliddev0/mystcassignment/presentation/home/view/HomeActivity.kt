package com.khaliddev0.mystcassignment.presentation.home.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.khaliddev0.mystcassignment.databinding.ActivityHomeBinding
import com.khaliddev0.mystcassignment.presentation.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        binding.apply {
            userListRecycleView.layoutManager = LinearLayoutManager(this@HomeActivity)
            errorDialog.refreshButton.setOnClickListener { viewModel.getUserList() }
        }

        observe()
    }

    private fun observe() {
        viewModel.apply {
            lifecycleScope.launch {
                launch {
                    userListAdapter.collect {
                        binding.userListRecycleView.adapter = it
                    }
                }
                launch {
                    errorMassage.collect {
                        if (!it.isNullOrEmpty()) {
                            binding.errorDialog.errorText.text = it
                            binding.errorDialog.root.visibility = View.VISIBLE
                        } else binding.errorDialog.root.visibility = View.GONE
                    }
                }
                launch {
                    loadingIndicatorVisibility.collect {
                        binding.loadingIndicator.root.visibility = it
                    }
                }
            }
        }
    }
}