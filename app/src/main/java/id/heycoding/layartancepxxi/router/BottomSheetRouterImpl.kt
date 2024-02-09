package id.heycoding.layartancepxxi.router

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.heycoding.detailmovie.presentation.ui.movieinfo.MovieInfoBottomSheet
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.router.BottomSheetRouter


/**
 * Created by Irfan Nawawi on 09/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class BottomSheetRouterImpl : BottomSheetRouter {
    override fun createMovieInfoBottomSheet(movieViewParam: MovieViewParam): BottomSheetDialogFragment {
        return MovieInfoBottomSheet(movieViewParam)
    }
}