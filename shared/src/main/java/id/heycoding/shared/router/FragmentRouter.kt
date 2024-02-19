package id.heycoding.shared.router

import androidx.fragment.app.Fragment


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface FragmentRouter {
    fun createPlayerFragment(videoUrl: String): Fragment
}