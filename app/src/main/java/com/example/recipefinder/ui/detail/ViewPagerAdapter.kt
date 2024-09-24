package com.example.recipefinder.ui.detail



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinder.databinding.ViewPagerImgBinding
import com.squareup.picasso.Picasso


class ViewPagerAdapter(var imageList: MutableList<String>) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>(){



    class MyViewHolder(val binding: ViewPagerImgBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewPagerImgBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentImage = imageList[position]

        with(holder.binding){
println(currentImage)
            println("current image")
            Picasso.get().load(currentImage).into(img)

        }

    }
    override fun getItemCount(): Int = imageList.size

    }

