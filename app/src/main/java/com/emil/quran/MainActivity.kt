package com.emil.quran

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.text.SpannableString
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
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
    private lateinit var sharedPref: SharedPreferences
    private lateinit var scrollView: ScrollView
    private lateinit var language: String

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
        scrollView = findViewById(R.id.sv)
        bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        progressBar = bottomSheetView.findViewById(R.id.progress_bar)
        bottomSheetTitle = bottomSheetView.findViewById(R.id.bottom_sheet_title)
        bottomSheetDialog.setContentView(bottomSheetView)
        sharedPref = getSharedPreferences("appData", Context.MODE_PRIVATE)
        language = sharedPref.getString("language", "null").toString()
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
                R.id.it_sura_1 -> updateSura(1)
                R.id.it_sura_2 -> updateSura(2)
                R.id.it_sura_3 -> updateSura(3)
                R.id.it_sura_4 -> updateSura(4)
                R.id.it_sura_5 -> updateSura(5)
                R.id.it_sura_6 -> updateSura(6)
                R.id.it_sura_7 -> updateSura(7)
                R.id.it_sura_8 -> updateSura(8)
                R.id.it_sura_9 -> updateSura(9)
                R.id.it_sura_10 -> updateSura(10)
                R.id.it_sura_11 -> updateSura(11)
                R.id.it_sura_12 -> updateSura(12)
                R.id.it_sura_13 -> updateSura(13)
                R.id.it_sura_14 -> updateSura(14)
                R.id.it_sura_15 -> updateSura(15)
                R.id.it_sura_16 -> updateSura(16)
                R.id.it_sura_17 -> updateSura(17)
                R.id.it_sura_18 -> updateSura(18)
                R.id.it_sura_19 -> updateSura(19)
                R.id.it_sura_20 -> updateSura(20)
                R.id.it_sura_21 -> updateSura(21)
                R.id.it_sura_22 -> updateSura(22)
                R.id.it_sura_23 -> updateSura(23)
                R.id.it_sura_24 -> updateSura(24)
                R.id.it_sura_25 -> updateSura(25)
                R.id.it_sura_26 -> updateSura(26)
                R.id.it_sura_27 -> updateSura(27)
                R.id.it_sura_28 -> updateSura(28)
                R.id.it_sura_29 -> updateSura(29)
                R.id.it_sura_30 -> updateSura(30)
                R.id.it_sura_31 -> updateSura(31)
                R.id.it_sura_32 -> updateSura(32)
                R.id.it_sura_33 -> updateSura(33)
                R.id.it_sura_34 -> updateSura(34)
                R.id.it_sura_35 -> updateSura(35)
                R.id.it_sura_36 -> updateSura(36)
                R.id.it_sura_37 -> updateSura(37)
                R.id.it_sura_38 -> updateSura(38)
                R.id.it_sura_39 -> updateSura(39)
                R.id.it_sura_40 -> updateSura(40)
                R.id.it_sura_41 -> updateSura(41)
                R.id.it_sura_42 -> updateSura(42)
                R.id.it_sura_43 -> updateSura(43)
                R.id.it_sura_44 -> updateSura(44)
                R.id.it_sura_45 -> updateSura(45)
                R.id.it_sura_46 -> updateSura(46)
                R.id.it_sura_47 -> updateSura(47)
                R.id.it_sura_48 -> updateSura(48)
                R.id.it_sura_49 -> updateSura(49)
                R.id.it_sura_50 -> updateSura(50)
                R.id.it_sura_51 -> updateSura(51)
                R.id.it_sura_52 -> updateSura(52)
                R.id.it_sura_53 -> updateSura(53)
                R.id.it_sura_54 -> updateSura(54)
                R.id.it_sura_55 -> updateSura(55)
                R.id.it_sura_56 -> updateSura(56)
                R.id.it_sura_57 -> updateSura(57)
                R.id.it_sura_58 -> updateSura(58)
                R.id.it_sura_59 -> updateSura(59)
                R.id.it_sura_60 -> updateSura(60)
                R.id.it_sura_61 -> updateSura(61)
                R.id.it_sura_62 -> updateSura(62)
                R.id.it_sura_63 -> updateSura(63)
                R.id.it_sura_64 -> updateSura(64)
                R.id.it_sura_65 -> updateSura(65)
                R.id.it_sura_66 -> updateSura(66)
                R.id.it_sura_67 -> updateSura(67)
                R.id.it_sura_68 -> updateSura(68)
                R.id.it_sura_69 -> updateSura(69)
                R.id.it_sura_70 -> updateSura(70)
                R.id.it_sura_71 -> updateSura(71)
                R.id.it_sura_72 -> updateSura(72)
                R.id.it_sura_73 -> updateSura(73)
                R.id.it_sura_74 -> updateSura(74)
                R.id.it_sura_75 -> updateSura(75)
                R.id.it_sura_76 -> updateSura(76)
                R.id.it_sura_77 -> updateSura(77)
                R.id.it_sura_78 -> updateSura(78)
                R.id.it_sura_79 -> updateSura(79)
                R.id.it_sura_80 -> updateSura(80)
                R.id.it_sura_81 -> updateSura(81)
                R.id.it_sura_82 -> updateSura(82)
                R.id.it_sura_83 -> updateSura(83)
                R.id.it_sura_84 -> updateSura(84)
                R.id.it_sura_85 -> updateSura(85)
                R.id.it_sura_86 -> updateSura(86)
                R.id.it_sura_87 -> updateSura(87)
                R.id.it_sura_88 -> updateSura(88)
                R.id.it_sura_89 -> updateSura(89)
                R.id.it_sura_90 -> updateSura(90)
                R.id.it_sura_91 -> updateSura(91)
                R.id.it_sura_92 -> updateSura(92)
                R.id.it_sura_93 -> updateSura(93)
                R.id.it_sura_94 -> updateSura(94)
                R.id.it_sura_95 -> updateSura(95)
                R.id.it_sura_96 -> updateSura(96)
                R.id.it_sura_97 -> updateSura(97)
                R.id.it_sura_98 -> updateSura(98)
                R.id.it_sura_99 -> updateSura(99)
                R.id.it_sura_100 -> updateSura(100)
                R.id.it_sura_101 -> updateSura(101)
                R.id.it_sura_102 -> updateSura(102)
                R.id.it_sura_103 -> updateSura(103)
                R.id.it_sura_104 -> updateSura(104)
                R.id.it_sura_105 -> updateSura(105)
                R.id.it_sura_106 -> updateSura(106)
                R.id.it_sura_107 -> updateSura(107)
                R.id.it_sura_108 -> updateSura(108)
                R.id.it_sura_109 -> updateSura(109)
                R.id.it_sura_110 -> updateSura(110)
                R.id.it_sura_111 -> updateSura(111)
                R.id.it_sura_112 -> updateSura(112)
                R.id.it_sura_113 -> updateSura(113)
                R.id.it_sura_114 -> updateSura(114)
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun updateSura(suraNumber: Int) {
        currentSura = suraNumber
        if (suraNumber>1) back.visibility= View.VISIBLE
        else back.visibility= View.GONE


        if (suraNumber==114) next.visibility= View.GONE
        else next.visibility= View.VISIBLE


        val titleResId = resources.getIdentifier("sura_$suraNumber", "string", packageName)
        val audioResId = resources.getIdentifier("s$suraNumber", "raw", packageName)



        if (::mediaPlayer.isInitialized) {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                play.setBackgroundResource(R.drawable.play)
            }
            mediaPlayer.reset()
        }

        title.text = getString(titleResId)
        bottomSheetTitle.text = getString(titleResId)
        pausePosition = 0
        loadTextFromFile("sura_${suraNumber}_${language}.txt")
        mediaPlayer = MediaPlayer.create(this, audioResId)
        scrollView.scrollTo(0, 0)
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
    private fun loadTextFromFile(fileName: String) {
        try {
            val inputStream = assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val text = reader.readText()
            content.text = text
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                val switchingToSettingsActivity= Intent(this,SettingsActivity::class.java)
                startActivity(switchingToSettingsActivity)
                finish()
                true
            }
            R.id.Names -> {
                val switchingToNamesActivity= Intent(this,NamesActivity::class.java)
                startActivity(switchingToNamesActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
