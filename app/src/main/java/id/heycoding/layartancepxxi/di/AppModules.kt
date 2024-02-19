package id.heycoding.layartancepxxi.di

import id.heycoding.core.base.BaseModules
import id.heycoding.layartancepxxi.router.ActivityRouterImpl
import id.heycoding.layartancepxxi.router.BottomSheetRouterImpl
import id.heycoding.layartancepxxi.router.FragmentRouterImpl
import id.heycoding.shared.router.ActivityRouter
import id.heycoding.shared.router.BottomSheetRouter
import id.heycoding.shared.router.FragmentRouter
import org.koin.core.module.Module
import org.koin.dsl.module


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object AppModules : BaseModules {
    override fun getModules(): List<Module> = listOf(routers)

    val routers: Module = module {
        single<ActivityRouter> { ActivityRouterImpl() }
        single<BottomSheetRouter> { BottomSheetRouterImpl() }
        single<FragmentRouter> { FragmentRouterImpl() }
    }

}