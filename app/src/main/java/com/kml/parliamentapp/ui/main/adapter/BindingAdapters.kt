package com.kml.parliamentapp.ui.main.adapter

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//Is called when XML contains value imageUrl
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    val mImgUrl =  "https://avoindata.eduskunta.fi/$imgUrl"
    Log.i("BindingAdapters", "Url is: $mImgUrl")
    mImgUrl.let {
        val imgUri = mImgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context).load(imgUri).into(imgView)
    }
}