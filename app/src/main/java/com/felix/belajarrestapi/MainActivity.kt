package com.felix.belajarrestapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.felix.belajarrestapi.databinding.ActivityMainBinding
import com.felix.belajarrestapi.model.Register.PostRegisterResponse
import com.felix.belajarrestapi.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

        binding.btnLoginPage.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setupViews() {
        binding.apply {
            btnDaftar.setOnClickListener {
                if (!etEmail.text.isNullOrEmpty() || !etPassword.text.isNullOrEmpty() || !etUsername.text.isNullOrEmpty()){
                    registerAction(etEmail.text.toString(), etPassword.text.toString(), etUsername.text.toString())
                }
                else{
                    Toast.makeText(this@MainActivity, "data tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun registerAction(email: String, password: String, username: String) {
        val request = RegisterRequest(
            email = email,
            password = password,
            username = username
        )

        ApiClient.instance.getRegisterPost(request)
            .enqueue(object : Callback<PostRegisterResponse>{
                override fun onResponse(
                    call: Call<PostRegisterResponse>,
                    response: Response<PostRegisterResponse>
                ) {
                    val code = response.code()
                    if (code == 201){
                        Toast.makeText(this@MainActivity, "sukses", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    }
                    else{
                        Toast.makeText(this@MainActivity, "gagal", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<PostRegisterResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}