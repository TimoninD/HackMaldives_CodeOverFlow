package ru.codeoverflow.junctionhack.ui.fragment.confirmcode

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_confirm_code.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.ext.hideKeyboard
import ru.codeoverflow.junctionhack.viewmodel.codeconfirm.ConfirmCodeViewModel

private const val PIN_CODE_LENGTH = 4

class ConfirmCodeFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_confirm_code

    private val viewModel by viewModel<ConfirmCodeViewModel>()

    private val args: ConfirmCodeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.codeConfirmResult.observe(viewLifecycleOwner, Observer {
            hideKeyboard()
            findNavController().navigate(ConfirmCodeFragmentDirections.actionConfirmCodeFragmentToHomeFragment())
        })

        etPinCode.setOnPinEnteredListener { pinCode ->
            if (pinCode.length == PIN_CODE_LENGTH) {
                viewModel.verify(args.phone, etPinCode.text.toString())
            }
        }
    }

}