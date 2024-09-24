package com.example.recipefinder.ui.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recipefinder.R
import com.example.recipefinder.databinding.FragmentLoginBinding
import com.example.recipefinder.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment: Fragment() {



    lateinit var binding: FragmentRegisterBinding
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(
            layoutInflater,
            container, false
        )

        auth = FirebaseAuth.getInstance()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.registerBttn.setOnClickListener {

            var email = binding.emailText.text.toString().trim()
            var password = binding.passwordText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    getActivity(),
                    "Authentication failed.",
                    Toast.LENGTH_LONG,
                ).show()
            } else {

                with(binding) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                findNavController().navigate(R.id.register_to_login)

                            } else {
                                Toast.makeText(
                                    getActivity(),
                                    "Authentication failed.",
                                    Toast.LENGTH_LONG,
                                ).show()
                            }

                        }

                }

            }
        }
        binding.loginText.setOnClickListener {
            findNavController().navigate(R.id.register_to_login)

        }

    }

}




