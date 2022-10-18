package com.demo.git5.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.git5.databinding.ActivityMainBinding
import com.demo.git5.domain.model.UserSearchItem
import com.demo.git5.utils.state.LoaderState
import com.demo.git5.utils.viewUtils.setGone
import com.demo.git5.utils.viewUtils.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val items = mutableListOf<UserSearchItem>()

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(this)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        searchUsers()
        initRecyclerView()
        initObserver()
    }

    private fun searchUsers() {
        binding.apply {
            svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (it.isNotEmpty()) {
                            items.clear()
                            mainViewModel.getUserFromApi(query)
                            svSearch.clearFocus()
                            setIllustration(false)
                        } else {
                            svSearch.clearFocus()
                            setIllustration(true)
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean = false
            })
            svSearch.setOnCloseListener {
                svSearch.setQuery("", false)
                svSearch.clearFocus()
                mainAdapter.clearItems()
                setIllustration(true)
                true
            }
        }
    }

    private fun initObserver() {
        with(mainViewModel) {
            state.observe(this@MainActivity) {
                it?.let {
                    handleStateLoading(it)
                }
            }
            resultUserApi.observe(this@MainActivity) {
                it?.let {
                    handleUserFromApi(it)
                }
            }
            networkError.observe(this@MainActivity) {
                it?.let {
                    handleStateInternet(it)
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvUser.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mainAdapter
        }
        mainAdapter.setActivity(this)
    }

    private fun handleUserFromApi(result: List<UserSearchItem>) {
        items.clear()
        items.addAll(result)
        mainAdapter.setItems(items)
    }

    private fun handleStateInternet(error: Boolean) {
        with(binding) {
            if (error) {
                baseLoading.root.setVisible()
                rvUser.setGone()
            } else {
                baseLoading.root.setGone()
                rvUser.setVisible()
            }
        }

    }

    private fun handleStateLoading(loading: LoaderState) {
        with(binding) {
            if (loading is LoaderState.ShowLoading) {
                baseLoading.root.setVisible()
                setIllustration(false)
                rvUser.setGone()
            } else {
                baseLoading.root.setGone()
                setIllustration(false)
                rvUser.setVisible()
            }
        }

    }

    private fun setIllustration(status: Boolean) {
        binding.baseEmpty.root.visibility = if (status) VISIBLE else INVISIBLE
    }
}