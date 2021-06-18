package com.example.popularlibraries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibraries.App
import com.example.popularlibraries.NetworkStatusImpl
import com.example.popularlibraries.UsersRVAdapter
import com.example.popularlibraries.cache.GithubUsersCacheImpl
import com.example.popularlibraries.databinding.FragmentUsersBinding
import com.example.popularlibraries.model.Api
import com.example.popularlibraries.model.GlideImageLoader
import com.example.popularlibraries.model.RetrofitGithubUsersRepo
import com.example.popularlibraries.navigation.BackButtonListener
import com.example.popularlibraries.presenter.UsersPresenter
import com.example.popularlibraries.room.Database
import com.example.popularlibraries.view.UsersView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val database: Database by lazy {
        Database.apply { create(requireContext()) }.getInstance()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            RetrofitGithubUsersRepo(
                Api.api,
                NetworkStatusImpl(requireContext()),
                GithubUsersCacheImpl(database)
            ),
            App.instance.router,
            AndroidSchedulers.mainThread()
        )
    }
    private var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        vb = null
        adapter = null
        super.onDestroyView()
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}