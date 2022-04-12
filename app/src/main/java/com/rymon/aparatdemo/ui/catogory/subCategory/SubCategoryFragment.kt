package com.rymon.aparatdemo.ui.catogory.subCategory

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.rymon.aparatdemo.R
import com.rymon.aparatdemo.data.models.Video
import com.rymon.aparatdemo.databinding.FragmentCategorySubBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubCategoryFragment : Fragment(R.layout.fragment_category_sub),
    SubCategoryAdapter.OnItemClickListener {

    private val args: SubCategoryFragmentArgs by navArgs()

    private val viewModel by viewModels<SubCategoryViewModel>()

    private var _binding: FragmentCategorySubBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCategorySubBinding.bind(view)
        val adapter = SubCategoryAdapter(this)
        Toast.makeText(context,"cat : ${args.categoryInfo.id} is clicked", Toast.LENGTH_LONG).show()

        initViews(binding, adapter)
        subscribeObservables(viewModel, adapter)
        loadStateInit(binding,adapter)
        requestForVideoCatch()

    }


    private fun initViews(binding: FragmentCategorySubBinding, adapter: SubCategoryAdapter) {
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = SubCategoryLoadStateAdapter { adapter.retry() },
                footer = SubCategoryLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }
        }
        setHasOptionsMenu(true)
    }

    private fun subscribeObservables(viewModel: SubCategoryViewModel, adapter: SubCategoryAdapter) {
        viewModel.categoryVideos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }
    private fun loadStateInit(binding: FragmentCategorySubBinding, adapter: SubCategoryAdapter) {
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
    private fun requestForVideoCatch(){
            viewModel.searchVideos(args.categoryInfo.id.toInt())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(video: Video) {
        TODO("Not yet implemented")
    }
}