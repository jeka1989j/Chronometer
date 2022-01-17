package com.example.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import com.example.chronometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var chronometer: Chronometer
    private val elapsedTime = SystemClock.elapsedRealtime()
    private var offset = 0L
    var startTime = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        chronometer = binding.chronometer

        if (savedInstanceState != null) {
            chronometer.base = savedInstanceState.getLong("chronometer")
            chronometer.start()
        }

        binding.startBtn.setOnClickListener {
            startTimer()
        }

        binding.stopBtn.setOnClickListener {
            stopTimer()
        }
    }

    private fun startTimer() {
//        chronometer.base = elapsedTime
        chronometer.start()

        binding.startBtn.isEnabled = false
        binding.stopBtn.isEnabled = true
    }

    private fun stopTimer() {
        offset = elapsedTime - chronometer.base
        chronometer.stop()

        binding.stopBtn.isEnabled = false
        binding.startBtn.isEnabled = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("chronometer", chronometer.base)
    }
}