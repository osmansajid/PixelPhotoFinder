package com.example.pixelphotofinder.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pixelphotofinder.R
import com.example.pixelphotofinder.data.PhotoItem
import com.example.pixelphotofinder.databinding.ItemPhotoBinding

class GalleryPixelPhotoAdapter(private val listener: OnClickListener): PagingDataAdapter<PhotoItem,GalleryPixelPhotoAdapter.PhotoViewHolder>(
    COMPARATOR) {

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val pos = bindingAdapterPosition
                if(pos != RecyclerView.NO_POSITION){
                    val photo = getItem(pos)
                    if(photo != null){
                        listener.onClick(photo)
                    }
                }
            }
        }

        fun setItem(photoItem: PhotoItem){
            binding.apply {
                Glide.with(itemView)
                    .load(photoItem.src.large)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)

                textViewUsername.text = photoItem.photographer
            }
        }
    }

    interface OnClickListener{
        fun onClick(photoItem: PhotoItem)
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<PhotoItem>(){
            override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem) =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return  PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if(currentItem != null){
            holder.setItem(currentItem)
        }
    }


}