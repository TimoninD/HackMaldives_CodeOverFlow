package ru.codeoverflow.junctionhack.ui.fragment.signin

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.ext.hideKeyboard
import ru.codeoverflow.junctionhack.ext.updateMarginBottomOnApplySystemWindowInsets
import ru.codeoverflow.junctionhack.viewmodel.login.SignInViewModel

class SignInFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_sign_in

    private val viewModel by viewModel<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrollView.updateMarginBottomOnApplySystemWindowInsets()

        viewModel.signInResult.observe(viewLifecycleOwner, Observer {
            if (it) {
                hideKeyboard()
                findNavController().navigate(
                    SignInFragmentDirections.actionSignInFragmentToConfirmCodeFragment(
                        etPhoneNumber.text.toString()
                    )
                )
            }
        })

        btnSignIn.setOnClickListener {
            viewModel.signIn(etPhoneNumber.text.toString())
        }

    }

}