package com.example.github_trend_repo

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github_trend_repo.adapter.RepositoryAdapter
import com.example.github_trend_repo.api.onItemClickCallback
import com.example.github_trend_repo.data.Repository
import com.example.github_trend_repo.databinding.FragmentRepoBinding
import com.example.github_trend_repo.viewmodel.RepositoryViewModel


class RepoFragment : Fragment() {

  private var _binding: FragmentRepoBinding? = null
  private val binding get() = _binding!!
  private lateinit var viewModel : RepositoryViewModel
  private lateinit var adapter : RepositoryAdapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentRepoBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter = RepositoryAdapter()
    adapter.notifyDataSetChanged()


    viewModel = ViewModelProvider(viewModelStore,
      ViewModelProvider.NewInstanceFactory()).get(RepositoryViewModel::class.java)
    viewModel.setSearchRepos("stars:>100000")
    binding.apply {
      rvUser2.layoutManager = LinearLayoutManager(requireContext().applicationContext)
      rvUser2.setHasFixedSize(true)
      rvUser2.adapter = adapter
      btnSearch2.setOnClickListener() {
        searchRepos()
      }
      etQuery2.setOnKeyListener { v, keyCode, event ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
          searchRepos()
          return@setOnKeyListener true
        }
        return@setOnKeyListener false
      }
    }

    val lifecycleOwner: LifecycleOwner = this
    viewModel.getSearchRepos().observe(lifecycleOwner, {
      if (it != null) {
        adapter.setList(it)
        showLoading(false)
      }
    })




    adapter.setOnItemClickCallback(object : onItemClickCallback {
      override fun onItemClicked(data: Repository) {
        val action = RepoFragmentDirections.actionRepoFragmentToDetailRepoFragment(fullName = data.full_name)
        findNavController().navigate(action)

      }


    })
  }
  private fun searchRepos() {
    binding.apply {
      val query = etQuery2.text.toString()
      if (query.isEmpty()) return
      showLoading(true)
      viewModel.setSearchRepos("stars:>100000")
    }

  }


  private fun showLoading(state: Boolean) {
    if (state) {
      binding.progressBar2.visibility = View.GONE
    } else {

    }
  }

}