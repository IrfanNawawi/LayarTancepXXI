package id.heycoding.shared.data.model.mapper

import id.heycoding.shared.data.model.response.UserResponse
import id.heycoding.shared.data.model.viewparam.UserViewParam
import id.heycoding.shared.utils.mapper.ViewParamMapper


/**
 * Created by Irfan Nawawi on 05/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object UserMapper : ViewParamMapper<UserResponse, UserViewParam> {
    override fun toViewParam(dataObject: UserResponse?): UserViewParam = UserViewParam(
        email = dataObject?.email.orEmpty(),
        birthdate = dataObject?.birthdate.orEmpty(),
        gender = dataObject?.gender ?: -1,
        id = dataObject?.id ?: -1,
        username = dataObject?.username.orEmpty()
    )

}