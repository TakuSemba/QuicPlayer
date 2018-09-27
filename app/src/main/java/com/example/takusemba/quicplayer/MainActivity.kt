package com.example.takusemba.quicplayer

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.ext.cronet.CronetDataSourceFactory
import com.google.android.exoplayer2.ext.cronet.CronetEngineWrapper
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import org.chromium.net.CronetEngine
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

  private lateinit var player: ExoPlayer

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onStart() {
    super.onStart()
    val userAgent = "youUserAgent"
    val cronetEngine = CronetEngine.Builder(this).enableQuic(true).build()
    val wrapper = CronetEngineWrapper(cronetEngine)
    val executor = Executors.newSingleThreadExecutor()
    val factory = CronetDataSourceFactory(wrapper, executor, null, userAgent)

    player = ExoPlayerFactory.newSimpleInstance(this, DefaultTrackSelector())
    val playerView = findViewById<PlayerView>(R.id.player_view)
    playerView.player = player

    val uri = Uri.parse(BuildConfig.STREAMING_URL)

    if (uri == Uri.EMPTY) {
      Log.d("CRONET_SAMPLE", "uri is empty. please specify playlist url")
    }

    val mediaSource = HlsMediaSource.Factory(factory)
        .setAllowChunklessPreparation(false)
        .createMediaSource(uri)
    player.prepare(mediaSource)
    player.playWhenReady = true
  }

  override fun onStop() {
    super.onStop()
    player.release()
  }
}
