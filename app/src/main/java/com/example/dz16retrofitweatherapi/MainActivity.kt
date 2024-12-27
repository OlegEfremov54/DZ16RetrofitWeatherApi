package com.example.dz16retrofitweatherapi

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar
    private lateinit var mainViewModel: MainViewModel
    private lateinit var cityTV: TextView
    private lateinit var tempTV: TextView
    private lateinit var pressureTV: TextView
    private lateinit var humidityTV: TextView
    private lateinit var windDegTV: TextView
    private lateinit var windSpeedTV: TextView
    private lateinit var iconIV: ImageView
    private lateinit var cityET: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Тулбар
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = " Погода"
        toolbarMain.subtitle = "  Версия 1. Главная страница"
        toolbarMain.setLogo(R.drawable.baseline_cloudy_snowing_24)

        cityTV = findViewById(R.id.tv_city)
        tempTV = findViewById(R.id.tv_temp)
        pressureTV = findViewById(R.id.tv_pressure)
        humidityTV = findViewById(R.id.tv_humidity)
        windDegTV = findViewById(R.id.tv_wind_deg)
        windSpeedTV = findViewById(R.id.tv_wind_speed)
        iconIV = findViewById(R.id.iv_icon)
        cityET = findViewById(R.id.et_city)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        observeMainViewModel()
        mainViewModel.getCurrentWeatherByLocation()

        findViewById<Button>(R.id.btn_update).setOnClickListener {
            mainViewModel.getCurrentWeatherByCity(cityET.text.toString().trim())
        }
    }

    private fun observeMainViewModel() {
        mainViewModel.city.observe(this) {
            cityTV.text = it
        }
        mainViewModel.temp.observe(this) {
            tempTV.text = "$it ${getString(R.string.units_deg_c)}"
        }
        mainViewModel.pressure.observe(this) {
            pressureTV.text = "$it ${getString(R.string.units_pressure)}"
        }
        mainViewModel.humidity.observe(this) {
            humidityTV.text = "${getString(R.string.tv_humidity)} $it %"
        }
        mainViewModel.windDeg.observe(this) {
            windDegTV.text = "${getString(R.string.tv_wind_deg)} $it"
        }
        mainViewModel.windSpeed.observe(this) {
            windSpeedTV.text = "$it ${getString(R.string.units_wind_speed)}"
        }
        mainViewModel.iconUrl.observe(this) {
            Picasso.get().load(it).into(iconIV)
        }
    }




    //Инициализация Меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.infoMenuMain -> {
                Toast.makeText(
                    applicationContext, "Автор Ефремов О.В. Создан 27.12.2024",
                    Toast.LENGTH_LONG
                ).show()
            }

            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}