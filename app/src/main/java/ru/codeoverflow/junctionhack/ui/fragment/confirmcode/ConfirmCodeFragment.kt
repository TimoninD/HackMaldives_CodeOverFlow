package ru.codeoverflow.junctionhack.ui.fragment.confirmcode

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_confirm_code.*
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R

private const val PIN_CODE_LENGTH = 4

class ConfirmCodeFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_confirm_code

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etPinCode.setOnPinEnteredListener { pinCode ->
            if (pinCode.length == PIN_CODE_LENGTH) {
                findNavController().navigate(ConfirmCodeFragmentDirections.actionConfirmCodeFragmentToHomeFragment())
            }
        }
    }

}