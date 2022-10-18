package com.demo.git5.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.git5.databinding.FragmentRepoBinding
import com.demo.git5.ui.detail.UserDetailActivity
import com.demo.git5.domain.model.UserRepo
import com.demo.git5.utils.state.LoaderState
import com.demo.git5.utils.viewUtils.setGone
import com.demo.git5.utils.viewUtils.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class ReposFragment : Fragment() {

    private val followerViewModel: ReposViewModel by viewModels()

    private var lists = mutableListOf<UserRepo>()

    private val reposAdapter: ReposAdapter by lazy {
        ReposAdapter(requireContext())
    }

    private val binding: FragmentRepoBinding by lazy {
        FragmentRepoBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleUserName()
        initObserver()
        initRecyclerView()
    }

    private fun handleUserName() {
        val activity = activity as UserDetailActivity
        val username: String? = activity.getUsername()
        followerViewModel.getUserFollowers(username!!)
    }

    private fun initRecyclerView() {
        binding.rcView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = reposAdapter
        }
    }

    private fun initObserver() {
        with(followerViewModel) {
            state.observe(viewLifecycleOwner) {
                handleStateLoading(it)
            }
            resultUserFollower.observe(viewLifecycleOwner) {
                handleUserFollower(it)
            }
        }
    }

    private fun handleEmptyFollower(data: List<UserRepo>) {
        if (data.isEmpty()) {
            binding.apply {
//                baseEmptyFollower.setVisible()
                rcView.setGone()
            }
        } else {
            binding.apply {
//                baseEmptyFollower.setGone()
                rcView.setVisible()
            }
        }
    }

    private fun handleStateLoading(loading: LoaderState) {
        if (loading is LoaderState.ShowLoading) {
            binding.apply {
                baseLoader.root.setVisible()
                rcView.setGone()
            }
        } else {
            binding.apply {
                baseLoader.root.setGone()
                rcView.setVisible()
            }
        }
    }

    private fun handleUserFollower(data: List<UserRepo>) {
        handleEmptyFollower(data)
        lists.clear()
        lists.addAll(data)
        reposAdapter.setItems(data = lists)
    }

}