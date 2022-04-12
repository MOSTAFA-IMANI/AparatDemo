package com.rymon.aparatdemo.ui.catogory.mainCategory

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rymon.aparatdemo.R
import com.rymon.aparatdemo.data.category.Category
import com.rymon.aparatdemo.databinding.FragmentCategoryMainBinding

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainCategoryFragment : Fragment(R.layout.fragment_category_main) ,
    MainCategoryAdapter.OnItemClickListener {


    private val viewModel by viewModels<MainCategoryViewModel>()

    private var _binding: FragmentCategoryMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCategoryMainBinding.bind(view)
        val adapter = MainCategoryAdapter(this)


        initViews(adapter)
        subscribeOnLiveData(adapter)
        requestForAllCategoryes()


    }

    private fun requestForAllCategoryes() {
        viewModel.getAllCategories()
    }

    private fun subscribeOnLiveData(adapter: MainCategoryAdapter) {
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


    }

    private fun initViews(adapter: MainCategoryAdapter) {
        binding.recyclerView.adapter =adapter
        setHasOptionsMenu(true)
    }

    override fun onItemClick(category: Category) {
        val action = MainCategoryFragmentDirections.actionMainCategoryFragmentToSubCategoryFragment(category)
        findNavController().navigate(action)
    }

}