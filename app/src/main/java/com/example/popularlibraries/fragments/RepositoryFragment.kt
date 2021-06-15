package com.example.popularlibraries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibraries.App
import com.example.popularlibraries.databinding.FragmentRepositoryBinding
import com.example.popularlibraries.model.GitHubRepo
import com.example.popularlibraries.navigation.BackButtonListener
import com.example.popularlibraries.presenter.RepositoryPresenter
import com.example.popularlibraries.view.RepositoryView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    private var vb: FragmentRepositoryBinding? = null
    val presenter: RepositoryPresenter by moxyPresenter {
        val repo = arguments?.getParcelable<GitHubRepo>(REPO) as GitHubRepo
        RepositoryPresenter(App.instance.router, repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentRepositoryBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    companion object {
        private const val REPO = "repository"

        @JvmStatic
        fun newInstance(repo: GitHubRepo) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO, repo)
            }
        }
    }

    override fun init() {}

    override fun setId(id: String) {
        val idText = "id: $id"
        vb?.idTextview?.text = idText
    }

    override fun setTitle(title: String) {
        val nameText = "Name: $title"
        vb?.titleTextview?.text = nameText
    }

    override fun setForksCount(forksCount: String) {
        val forksText = "Forks Count: $forksCount"
        vb?.countForksTextview?.text = forksText
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onDestroyView() {
        vb = null
        super.onDestroyView()
    }
}