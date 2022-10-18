package com.demo.git5.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.demo.git5.R
import com.demo.git5.databinding.ActivityUserDetailBinding
import com.demo.git5.ui.pager.ViewPagerAdapter
import com.demo.git5.domain.model.UserDetail
import com.demo.git5.event.*
import com.demo.git5.utils.state.LoaderState
import com.demo.git5.utils.viewUtils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity(), EventListener {
    private val EVENT_TYPES: List<EventType> = listOf(
        EventType.SHOW_URL,
    )
    private val binding: ActivityUserDetailBinding by lazy {
        ActivityUserDetailBinding.inflate(layoutInflater)
    }

    private val userDetailViewModel: UserDetailViewModel by viewModels()

    private var userDetail: UserDetail? = null

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleIntent()
        initObserver()
        fetchData()
        initToolbar()
        initPageAdapter()
        EventBus.addListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    fun getUsername(): String? {
        return username
    }

    private fun initToolbar() {

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            elevation = 0f
            title = "$username\'s Profile"
            setCustomView(R.layout.custom_toolbar)
        }
    }

    private fun initPageAdapter() {
        val sectionPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun fetchData() {
        username?.let {
            userDetailViewModel.getUserDetail(it)
        }
    }

    private fun handleIntent() {
        username = intent.getStringExtra(USERNAME_KEY) as String
    }

    private fun initObserver() {
        with(userDetailViewModel) {
            state.observe(this@UserDetailActivity) {
                handleStateLoading(it)
            }
            resultUserDetail.observe(this@UserDetailActivity) {
                handleResultUserDetail(it)
            }
        }

    }

    private fun handleStateLoading(loading: LoaderState) {
        if (loading is LoaderState.ShowLoading) {
//            binding.favButton.setGone()
        } else {
//            binding.favButton.setVisible()
        }
    }

    private fun handleResultUserDetail(data: UserDetail) {
        userDetail = data
        binding.apply {
            txtUsername.text = data.name
            txtBio.text = data.bio ?: getString(R.string.no_bio)
            txtFollower.text = data.followers.toString()
            txtFollowing.text = data.following.toString()
            ivUser.load(data.avatarUrl)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    companion object {
        const val USERNAME_KEY = "username_key"
    }

    override val types: List<EventType>
        get() = EVENT_TYPES

    override fun onNotify(event: Event) {
        when (event.type) {
            EventType.SHOW_URL -> {
                val url = (event as ShowURLEvent).url
                supportActionBar?.title = url

                Log.i("DETAIL-ACTIVITY", "RECEIVE EVENT $url")
            }
        }
    }
}