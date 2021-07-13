package com.example.pixelphotofinder.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pixelphotofinder.databinding.GalleryPhotoItemHeaderFooterBinding

class GalleryPixelPhotoHeaderFooterAdapter(private val retry:() -> Unit): LoadStateAdapter<GalleryPixelPhotoHeaderFooterAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: GalleryPhotoItemHeaderFooterBinding):RecyclerView.ViewHolder(binding.root){

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            binding.apply {
                progressBarCircle.isVisible= loadState is LoadState.Loading
                textViewRetry.isVisible = loadState !is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = GalleryPhotoItemHeaderFooterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  LoadStateViewHolder(binding)
    }
}