package com.emil.quran

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var title: TextView
    private lateinit var content: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        title = findViewById(R.id.tv_title)
        content = findViewById(R.id.tv_content)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(topAppBar)
        setText(R.string.sura_1,R.string.content_sura_1)
        toggle = ActionBarDrawerToggle(this, drawerLayout, topAppBar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
               R.id.it_sura_1 -> {
                   setText(R.string.sura_1,R.string.content_sura_1)
}
                R.id.it_sura_2 -> {
                    title.text = getString(R.string.sura_2)
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun setText (t:Int, c:Int){
        title.text = getString(t)
        content.text = getString(c)
    }
    }
