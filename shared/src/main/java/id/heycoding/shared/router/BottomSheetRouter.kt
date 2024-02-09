package id.heycoding.shared.router

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.heycoding.shared.data.model.viewparam.MovieViewParam


/**
 * Created by Irfan Nawawi on 09/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface BottomSheetRouter {
    fun createMovieInfoBottomSheet(movieViewParam: MovieViewParam): BottomSheetDialogFragment
}