package id.heycoding.player.manager

import android.os.Build
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlin.math.max


/**
 * Created by Irfan Nawawi on 19/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class ExoPlayerManagerImpl(private val playerView: StyledPlayerView): ExoPlayerManager {
    private var player : ExoPlayer? = null
    private var startAutoPlay = false
    private var startItemIndex = 0
    private var startPosition: Long = 0L
    private var currentMediaItem : MediaItem? = null
    override fun play(videoUrl: String) {
        playPlayer(MediaItem.Builder().setUri(videoUrl).build(), false)
    }

    private fun playPlayer(mediaItem: MediaItem?, haveStartPosition: Boolean = false) {
        currentMediaItem = mediaItem
        currentMediaItem?.let {
            player?.playWhenReady = startAutoPlay
            player?.setMediaItem(it, !haveStartPosition)
            player?.prepare()
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        clearIndex()
    }

    private fun clearIndex() {
        startAutoPlay = true
        startItemIndex = C.INDEX_UNSET
        startPosition = C.TIME_UNSET
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        if (Build.VERSION.SDK_INT > 23) {
            initializePlayer()
            playerView.onResume()
        }
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(playerView.context).build().also {
            playerView.player = it
        }
        //seek when there's current position when back
        val haveStartPosition = startItemIndex != C.INDEX_UNSET
        if (haveStartPosition) {
            player?.seekTo(startItemIndex, startPosition)
        }
        playPlayer(currentMediaItem, haveStartPosition)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        if (Build.VERSION.SDK_INT > 23) {
            playerView.onPause()
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.let {
            updateIndex()
            it.release()
            player = null
            playerView.player = null
        }
    }

    private fun updateIndex() {
        player?.let {
            startAutoPlay = it.playWhenReady
            startItemIndex = it.currentMediaItemIndex
            startPosition = max(0, it.contentPosition)
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        if (Build.VERSION.SDK_INT > 23) {
            playerView.onPause()
            releasePlayer()
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (Build.VERSION.SDK_INT > 23 || player == null) {
            initializePlayer()
            playerView.onResume()
        }
    }
}