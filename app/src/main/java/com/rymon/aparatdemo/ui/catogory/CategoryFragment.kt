package com.rymon.aparatdemo.ui.catogory

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rymon.aparatdemo.R
import com.rymon.aparatdemo.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category){


    private val viewModel by viewModels<CategoryViewModel>()

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCategoryBinding.bind(view)
        val adapter = CategoryAdapter()

        binding.recyclerView.adapter =adapter


        viewModel.movieList.observe(viewLifecycleOwner, {
            adapter.setCategories(it)
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

        }
        setHasOptionsMenu(true)
    }


//    override fun onItemClick(video: HomeVideoIncluded) {
//        TODO("Not yet implemented")
//    }
}