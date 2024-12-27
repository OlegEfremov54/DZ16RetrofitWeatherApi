package com.example.dz16retrofitweatherapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class IntroActivity : AppCompatActivity() {
    private val introList : List<Intro> = listOf(
        Intro("Привествтуем!", R.drawable.weather1),
        Intro("Отличная погода!", R.drawable.weather2),
        Intro("Можете проверить дальше!", R.drawable.weather3,
            true),
    )
    private lateinit var introVP: ViewPager2

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        introVP = findViewById(R.id.vp_intro)
        val adapter = ViewPagerIntroAdapter(this, introList)
        introVP.adapter = adapter
    }
}
