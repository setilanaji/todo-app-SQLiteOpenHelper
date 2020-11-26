package com.ydh.todoapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable
import com.bumptech.glide.Glide
import com.ydh.todoapp.R
import com.ydh.todoapp.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(this)
                .load("https://i1.sndcdn.com/artworks-000081628310-9dnnio-t500x500.jpg")
                .override(100, 100)
                .circleCrop()
                .into(binding.ivProfile2)
        Glide.with(this)
                .load("https://avatarfiles.alphacoders.com/215/215445.jpg")
                .override(100, 100)
                .circleCrop()
                .into(binding.ivProfile)
    }

}