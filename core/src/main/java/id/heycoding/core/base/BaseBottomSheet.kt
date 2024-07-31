package id.heycoding.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.heycoding.core.utils.getErrorMessageByException


/**
 * Created by Irfan Nawawi on 18/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
abstract class BaseBottomSheet<B : ViewBinding>(val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> B) :
    BottomSheetDialogFragment() {

    protected lateinit var binding: B

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory(layoutInflater, container, false)
        return binding.root
    }

    abstract fun initView()

    open fun observeData() {}

    fun showError(isErrorEnabled: Boolean, exception: Exception) {
        if (isErrorEnabled) {
            Toast.makeText(
                requireContext(),
                requireContext().getErrorMessageByException(exception),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}