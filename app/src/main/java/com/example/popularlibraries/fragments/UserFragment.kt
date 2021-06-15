package com.example.popularlibraries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibraries.App
import com.example.popularlibraries.RepositoryRVAAdapter
import com.example.popularlibraries.databinding.FragmentUserBinding
import com.example.popularlibraries.model.Api
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.RetrofitGithubRepositoriesRepo
import com.example.popularlibraries.navigation.BackButtonListener
import com.example.popularlibraries.presenter.UserPresenter
import com.example.popularlibraries.view.UserView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var vb: FragmentUserBinding? = null
    private var adapter: RepositoryRVAAdapter? = null
    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER) as GithubUser
        UserPresenter(
            App.instance.router,
            user,
            RetrofitGithubRepositoriesRepo(Api.api),
            AndroidSchedulers.mainThread()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun init() {
        vb?.recyclerRepository?.layoutManager = LinearLayoutManager(context)
        adapter = RepositoryRVAAdapter(presenter.reposListPresenter)
        vb?.recyclerRepository?.adapter = adapter
    }

    override fun setLogin(text: String) {
        vb?.userLogin?.text = text
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroyView() {
        vb = null
        adapter = null
        super.onDestroyView()
    }

    companion object {
        private const val USER = "USER"
        fun newInstance(user: GithubUser) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER, user)
                }
            }
    }
}