package com.ei.android.task.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ei.android.task.R

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val url = parseParams()
        val image = findViewById<ImageView>(R.id.full_image)
        Glide
            .with(image.context)
            .load(url)
            .into(image)
    }

    private fun parseParams(): String {
        if (!intent.hasExtra(URL_EXTRA)) {
            throw RuntimeException("No params")
        }
        return intent.getStringExtra(URL_EXTRA).toString()

    }

    companion object {
        private const val URL_EXTRA = "url"
        fun newIntent(context: Context, url: String): Intent {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra(URL_EXTRA, url)
            return intent
        }
    }
}