package com.rymon.aparatdemo.ui.mainHome

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.rymon.aparatdemo.R
import com.rymon.aparatdemo.data.main.DataX
import com.rymon.aparatdemo.databinding.FragmentMainHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHomeFragment : Fragment(R.layout.fragment_main_home),
    MainHomeVideoHorizontalAdapter.OnItemClickListener {

    private val viewModel by viewModels<MainHomeViewModel>()

    private var _binding: FragmentMainHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainHomeBinding.bind(view)
        val adapter = MainHomeAdapter(this)
        initRecycler(adapter)
        initViewModel(adapter)
        addLoadStateListener(adapter)
        setHasOptionsMenu(true)
    }

    private fun initRecycler(adapter: MainHomeAdapter) {
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = MainHomeLoadStateAdapter{ adapter.retry() },
                footer = MainHomeLoadStateAdapter{ adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }
        }
    }

    private fun initViewModel(adapter: MainHomeAdapter) {
        viewModel.videos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun addLoadStateListener(adapter: MainHomeAdapter) {

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
            }
        }

    }

/*

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchVideos(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(video: DataX) {
        val action = MainHomeFragmentDirections.actionMainHomeFragmentToDetailsFragment(video.attributes?.frame)
        findNavController().navigate(action)
    }
}