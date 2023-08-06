package com.example.avmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.avmproject.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeMessage()
        observeLoginState()

        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.etEmail.text.toString(),binding.etPassword.text.toString())
        }
    }
    private fun observeMessage(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.message.collect{
                    AlertDialog.Builder(this@LoginActivity).setMessage(it).create().show()
                }
            }
        }
    }
    private fun observeLoginState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.loginState.collect {
                    when (it) {
                        is LoginState.Idle -> {}
                        is LoginState.Error -> {
                            AlertDialog.Builder(this@LoginActivity).setTitle("ERROR").setMessage("login error").setIcon(R.drawable.warning).create().show()
                        }
                        is LoginState.Success -> {
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        }

                    }
                }
            }
        }
    }
}