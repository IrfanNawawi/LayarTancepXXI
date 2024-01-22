package id.heycoding.home.presentation.ui.home

import androidx.fragment.app.Fragment
import id.heycoding.core.base.BaseActivity
import id.heycoding.home.databinding.ActivityHomeBinding
import id.heycoding.home.presentation.ui.homefeeds.HomeFeedsFragment
import id.heycoding.home.presentation.ui.watchlist.WatchlistFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity :
    BaseActivity<ActivityHomeBinding, HomeViewModel>(ActivityHomeBinding::inflate) {
    private val homeFeedsFragment = HomeFeedsFragment()
    private val watchListFragment = WatchlistFragment()
    private var activeFragment: Fragment = homeFeedsFragment

    override val viewModel: HomeViewModel by viewModel()

    override fun initView() {
        setupFragment()
    }

    private fun setupFragment() {
        // delete all fragment in fragment manager first
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        // add fragment to fragment manager
        supportFragmentManager.beginTransaction().apply {
            add(binding.container.id, homeFeedsFragment)
            add(binding.container.id, watchListFragment)
            hide(watchListFragment)
        }.commit()
        // set click menu for changing fragment
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                id.heycoding.styling.R.id.home -> {
                    showFragment(homeFeedsFragment)
                    true
                }

                else -> {
                    showFragment(watchListFragment)
                    true
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()
        activeFragment = fragment
    }

}