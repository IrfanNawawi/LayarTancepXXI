package id.heycoding.home.presentation.ui.homefeeds

import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import id.heycoding.core.base.BaseFragment
import id.heycoding.home.databinding.FragmentHomeFeedsBinding
import id.heycoding.home.presentation.adapter.HomeAdapterClickListener
import id.heycoding.home.presentation.adapter.HomeFeedsAdapter
import id.heycoding.home.presentation.ui.home.HomeViewModel
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.shared.utils.ColorUtils
import id.heycoding.shared.utils.ext.subscribe
import id.heycoding.shared.utils.textdrawable.ColorGenerator
import id.heycoding.shared.utils.textdrawable.TextDrawable
import id.heycoding.styling.ProjectColor
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.min


class HomeFeedsFragment :
    BaseFragment<FragmentHomeFeedsBinding, HomeViewModel>(FragmentHomeFeedsBinding::inflate) {
    override val viewModel: HomeViewModel by sharedViewModel()
    private val recyclerViewPool: RecycledViewPool by lazy {
        RecycledViewPool()
    }
    private val homeFeedsAdapter: HomeFeedsAdapter by lazy {
        HomeFeedsAdapter(object : HomeAdapterClickListener {
            override fun onMyListClicked(movieViewParam: MovieViewParam) {
                // TODO : Add to Watchlist
            }

            override fun onPlayMovieClicked(movieViewParam: MovieViewParam) {
                // TODO : Handle click item
            }

            override fun onMovieClicked(movieViewParam: MovieViewParam) {
                // TODO : On Movie Clicked
            }
        }, recyclerViewPool)
    }

    private fun setupRecyclerView() {
        binding.rvHome.apply {
            adapter = homeFeedsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setRecycledViewPool(recyclerViewPool)
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

    override fun initView() {
        setupRecyclerView()
        initData()
    }

    override fun observeData() {
        super.observeData()
        viewModel.homeFeedsResult.observe(viewLifecycleOwner) {
            it.subscribe(doOnSuccess = { result ->
                showLoading(false)
                result.payload?.let { data ->
                    homeFeedsAdapter.setItems(data)
                }
            }, doOnLoading = {
                showLoading(true)
            }, doOnError = { error ->
                showLoading(false)
                error.exception?.let { e -> showError(true, e) }
            })
        }
        viewModel.currentUserResult.observe(viewLifecycleOwner) {
            it.subscribe(doOnSuccess = { result ->
                binding.ivAvatarUser.setImageDrawable(
                    TextDrawable.builder().beginConfig().bold().toUpperCase().endConfig().buildRect(
                        result.payload?.username?.get(0).toString(),
                        ColorGenerator.MATERIAL.randomColor
                    )
                )
            })
        }
    }

    private fun initData() {
        viewModel.getCurrentUser()
        viewModel.fetchHomeFeeds()
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbHome.isVisible = isShowLoading
    }
}