package com.example.fitlife.ui.components

import android.content.Context
import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull

suspend fun prepareMultipartFromUri(context: Context, uri: Uri, partName: String = "file"): MultipartBody.Part {
    val bytes = context.contentResolver.openInputStream(uri)!!.readBytes()
    val req = RequestBody.create("image/*".toMediaTypeOrNull(), bytes)
    return MultipartBody.Part.createFormData(partName, "upload.jpg", req)
}
