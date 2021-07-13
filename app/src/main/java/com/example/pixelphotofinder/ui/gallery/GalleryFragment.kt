package com.example.pixelphotofinder.ui.gallery

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pixelphotofinder.R
import com.example.pixelphotofinder.databinding.FragmentGalleryBinding
import com.example.pixelphotofinder.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment: Fragment(R.layout.fragment_gallery) {

    private val viewModel by viewModels<GalleryViewModel>()
    private var _binding : FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: android.view.View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGalleryBinding.bind(view)

        val adapter = GalleryPixelPhotoAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = GalleryPixelPhotoHeaderFooterAdapter {adapter.retry()},
                footer = GalleryPixelPhotoHeaderFooterAdapter {adapter.retry()}
            )
        }

        viewModel.photos.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}