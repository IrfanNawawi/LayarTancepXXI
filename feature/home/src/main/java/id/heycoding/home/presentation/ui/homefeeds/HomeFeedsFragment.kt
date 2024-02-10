package id.heycoding.home.presentation.ui.homefeeds

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import androidx.recyclerview.widget.SnapHelper
import id.heycoding.core.base.BaseFragment
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import id.heycoding.home.databinding.FragmentHomeFeedsBinding
import id.heycoding.home.presentation.adapter.HomeAdapter
import id.heycoding.home.presentation.adapter.HomeAdapterClickListener
import id.heycoding.home.presentation.ui.home.HomeViewModel
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.router.BottomSheetRouter
import id.heycoding.shared.utils.ColorUtils
import id.heycoding.shared.utils.Const
import id.heycoding.shared.utils.ext.subscribe
import id.heycoding.styling.ProjectColor
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.min


class HomeFeedsFragment :
    BaseFragment<FragmentHomeFeedsBinding, HomeViewModel>(FragmentHomeFeedsBinding::inflate) {
    override val viewModel: HomeViewModel by sharedViewModel()
    private val bottomSheetRouter: BottomSheetRouter by inject()
    private val snapHelper: SnapHelper = LinearSnapHelper()
    private val homeUiItem = mutableListOf<HomeUiItem>()
    private val recyclerViewPool: RecycledViewPool by lazy {
        RecycledViewPool()
    }

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(object : HomeAdapterClickListener{
            override fun onMyListClicked(movieViewParam: MovieViewParam) {
                TODO("Not yet implemented")
            }

            override fun onPlayMovieClicked(movieViewParam: MovieViewParam) {
                TODO("Not yet implemented")
            }

            override fun onMovieClicked(movieViewParam: MovieViewParam) {
                TODO("Not yet implemented")
            }

        }, recyclerViewPool)
    }

    override fun initView() {
        setupRecyclerView()
        initData()
    }

    private fun setupRecyclerView() {
        binding.rvHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setRecycledViewPool(recycledViewPool)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val scrollY: Int = binding.rvHome.computeVerticalScrollOffset()
                    val color = ColorUtils.changeAlpha(
                        ContextCompat.getColor(requireActivity(), ProjectColor.black_transparent),
                        (min(255, scrollY).toFloat() / 255.0f).toDouble()
                    )
                    binding.clToolbarHomeFeed.setBackgroundColor(color)
                }
            })
        }
    }

    override fun observeData() {
        super.observeData()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homePopularResult.observe(viewLifecycleOwner) {
                    it.subscribe(
                        doOnSuccess = { result ->
                            showLoading(false)
                            result.payload?.let { data ->
                                homeAdapter.submitList(data)
                            }
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
                viewModel.homeUpcomingResult.observe(viewLifecycleOwner) {
                    it.subscribe(
                        doOnSuccess = { result ->
                            showLoading(false)
                            result.payload?.let { data ->
                                homeAdapter.submitList(data)
                            }
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
        }

    }

    private fun initData() {
        viewModel.fetchHomePopular()
        viewModel.fetchHomeUpcoming()
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbHome.isVisible = isShowLoading
    }
}