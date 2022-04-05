package com.rymon.aparatdemo.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import com.rymon.aparatdemo.R
import com.rymon.aparatdemo.data.home.HomeVideoIncluded
import com.rymon.aparatdemo.databinding.FragmentHomeBinding
import com.rymon.aparatdemo.ui.search.SearchVideoLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    HomeVideoAdapter.OnItemClickListener {


    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)
        val adapter = HomeVideoAdapter2()
        binding.recyclerView.adapter =adapter


        viewModel.movieList.observe(viewLifecycleOwner, {
            adapter.setMovies(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.getAllVideoIncludedWithoutPaging()

        binding.apply {

/*

            viewModel.movieList.observe(this, {
                adapter.setMovies(it)
            })

            viewModel.errorMessage.observe(this, {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })

            viewModel.loading.observe(this, Observer {
                if (it) {
                    binding.progressDialog.visibility = View.VISIBLE
                } else {
                    binding.progressDialog.visibility = View.GONE
                }
            })
*/

            viewModel.getAllVideoIncludedWithoutPaging()
            val test = viewModel.movieList.value?.get(0)?.id
            testText.text = "Test $test"


            /*
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = SearchVideoLoadStateAdapter { adapter.retry() },
                footer = SearchVideoLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }
        }

        viewModel.videos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }



        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }*/
        }

        setHasOptionsMenu(true)
    }

    override fun onItemClick(video: HomeVideoIncluded) {
        TODO("Not yet implemented")
    }
}