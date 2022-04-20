package com.felix.belajarrestapi

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.felix.belajarrestapi.databinding.ActivityLoginBinding
import com.felix.belajarrestapi.databinding.ActivityMain3Binding
import com.felix.belajarrestapi.model.Auth.Data
import com.felix.belajarrestapi.model.Auth.GetAuthResponse
import com.felix.belajarrestapi.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences : SharedPreferences = this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        val token = sharedPreferences.getString("token","0")

        if (token != null) {
            setupViews(token)
        }
    }

    private fun setupViews(token: String) {
        ApiClient.instance.getAuthResponse("Bearer $token")
            .enqueue(object : Callback<GetAuthResponse>{
                override fun onResponse(
                    call: Call<GetAuthResponse>,
                    response: Response<GetAuthResponse>
                ) {
                    var code = response.code()
                    if (code == 200){
                        var email = binding.tvEmailblank
                        var username = binding.tvUsernameblank
                        email.text = "${response.body()?.data?.email}"
                        username.text = "${response.body()?.data?.username}"
                    }
                }

                override fun onFailure(call: Call<GetAuthResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity2, "error", Toast.LENGTH_SHORT).show()
                }
            })
    }
}