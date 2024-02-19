package id.heycoding.shared.utils.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


/**
 * Created by Irfan Nawawi on 07/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
fun EditText.listen(
    beforeTextChanged: ((String) -> Unit)? = null,
    onTextChanged: ((String) -> Unit)? = null,
    afterTextChanged: ((String) -> Unit)? = null,
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            beforeTextChanged?.invoke(s.toString())
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged?.invoke(s.toString())
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged?.invoke(editable.toString())
        }

    })
}