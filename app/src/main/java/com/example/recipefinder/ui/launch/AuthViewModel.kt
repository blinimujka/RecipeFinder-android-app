package com.example.recipefinder.ui.launch

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipefinder.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.security.AccessController.getContext
import kotlin.coroutines.coroutineContext

class AuthViewModel: ViewModel(){

    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser?>
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth

    companion object{
        var currentUser:String = ""
    }

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
                firebaseUserMutableLiveData.postValue(auth.currentUser)
                currentUser = auth.currentUser!!.email.toString()
        }
    }

    fun register(email: String?, pass: String?): Boolean {
        auth.createUserWithEmailAndPassword(email!!, pass!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseUserMutableLiveData.postValue(auth.currentUser)

            } else {
               // Toast.makeText(application, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    fun login(email: String?, pass: String?):Boolean {
            auth.signInWithEmailAndPassword(email!!, pass!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseUserMutableLiveData.postValue(auth.currentUser)

            } else {
                //Toast.makeText(, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }
}