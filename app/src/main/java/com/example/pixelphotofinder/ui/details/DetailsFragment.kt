package com.example.pixelphotofinder.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pixelphotofinder.R
import com.example.pixelphotofinder.databinding.FragmentGalleryPhotoDetailsBinding

class DetailsFragment: Fragment(R.layout.fragment_gallery_photo_details) {

    private val args by navArgs<DetailsFragmentArgs>()
    private var _binding: FragmentGalleryPhotoDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGalleryPhotoDetailsBinding.bind(view)

        binding.apply {
            val photo = args.photo

            Glide.with(this@DetailsFragment)
                .load(photo.src.original)
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBarCircle.isVisible = false
                        Toast.makeText(context,"Failed to load image, please check your connection!",Toast.LENGTH_SHORT).show()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBarCircle.isVisible = false
                        textViewName.isVisible = true
                        imageView.layoutParams.height = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            600F,
                            resources.displayMetrics
                            ).toInt();
                        return false
                    }

                })
                .into(imageView)

            textViewName.text = "Photo by ${photo.photographer}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}