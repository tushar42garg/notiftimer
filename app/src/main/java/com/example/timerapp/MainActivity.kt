package com.example.timerapp

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var buildButton: Button
    lateinit var playButton: Button
    lateinit var stopButton: Button
    lateinit var pauseButton: Button
    lateinit var terminateButton: Button
    lateinit var editText: EditText
    lateinit var textView: TextView

    lateinit var notiTimer: NotificationTimer.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pendingIntent = Intent(this, MainActivity::class.java).let{
            PendingIntent.getActivity(this, 0, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        buildButton = findViewById(R.id.build_btn)
        playButton = findViewById(R.id.play_btn)
        stopButton = findViewById(R.id.stop_btn)
        pauseButton = findViewById(R.id.pause_btn)
        terminateButton = findViewById(R.id.terminate_btn)
        editText = findViewById(R.id.time_editText)
        textView = findViewById(R.id.time_until_finish_text)


        buildButton.setOnClickListener {
            notiTimer = NotificationTimer.Builder(this)
                .setSmallIcon(R.drawable.ic_timer)
                .setStopButtonIcon(R.drawable.ic_baseline_stop_24)
                .setControlMode(true)
                .setShowWhen(false)
                .setAutoCancel(false)
                .setOnlyAlertOnce(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
                .setOnTickListener { textView.text = it.toString() }
                .setOnFinishListener { Toast.makeText(this, "timer finished", Toast.LENGTH_SHORT).show() }
                .setContentTitle("Timer :)")
        }

        playButton.setOnClickListener {
            notiTimer.play(editText.text.toString().toLong())
        }

        pauseButton.setOnClickListener {
            notiTimer.pause()
        }

        stopButton.setOnClickListener {
            notiTimer.stop()
            textView.text = null
        }

        terminateButton.setOnClickListener {
            notiTimer.terminate()
        }
    }


}