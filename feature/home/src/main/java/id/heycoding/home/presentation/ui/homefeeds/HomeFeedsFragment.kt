package id.heycoding.home.presentation.ui.homefeeds

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import androidx.recyclerview.widget.SnapHelper
import id.heycoding.core.base.BaseFragment
import id.heycoding.home.R
import id.heycoding.home.databinding.FragmentHomeFeedsBinding
import id.heycoding.home.presentation.adapter.BannerAdapter
import id.heycoding.home.presentation.adapter.PopularAdapter
import id.heycoding.home.presentation.adapter.PopularAdapterClickListener
import id.heycoding.home.presentation.adapter.UpcomingAdapter
import id.heycoding.home.presentation.adapter.UpcomingAdapterClickListener
import id.heycoding.home.presentation.ui.home.HomeViewModel
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.router.BottomSheetRouter
import id.heycoding.shared.utils.ColorUtils
import id.heycoding.shared.utils.ext.subscribe
import id.heycoding.styling.ProjectColor
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.min


class HomeFeedsFragment :
    BaseFragment<FragmentHomeFeedsBinding, HomeViewModel>(FragmentHomeFeedsBinding::inflate) {
    override val viewModel: HomeViewModel by sharedViewModel()
    private val bottomSheetRouter: BottomSheetRouter by inject()
    private val snapHelper: SnapHelper = LinearSnapHelper()
    private val recyclerViewPool: RecycledViewPool by lazy {
        RecycledViewPool()
    }

    private val bannerAdapter: BannerAdapter by lazy {
        BannerAdapter()
    }

    private val popularAdapter: PopularAdapter by lazy {
        PopularAdapter(object : PopularAdapterClickListener {
            override fun onPopularMovieClicked(popularViewParam: MovieViewParam) {
                bottomSheetRouter.createMovieInfoBottomSheet(popularViewParam)
                    .show(childFragmentManager, null)
            }

        })
    }

    private val upcomingAdapter: UpcomingAdapter by lazy {
        UpcomingAdapter(object : UpcomingAdapterClickListener {
            override fun onUpcomingMovieClicked(upcomingViewParam: MovieViewParam) {
                bottomSheetRouter.createMovieInfoBottomSheet(upcomingViewParam)
                    .show(childFragmentManager, null)
            }

        })
    }

    override fun initView() {
        setupRecyclerView()
        initData()
    }

    private fun setupRecyclerView() {
        binding.layoutSectionHeader.apply {
            rvMovieBanner.apply {
                adapter = bannerAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setRecycledViewPool(recyclerViewPool)
                setHasFixedSize(true)
                snapHelper.attachToRecyclerView(rvMovieBanner)
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val scrollY: Int = rvMovieBanner.computeVerticalScrollOffset()
                        val color = ColorUtils.changeAlpha(
                            ContextCompat.getColor(requireActivity(), ProjectColor.black_transparent),
                            (min(255, scrollY).toFloat() / 255.0f).toDouble()
                        )
                        binding.clToolbarHomeFeed.setBackgroundColor(color)
                    }
                })
            }
        }
        binding.layoutSectionMovie.apply {
            rvMoviePopular.apply {
                adapter = popularAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setRecycledViewPool(recyclerViewPool)
                setHasFixedSize(true)
                snapHelper.attachToRecyclerView(rvMoviePopular)
            }
            rvMovieUpcoming.apply {
                adapter = upcomingAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setRecycledViewPool(recyclerViewPool)
                setHasFixedSize(true)
                snapHelper.attachToRecyclerView(rvMovieUpcoming)
            }
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.homePopularResult.observe(viewLifecycleOwner) {
            it.subscribe(
                doOnSuccess = { result ->
                    showLoading(false)
                    result.payload?.let { data ->
                        showTitleMovie(true)
                        bannerAdapter.setItems(data.shuffled())
                        popularAdapter.setItems(data)
                    }
                },
                doOnLoading = {
                    showLoading(true)
                    showTitleMovie(false)
                },
                doOnError = { error ->
                    showLoading(false)
                    showTitleMovie(false)
                    error.exception?.let { e -> showError(true, e) }
                }
            )
        }
        viewModel.homeUpcomingResult.observe(viewLifecycleOwner) {
            it.subscribe(
                doOnSuccess = { result ->
                    showLoading(false)
                    result.payload?.let { data ->
                        showTitleMovie(true)
                        upcomingAdapter.setItems(data.reversed())
                    }
                },
                doOnLoading = {
                    showLoading(true)
                    showTitleMovie(false)
                },
                doOnError = { error ->
                    showLoading(false)
                    showTitleMovie(false)
                    error.exception?.let { e -> showError(true, e) }
                }
            )
        }
    }

    private fun initData() {
        viewModel.fetchHomePopular()
        viewModel.fetchHomeUpcoming()
    }

    private fun showTitleMovie(isShowLoading: Boolean) {
        binding.layoutSectionMovie.apply {
            tvMoviePopular.isVisible = isShowLoading
            tvMovieUpcoming.isVisible = isShowLoading
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbHome.isVisible = isShowLoading
    }
}