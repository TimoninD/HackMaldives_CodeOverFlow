package ru.codeoverflow.junctionhack.ui.fragment.signin

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_sign_in.*
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R

class SignInFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_sign_in

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignIn.setOnClickListener {

        }

    }

}