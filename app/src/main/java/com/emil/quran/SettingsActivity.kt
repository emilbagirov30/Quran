package com.emil.quran

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var language: String
    private lateinit var apply: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        apply = findViewById(R.id.bt_apply)
        sharedPref = getSharedPreferences("appData", Context.MODE_PRIVATE)
        language = sharedPref.getString("language", "en").toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val spinner = findViewById<Spinner>(R.id.language_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.languages_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
apply.setOnClickListener {
    restartApp()
}

        val languages = resources.getStringArray(R.array.languages_array)
        val defaultLanguageIndex = when (language) {
            "ru" -> languages.indexOf("Русский")
            "en" -> languages.indexOf("English")
            else -> 0
        }
        spinner.setSelection(defaultLanguageIndex)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedLanguage = when (position) {
                   1 -> "en"
                    0 -> "ru"
                    else -> "en"
                }
                with(sharedPref.edit()) {
                    putString("language", selectedLanguage)
                    apply()

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val switchingToMainActivity= Intent(this, MainActivity::class.java)
        startActivity(switchingToMainActivity)
        return true
    }
    fun restartApp() {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        val componentName = intent?.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }
}
