package com.example.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.chronometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val chronometer = SystemClock.elapsedRealtime()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       binding.start.setOnClickListener {
           updateButton()
       }
    }

    fun updateButton() {
        val nowSeconds = SystemClock.elapsedRealtime()
        val chronometerResult = getString(R.string.chronometer_text,
            (nowSeconds - chronometer) /1000)
        binding.chronometer.text = chronometerResult
    }
}