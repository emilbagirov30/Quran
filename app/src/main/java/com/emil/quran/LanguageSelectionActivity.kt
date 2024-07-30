package com.emil.quran

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LanguageSelectionActivity : AppCompatActivity() {
    private lateinit var rus: ImageView
    private lateinit var eng: ImageView
    private lateinit var next: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_selection)
        rus = findViewById(R.id.iv_rus)
        eng = findViewById(R.id.iv_eng)
        next = findViewById(R.id.bt_next)

       rus.setOnClickListener {
            selectImage(it)
        }

        eng.setOnClickListener {
            selectImage(it)
        }
        next.setOnClickListener {
            val switchingToMainActivity= Intent(this, MainActivity::class.java)
            startActivity(switchingToMainActivity)
            finish()
        }

    }

    private fun selectImage(selectedImageView: View) {
       rus.isSelected = false
       eng.isSelected = false
       next.visibility = View.VISIBLE
        selectedImageView.isSelected = true

        if (selectedImageView == rus) {
            ManagingSettings.setLocale(this@LanguageSelectionActivity, "ru");
        } else{
            ManagingSettings.setLocale(this@LanguageSelectionActivity, "en");
        }
    }
}