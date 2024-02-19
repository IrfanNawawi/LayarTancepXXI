package id.heycoding.layartancepxxi.router

import android.content.Context
import android.content.Intent
import id.heycoding.detailmovie.presentation.ui.detailmovie.DetailMovieActivity
import id.heycoding.home.presentation.ui.home.HomeActivity
import id.heycoding.login.presentation.ui.LoginActivity
import id.heycoding.player.presentation.activity.PlayerActivity
import id.heycoding.register.presentation.ui.RegisterActivity
import id.heycoding.shared.router.ActivityRouter


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class ActivityRouterImpl : ActivityRouter {
    override fun loginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    override fun homeActivity(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }

    override fun registerActivity(context: Context): Intent {
        return Intent(context, RegisterActivity::class.java)
    }

    override fun detailMovieActivity(context: Context, movieId: String): Intent {
        return DetailMovieActivity.createIntent(context, movieId)
    }

    override fun playerActivity(context: Context, videoUrl: String): Intent {
        return PlayerActivity.createIntent(context, videoUrl)
    }


}