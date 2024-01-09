package id.heycoding.layartancepxxi

import android.app.Application
import id.heycoding.layartancepxxi.di.AppModules
import id.heycoding.shared.di.SharedModules
import id.heycoding.splashscreen.di.SplashScreenModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                AppModules.getModules() +
                SharedModules.getModules() +
                SplashScreenModules.getModules()
            )
        }
    }
}