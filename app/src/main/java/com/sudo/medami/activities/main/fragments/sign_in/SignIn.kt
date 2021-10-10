package com.sudo.medami.activities.main.fragments.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sudo.medami.R
import com.sudo.medami.databinding.FragmentSignInBinding

class SignIn : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(
            inflater,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkClickEvent(view)
    }

    private fun checkClickEvent(view: View) {
        binding.btnSignIn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_signIn_to_confirmFragment)
        }
        binding.btnSwitchToSignUp.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_signIn_to_signUp)
        }
    }

}