package com.kml.parliamentapp.ui.main.adapter

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* BindingAdapters that are being called from xml when value is used,
* has methods to insert images to MemberFragment,
* setting image resource using party.party if String is same,
* setting party full name from shortened version for PartyList and MemberFragment
* */

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kml.parliamentapp.R
import com.kml.parliamentapp.data.model.Party
import com.kml.parliamentapp.data.api.ParliamentApiStatus

//Is called when XML contains value imageUrl
//Used to get image of selected member
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    val mImgUrl =  "https://avoindata.eduskunta.fi/$imgUrl"
    Log.i("BindingAdapters", "Url is: $mImgUrl")
    mImgUrl.let {
        val imgUri = mImgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context).load(imgUri).apply(RequestOptions().placeholder(R.drawable.loading_animation)).into(imgView)
    }
}

@BindingAdapter("partyLogo")
fun ImageView.bindLogo(item: Party) {
    setImageResource(when (item.party) {
        "kesk" -> R.mipmap.ic_kesk_logo
        "sd" -> R.mipmap.ic_sdp_logo
        "vihr" -> R.mipmap.ic_vihr_logo
        "kok" -> R.mipmap.ic_kok_logo
        "ps" -> R.mipmap.ic_ps_logo
        "r" -> R.mipmap.ic_r_logo
        "vas" -> R.mipmap.ic_vas_logo
        "kd" -> R.mipmap.ic_kd_logo
        "liik" -> R.mipmap.ic_liik_logo
        else -> R.drawable.ic_error_icon
    })
}

@BindingAdapter("partyName")
fun TextView.bindPartyName(item: Party) {
    text = (when (item.party) {
        "sd" -> "Suomen Sosialidemokraattinen Puolue"
        "vihr" -> "Vihreä liitto"
        "kok" -> "Kansallinen Kokoomus"
        "ps" -> "Perussuomalaiset"
        "r" -> "Suomen ruotsalainen kansanpuolue"
        "kesk" -> "Suomen Keskusta"
        "vas" -> "Vasemmistoliitto"
        "kd" -> "Suomen Kristillisdemokraatit"
        "liik" -> "Liike Nyt"
        else -> "Error"
    })
}

@BindingAdapter("partyNameMember")
fun TextView.bindPartyNameMember(item: String) {
    text = (when (item) {
        "sd" -> "Suomen Sosialidemokraattinen Puolue"
        "vihr" -> "Vihreä liitto"
        "kok" -> "Kansallinen Kokoomus"
        "ps" -> "Perussuomalaiset"
        "r" -> "Suomen ruotsalainen kansanpuolue"
        "kesk" -> "Suomen Keskusta"
        "vas" -> "Vasemmistoliitto"
        "kd" -> "Suomen Kristillisdemokraatit"
        "liik" -> "Liike Nyt"
        else -> "Error"
    })
}

//ParliamentApiStatus comes here as null so doesn't work
@BindingAdapter("parliamentApiStatus")
fun bindStatus(statusImageView: ImageView, status: ParliamentApiStatus?) {
    when (status) {
        ParliamentApiStatus.LOADING -> {
            Log.i("parliamentApiStatus", "Loading")
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ParliamentApiStatus.DONE -> {
            Log.i("parliamentApiStatus", "Done")
            statusImageView.visibility = View.GONE
        }
    }
}