package com.emil.quran

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LanguageSelectionActivity : AppCompatActivity() {
    private lateinit var rus: ImageView
    private lateinit var eng: ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_selection)
        rus = findViewById(R.id.iv_rus)
        eng = findViewById(R.id.iv_eng)

       rus.setOnClickListener {
            selectImage(it)
        }

        eng.setOnClickListener {
            selectImage(it)
        }

    }

    private fun selectImage(selectedImageView: View) {
       rus.isSelected = false
       eng.isSelected = false

        selectedImageView.isSelected = true

        // Обработка выбранного изображения
        if (selectedImageView == rus) {
            // Выбрано первое изображение
        } else if (selectedImageView == eng) {
            // Выбрано второе изображение
        }
    }
}