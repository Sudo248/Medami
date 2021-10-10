package com.sudo.medami.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.sudo.medami.R
import com.sudo.medami.activities.main.MainActivity
import com.sudo.medami.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        binding.btnSwitchToSignIn.setOnClickListener{
            Log.e("dir", "onCreate: ", )
            toMainActivity(State.TO_SIGNIN)
        }
        binding.btnSwitchToSignUp.setOnClickListener{
            toMainActivity(State.TO_SIGNUP)
        }
    }
    private fun toMainActivity(direction: State) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("direction", direction.toString())
        Log.e("dir", "toMainActivity: switch", )
        startActivity(intent)
    }
}
enum class State {
    TO_SIGNIN,
    TO_SIGNUP
}