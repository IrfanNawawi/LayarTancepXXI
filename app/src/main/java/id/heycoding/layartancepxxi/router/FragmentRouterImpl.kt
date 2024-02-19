package id.heycoding.layartancepxxi.router

import androidx.fragment.app.Fragment
import id.heycoding.player.presentation.fragment.PlayerFragment
import id.heycoding.shared.router.FragmentRouter


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class FragmentRouterImpl : FragmentRouter {
    override fun createPlayerFragment(videoUrl: String): Fragment {
        return PlayerFragment.newInstance(videoUrl)
    }

}