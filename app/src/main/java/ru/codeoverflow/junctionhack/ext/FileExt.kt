package ru.codeoverflow.junctionhack.ext

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun File.toFilePart(name: String = "file"): MultipartBody.Part {
    val fileReqBody = this.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(name, this.name, fileReqBody)
}