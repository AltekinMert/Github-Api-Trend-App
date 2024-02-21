package com.example.github_trend_repo

import android.os.Binder
import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.github_trend_repo.databinding.FragmentDetailRepoBinding
import com.example.github_trend_repo.databinding.FragmentRepoBinding
import com.example.github_trend_repo.viewmodel.DetailRepoViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


class DetailRepoFragment : Fragment() {
    private var _binding: FragmentDetailRepoBinding? = null
    private val binding get() = _binding!!


    var full_name : String = "extra_fullname"

    private lateinit var viewModel : DetailRepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            full_name = it.getString("full_name").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailRepoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailRepoViewModel::class.java)

        viewModel.setDetailRepos(full_name)

        val lifecycleOwner: LifecycleOwner = this
        viewModel.getDetailRepos().observe(lifecycleOwner) {
            if (it != null) {
                binding.apply {

                    detailRepoDesc.text = it[0].owner.login
                    detailStars.text = it[0].stargazers_count
                    detailWatchers.text = it[0].watchers_count
                    detailForks.text = it[0].forks
                    detailSize.text = it[0].size
                    detailCreated.text = formatTimeAgo(it[0].created_at)
                    detailUpdated.text = formatTimeAgo(it[0].updated_at)
                    detailIssues.text = it[0].open_issues
                    Glide.with(this@DetailRepoFragment)
                        .load(it[0].owner.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(detailAvatar)

                }
            }
        }


    }
    fun formatTimeAgo(timestamp: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val currentTime = System.currentTimeMillis()
        val parsedTime = dateFormat.parse(timestamp)?.time ?: return ""

        val timeAgo = DateUtils.getRelativeTimeSpanString(parsedTime, currentTime, DateUtils.MINUTE_IN_MILLIS)
        return timeAgo.toString()
    }

}