package com.example.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import com.example.chronometer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var chronometer: Chronometer
    private var offset: Long = 0
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

        binding.resetBtn.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        chronometer.base = SystemClock.elapsedRealtime() - offset
        chronometer.start()
        binding.startBtn.isEnabled = false
        binding.stopBtn.isEnabled = true
        binding.resetBtn.isEnabled = false
        startTime = true
    }

    private fun stopTimer() {
        offset = SystemClock.elapsedRealtime() - chronometer.base
        chronometer.stop()
        startTime = false
        binding.stopBtn.isEnabled = false
        binding.startBtn.isEnabled = true
        binding.resetBtn.isEnabled = true
    }

    private fun resetTimer() {
        offset = 0
        chronometer.base = SystemClock.elapsedRealtime()
        binding.resetBtn.isEnabled = false
        binding.stopBtn.isEnabled = false
        binding.startBtn.isEnabled = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("chronometer", chronometer.base)
    }
}