package id.heycoding.core.base

import org.koin.core.module.Module


/**
 * Created by Irfan Nawawi on 03/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface BaseModules {
    fun getModules(): List<Module>
}

interface FeatureModules : BaseModules {
    val repositories: Module
    val viewModels: Module
    val dataSources: Module
    val useCases: Module
    val network: Module
}