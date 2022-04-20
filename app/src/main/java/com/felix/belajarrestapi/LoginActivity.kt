package com.felix.belajarrestapi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.felix.belajarrestapi.databinding.ActivityLoginBinding
import com.felix.belajarrestapi.model.Auth.GetAuthResponse
import com.felix.belajarrestapi.model.Login.PostLoginResponse
import com.felix.belajarrestapi.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        setupViews(editor)

    }

    private fun setupViews(editor: SharedPreferences.Editor) {
        binding.apply {
            btnLogin.setOnClickListener {
                if (!etEmail.text.isNullOrEmpty() && !etPassword.text.isNullOrEmpty()){
                    editor.putString("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MjVmY2U0NTdlYTQ3NjAwMTc0ZWE5ZTciLCJ1c2VybmFtZSI6ImNoaXVtYW5mZWxpeCIsImVtYWlsIjoiY2hpdW1hbmZlbGl4QGVtYWlsLmNvbSIsImlhdCI6MTY1MDQ2MTU3OCwiZXhwIjoxNjUwNDY4Nzc4fQ.Yc6aYNvDk08Ugyo7tTHn2v0SYq5Nr5F_HTWjL5sHhTU")
                    loginAction(etEmail.text.toString(), etPassword.text.toString())
                }
                else{
                    Toast.makeText(this@LoginActivity, "login error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loginAction(email: String, password: String) {
        val request = LoginRequest(
            email = email,
            password = password,
        )

        ApiClient.instance.getLoginResponse(request)
            .enqueue(object : Callback<PostLoginResponse>{
                override fun onResponse(
                    call: Call<PostLoginResponse>,
                    response: Response<PostLoginResponse>
                ) {
                    val code = response.code()
                    if (code == 200){
                        Toast.makeText(this@LoginActivity, "sukses", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity2::class.java))
                    }
                    else{
                        Toast.makeText(this@LoginActivity, "gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}