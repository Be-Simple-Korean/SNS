package com.example.data.retrofit

import android.net.Uri
import android.util.Log
import com.example.domain.usecase.file.GetInputStreamUseCase
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source
import java.io.FileNotFoundException

class UriRequestBody constructor(
    val contentUri: String,
    val contentType: MediaType? = null,
    val getInputStreamUseCase: GetInputStreamUseCase,
    val contentLength: Long,
) : RequestBody() {
    override fun contentType(): MediaType? {
        return contentType
    }

    override fun contentLength(): Long {
        return contentLength
    }

    override fun writeTo(sink: BufferedSink) {
        try {
            getInputStreamUseCase(
                contentUri = contentUri
            )
                .getOrThrow()
                .use { inputStream ->
                    sink.writeAll(inputStream.source())
                }
        } catch (e: FileNotFoundException) {
            Log.e("UriRequestBody", e.message.orEmpty())
        }
    }
}