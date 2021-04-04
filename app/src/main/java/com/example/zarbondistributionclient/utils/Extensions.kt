package com.example.zarbondistributionclient.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun Context.showToast(st: String) {
    Toast.makeText(this, st, Toast.LENGTH_SHORT).show()
}

fun ViewPager.pageChangeListener(f: (Int) -> Unit) =
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            f.invoke(position)
        }
    })


fun log(message : String, tag : String = "TTT"){
    Log.d(tag,message)
}


fun File.toRequestData(): MultipartBody.Part {
    val fileReqBody = this.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("file", name, fileReqBody)
}
