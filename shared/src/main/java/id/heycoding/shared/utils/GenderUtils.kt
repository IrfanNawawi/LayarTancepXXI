package id.heycoding.shared.utils

import android.content.Context
import android.widget.ArrayAdapter
import id.heycoding.styling.ProjectArray
import id.heycoding.styling.ProjectLayout


/**
 * Created by Irfan Nawawi on 07/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object GenderUtils {
    fun createGenderListAdapter(context: Context): ArrayAdapter<String> {
        val genders =
            context.resources.getStringArray(ProjectArray.gender_list)
        return ArrayAdapter(context, ProjectLayout.simple_list_item, genders)
    }

    fun parseGender(genderText: String): String {
        return if (genderText == Gender.MALE_OPTION) Gender.MALE else Gender.FEMALE
    }
}

object Gender {
    const val MALE = "0"
    const val MALE_OPTION = "Male"
    const val FEMALE = "1"
    const val FEMALE_OPTION = "Female"
}