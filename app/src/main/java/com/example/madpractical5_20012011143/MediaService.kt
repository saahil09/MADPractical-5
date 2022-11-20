package com.example.madpractical5_20012011143

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.madpractical5_20012011143.databinding.ActivityMainBinding

class MediaService : Service() {
    companion object{
        val DATA_KEY = "Service"
        val DATA_VALUE = "play/pause"
    }

    private lateinit var player : MediaPlayer
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(!this::player.isInitialized){
            player = MediaPlayer.create(this,R.raw.song)
        }
        if(intent != null){
            val str = intent.getStringExtra(DATA_KEY)
            if(str == DATA_VALUE){
                if(player.isPlaying){
                    player.pause()
                }
                else{
                    player.start()
                }
            }
        }
        else{
            player.start()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        player.stop()
        super.onDestroy()
    }
}