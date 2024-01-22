package id.heycoding.home.presentation.ui.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.heycoding.core.base.BaseFragment
import id.heycoding.home.R
import id.heycoding.home.databinding.FragmentWatchlistBinding
import id.heycoding.home.presentation.adapter.MovieAdapter
import id.heycoding.home.presentation.ui.home.HomeViewModel
import id.heycoding.shared.utils.ext.subscribe
import id.heycoding.styling.ProjectString
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WatchlistFragment : BaseFragment<FragmentWatchlistBinding, HomeViewModel>(FragmentWatchlistBinding::inflate) {
    override val viewModel: HomeViewModel by sharedViewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun initView() {
        setupRecyclerView()
        initData()
        initRefresh()
    }

    private fun setupRecyclerView() {
        binding.rvWatchlist.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    private fun initData() {
        viewModel.fetchWatchList()
    }

    override fun observeData() {
        super.observeData()
        viewModel.watchlistResult.observe(viewLifecycleOwner) {
            it.subscribe(doOnSuccess = { result ->
                showLoading(false)
                binding.tvErrorWatchlist.isVisible = false
                result.payload?.let { data ->
                    movieAdapter.setItems(data)
                }
            }, doOnLoading = {
                showLoading(true)
                binding.tvErrorWatchlist.isVisible = false
            }, doOnError = { error ->
                showLoading(false)
                binding.tvErrorWatchlist.isVisible = true
                error.exception?.let { e -> showError(true, e) }
            }, doOnEmpty = {
                showLoading(false)
                binding.tvErrorWatchlist.isVisible = true
                binding.tvErrorWatchlist.text = getString(ProjectString.text_empty_watchlist)
            })
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.rvWatchlist.isVisible = !isShowLoading
        binding.pbWatchlist.isVisible = isShowLoading
    }

    private fun initRefresh() {
        binding.srlWatchlist.setOnRefreshListener {
            viewModel.fetchWatchList()
            binding.srlWatchlist.isRefreshing = false
        }
    }


}