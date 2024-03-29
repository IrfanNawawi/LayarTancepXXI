package id.heycoding.player.presentation.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.heycoding.player.R
import id.heycoding.player.databinding.ActivityPlayerBinding
import id.heycoding.player.presentation.fragment.PlayerFragment

class PlayerActivity : AppCompatActivity() {
    private val binding: ActivityPlayerBinding by lazy {
        ActivityPlayerBinding.inflate(layoutInflater)
    }
    private val videoUrl: String? by lazy { intent?.extras?.getString(EXTRA_VIDEO_URL) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        videoUrl?.let {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.container.id, PlayerFragment.newInstance(it))
            }.commit()
        }
    }

    companion object {
        private const val EXTRA_VIDEO_URL = "EXTRA_VIDEO_URL"
        fun createIntent(context: Context, videoUrl: String): Intent {
            return Intent(context, PlayerActivity::class.java).apply {
                putExtra(EXTRA_VIDEO_URL, videoUrl)
            }
        }
    }
}