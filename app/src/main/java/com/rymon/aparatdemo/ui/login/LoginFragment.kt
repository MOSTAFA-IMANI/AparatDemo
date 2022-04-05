package com.rymon.aparatdemo.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rymon.aparatdemo.R
import com.rymon.aparatdemo.databinding.FragmentLoginBinding
import com.rymon.aparatdemo.databinding.FragmentSearchBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
//    private val args: LoginFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val usernameDeepLink = args.username
//        edit_text_username.setText(usernameDeepLink)
        _binding = FragmentLoginBinding.bind(view)
      binding.apply {
          buttonConfirm.setOnClickListener{
              val username = editTextUsername.text.toString()
              val password = editTextPassword.text.toString()
          }
      }
    }
}