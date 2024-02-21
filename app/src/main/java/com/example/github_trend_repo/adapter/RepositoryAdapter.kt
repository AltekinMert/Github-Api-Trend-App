package com.example.github_trend_repo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.github_trend_repo.data.Repository
import com.example.github_trend_repo.databinding.ItemUserBinding
import com.example.github_trend_repo.api.onItemClickCallback

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(){
    private val list = ArrayList<Repository>()

    private var onItemClickCallback : onItemClickCallback?=null

    fun setOnItemClickCallback(onItemClickCallback: onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    fun setList(repos : ArrayList<Repository>){
        list.clear()
        list.addAll(repos)
        notifyDataSetChanged()
    }
    inner class RepositoryViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(repos : Repository){
            binding.root.setOnClickListener(){
                onItemClickCallback?.onItemClicked(repos)
            }
            binding.apply { Glide.with(itemView)
                .load(repos.owner.avatar_url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(ivUser)
                tvUsername.text = repos.description
                stargazerCount.visibility = View.VISIBLE
                star.visibility = View.VISIBLE
                stargazerCount.text = repos.stargazers_count
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

}