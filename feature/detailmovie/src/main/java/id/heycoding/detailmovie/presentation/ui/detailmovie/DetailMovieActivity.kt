package id.heycoding.detailmovie.presentation.ui.detailmovie

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.view.isVisible
import coil.load
import id.heycoding.core.BuildConfig
import id.heycoding.core.base.BaseActivity
import id.heycoding.detailmovie.data.remote.model.viewparam.DetailMovieViewParam
import id.heycoding.detailmovie.data.remote.model.viewparam.VideoViewParam
import id.heycoding.detailmovie.databinding.ActivityDetailMovieBinding
import id.heycoding.shared.router.ActivityRouter
import id.heycoding.shared.router.FragmentRouter
import id.heycoding.shared.utils.CommonUtils
import id.heycoding.shared.utils.ext.subscribe
import org.koin.android.ext.android.inject


/**
 * Created by Irfan Nawawi on 12/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class DetailMovieActivity :
    BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel>(ActivityDetailMovieBinding::inflate) {

    override val viewModel: DetailMovieViewModel by inject()
    private val activityRouter: ActivityRouter by inject()
    private val fragmentRouter: FragmentRouter by inject()
    private val movieId: String? by lazy { intent?.extras?.getString(EXTRA_ID_MOVIE) }

    companion object {
        private const val EXTRA_ID_MOVIE = "EXTRA_ID_MOVIE"
        fun createIntent(context: Context, movieId: String): Intent {
            return Intent(context, DetailMovieActivity::class.java).apply {
                putExtra(EXTRA_ID_MOVIE, movieId)
            }
        }
    }

    override fun initView() {
        setupToolbar()
        movieId?.let {
            viewModel.fetchDetailMovie(it)
            viewModel.fetchVideoMovie(it)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        enableHomeAsBack()
    }

    override fun observeData() {
        super.observeData()
        viewModel.detailMovieResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    showLoading(false)
                    result.payload?.let { movie -> bindMovie(movie) }
                },
                doOnLoading = {
                    showLoading(true)
                },
                doOnError = { error ->
                    showLoading(false)
                    error.exception?.let { e -> showError(true, e) }
                }
            )
        }
        viewModel.videoMovieResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    showLoading(false)
                    result.payload?.let { movie -> bindVideo(movie) }
                },
                doOnLoading = {
                    showLoading(true)
                },
                doOnError = { error ->
                    showLoading(false)
                    error.exception?.let { e -> showError(true, e) }
                }
            )
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbDetail.isVisible = isShowLoading
        binding.layoutDetail.layoutDetail.isVisible = !isShowLoading
    }

    private fun bindMovie(movie: DetailMovieViewParam) {
        loadInfoMovie(movie)
        loadPoster(movie.backdrop_path)
        setClickListener(movie)
    }

    private fun bindVideo(video: List<VideoViewParam>) {
        binding.layoutDetail.layoutHeaderDetail.apply {
            ivPlayTrailer.setOnClickListener {
                flHeaderPoster.isVisible = false
                containerPlayer.isVisible = true
                supportFragmentManager.beginTransaction().apply {
                    replace(containerPlayer.id, fragmentRouter.createPlayerFragment("https://www.youtube.com/watch?v=${video.elementAt(0).key}"))
                }
            }
        }
        binding.layoutDetail.clDetailMovie.cvPlay.setOnClickListener {
            startActivity(activityRouter.playerActivity(this, "https://www.youtube.com/watch?v=${video.elementAt(0).key}"))
        }
    }

    private fun loadInfoMovie(movie: DetailMovieViewParam) {
        binding.layoutDetail.clDetailMovie.apply {
            tvTitleMovie.text = movie.title
            tvMovieDesc.text = movie.overview
            tvAdditionalInfo.text =
                "${movie.release_date} • ${movie.vote_count} • ${movie.runtime}m "
        }
    }

    private fun loadPoster(url: String) {
        val poster: String =
            BuildConfig.BASE_URL_IMAGE + "w300" + url
        binding.layoutDetail.layoutHeaderDetail.ivPosterDetail.load(poster)
    }

    private fun setClickListener(movie: DetailMovieViewParam) {
        binding.layoutDetail.clDetailMovie.apply {
            llShare.setOnClickListener {
                CommonUtils.shareFilm(this@DetailMovieActivity, movie.title, movie.poster_path)
            }
            llMyList.setOnClickListener {

            }
        }
    }

}