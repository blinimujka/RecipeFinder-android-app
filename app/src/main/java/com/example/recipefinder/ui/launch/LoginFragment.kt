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
import com.google.firebase.auth.FirebaseAuth

class LoginFragment:Fragment() {



    lateinit var binding: FragmentLoginBinding
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            layoutInflater,
            container, false
        )
        auth = FirebaseAuth.getInstance()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.loginBttn.setOnClickListener {
            var email = binding.emailText.text.toString().trim()
            var password = binding.passwordText.text.toString().trim()

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    println("Login success")
findNavController().navigate(R.id.loginSuccessful)
                }
                else{
                    Toast.makeText(
                        context, "Test TOAST",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }


        }
        binding.registerText.setOnClickListener {
            findNavController().navigate(R.id.login_to_register)

        }

    }

}




