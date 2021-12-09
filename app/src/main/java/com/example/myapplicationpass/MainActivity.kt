package com.example.myapplicationpass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var editTextTextPassword: EditText
    private lateinit var editTextTextPassword1: EditText
    private lateinit var button: Button
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        registerListeners()
    }

    private fun init() {
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)
        editTextTextPassword1 = findViewById(R.id.editTextTextPassword1)
        button = findViewById(R.id.button)
    }

    private fun registerListeners() {
        button.setOnClickListener {
            val email = editTextTextEmailAddress.text.toString()
            val password = editTextTextPassword.text.toString()
            val password2 = editTextTextPassword1.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Empty~!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password == password2 && password.length > 9 && password.contains("[0-9]".toRegex()) && password.contains(
                    "[a-z]".toRegex()
                ) && email.matches(emailPattern.toRegex())
            ) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }
    }
}