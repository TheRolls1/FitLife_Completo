package com.example.fitlife.viewmodel


import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class ProfileViewModel : ViewModel() {
    // Holds the URI of the selected image
    val imageUri = mutableStateOf<Uri?>(null)


    fun onImageSelected(uri: Uri?) {
        imageUri.value = uri
    }
}
