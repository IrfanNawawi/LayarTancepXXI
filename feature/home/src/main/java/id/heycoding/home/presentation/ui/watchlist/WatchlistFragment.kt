package id.heycoding.home.presentation.ui.watchlist

import id.heycoding.core.base.BaseFragment
import id.heycoding.home.databinding.FragmentWatchlistBinding
import id.heycoding.home.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class WatchlistFragment :
    BaseFragment<FragmentWatchlistBinding, HomeViewModel>(FragmentWatchlistBinding::inflate) {
    override val viewModel: HomeViewModel by sharedViewModel()

    override fun initView() {

    }
}