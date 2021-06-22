package com.example.popularlibraries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.databinding.ItemRepositoryBinding
import com.example.popularlibraries.presenter.IRepoListPresenter
import com.example.popularlibraries.view.RepoItemView

class RepositoryRVAAdapter(val presenter: IRepoListPresenter) :
    RecyclerView.Adapter<RepositoryRVAAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(private val vb: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override var pos: Int = -1

        override fun setName(name: String) = with(vb) {
            tvName.text = name
        }
    }
}