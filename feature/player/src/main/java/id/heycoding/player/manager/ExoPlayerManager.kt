package id.heycoding.player.manager

import androidx.lifecycle.DefaultLifecycleObserver


/**
 * Created by Irfan Nawawi on 19/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface ExoPlayerManager : DefaultLifecycleObserver {
    fun play(videoUrl: String)
}