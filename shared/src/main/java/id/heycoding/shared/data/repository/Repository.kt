package id.heycoding.shared.data.repository

import com.google.gson.Gson
import com.google.gson.JsonParseException
import id.heycoding.core.base.BaseRepository
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent.inject


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
open class Repository : BaseRepository() {
    private val gson: Gson by inject(Gson::class.java)
    override fun <T> getErrorMessageFromApi(response: T): String {
        val responseBody = response as ResponseBody
        return try {
            "Error Api"
        } catch (e: JsonParseException) {
            "Error Api"
        }
    }
}