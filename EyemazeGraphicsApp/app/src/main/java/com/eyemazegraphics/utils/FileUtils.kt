package com.eyemazegraphics.utils

import android.content.Context
import java.io.File

object FileUtils {
    fun saveImage(context: Context, bitmap: android.graphics.Bitmap, filename: String): String? {
        val file = File(context.filesDir, filename)
        return try {
            file.outputStream().use {
                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
            }
            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun loadImage(context: Context, filename: String): android.graphics.Bitmap? {
        val file = File(context.filesDir, filename)
        return if (file.exists()) {
            try {
                android.graphics.BitmapFactory.decodeFile(file.absolutePath)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }
}
