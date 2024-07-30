package com.emil.quran

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var title: TextView
    private lateinit var content: TextView
    private lateinit var play: ImageButton
    private lateinit var next: ImageButton
    private lateinit var back: ImageButton
    private lateinit var mediaPlayer: MediaPlayer
    private var pausePosition: Int = 0
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var progressBar: ProgressBar
    private lateinit var bottomSheetTitle: TextView
    private var currentSura = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        title = findViewById(R.id.tv_title)
        content = findViewById(R.id.tv_content)
        play = findViewById(R.id.ib_play)
        next = findViewById(R.id.ib_next)
        back = findViewById(R.id.ib_back)
        bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        progressBar = bottomSheetView.findViewById(R.id.progress_bar)
        bottomSheetTitle = bottomSheetView.findViewById(R.id.bottom_sheet_title)
        bottomSheetDialog.setContentView(bottomSheetView)

        mediaPlayer = MediaPlayer()

        updateSura(currentSura)

        next.setOnClickListener {
            currentSura++
            updateSura(currentSura)
        }
        back.setOnClickListener {
            currentSura--
            updateSura(currentSura)
        }

        play.setOnClickListener {
            mediaPlayer.setOnCompletionListener {
                play.setBackgroundResource(R.drawable.play)
                pausePosition = 0
                bottomSheetDialog.dismiss()
            }
            if (mediaPlayer.isPlaying) {
                play.setBackgroundResource(R.drawable.play)
                mediaPlayer.pause()
                pausePosition = mediaPlayer.currentPosition
            } else {
                play.setBackgroundResource(R.drawable.pause)
                mediaPlayer.seekTo(pausePosition)
                mediaPlayer.start()
                bottomSheetDialog.show()
                startProgressBarUpdater()
            }
        }

        val navView: NavigationView = findViewById(R.id.nav_view)
        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(topAppBar)
        toggle = ActionBarDrawerToggle(this, drawerLayout, topAppBar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.it_sura_1 -> {
                    currentSura = 1
                    updateSura(currentSura)
                }
                R.id.it_sura_2 -> {
                    currentSura = 2
                    updateSura(currentSura)
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun updateSura(suraNumber: Int) {
        if (suraNumber>1) back.visibility= View.VISIBLE
        else back.visibility= View.GONE


        if (suraNumber==114) next.visibility= View.GONE
        else next.visibility= View.VISIBLE


        val titleResId = resources.getIdentifier("sura_$suraNumber", "string", packageName)
        val contentResId = resources.getIdentifier("content_sura_$suraNumber", "string", packageName)
        val audioResId = resources.getIdentifier("sura_$suraNumber", "raw", packageName)

        if (::mediaPlayer.isInitialized) {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                play.setBackgroundResource(R.drawable.play)
            }
            mediaPlayer.reset()
        }

        title.text = getString(titleResId)
        content.text = getString(contentResId)
        bottomSheetTitle.text = getString(titleResId)
        pausePosition = 0

        mediaPlayer = MediaPlayer.create(this, audioResId)
    }

    private fun startProgressBarUpdater() {
        progressBar.max = mediaPlayer.duration
        fixedRateTimer("timer", false, 0L, 1000) {
            runOnUiThread {
                if (mediaPlayer.isPlaying) {
                    progressBar.progress = mediaPlayer.currentPosition
                }
            }
        }
    }
}
