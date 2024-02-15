package id.heycoding.detailmovie.presentation.ui.movieinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.heycoding.core.BuildConfig
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.heycoding.shared.data.remote.model.viewparam.MovieViewParam
import id.heycoding.shared.router.ActivityRouter
import id.heycoding.shared.utils.CommonUtils
import id.heycoding.styling.databinding.BottomSheetMovieInfoBinding
import org.koin.android.ext.android.inject


/**
 * Created by Irfan Nawawi on 09/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class MovieInfoBottomSheet(private val movie: MovieViewParam) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetMovieInfoBinding
    private val activityRouter: ActivityRouter by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetMovieInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindMovie(movie)
        observeData()
    }

    private fun bindMovie(movie: MovieViewParam) {
        setClickListener(movie)
        val poster: String =
            BuildConfig.BASE_URL_IMAGE + "w500" + movie.posterPath
        loadPoster(poster)
        loadInfoMovie(movie)
    }

    private fun loadInfoMovie(movie: MovieViewParam) {
        binding.tvMovieTitle.text = movie.title
        binding.tvShortDesc.text = movie.overview
        binding.tvAdditionalInfo.text =
            "${movie.releaseDate} • ${movie.popularity} • ${movie.voteAverage}"
//        binding.ivWatchlist.setImageResource(CommonUtils.getWatchlistIcon(movie.isUserWatchlist))
    }

    private fun loadPoster(url: String) {
        binding.ivPoster.load(url)
    }

    private fun setClickListener(movie: MovieViewParam) {
        binding.ivClose.setOnClickListener {
            this.dismiss()
        }
        binding.llPlayMovie.setOnClickListener {
//            startActivity(activityRouter.playerActivity(requireContext(),movie.videoUrl))
        }
        binding.llShare.setOnClickListener {
            CommonUtils.shareFilm(requireContext(), movie.title, movie.posterPath)
        }
        binding.llMyList.setOnClickListener {
//            viewModel.addOrRemoveWatchlist(movie)
        }
        binding.tvDetailMovie.setOnClickListener {
            startActivity(
                activityRouter.detailMovieActivity(
                    requireContext(), movie.id.toString()
                )
            )
        }
    }

    private fun observeData() {
//        viewModel.getWatchlistResult().observe(this) {
//            it.subscribe(
//                doOnSuccess = { result ->
//                    result.payload?.let { movie ->
//                        movie.isUserWatchlist = movie.isUserWatchlist
//                        binding.ivWatchlist.setImageResource(CommonUtils.getWatchlistIcon(movie.isUserWatchlist))
//                    }
//                },
//                doOnError = { error ->
//                    Toast.makeText(requireContext(), error.message.orEmpty(), Toast.LENGTH_SHORT).show()
//                }
//            )
//        }
    }

}