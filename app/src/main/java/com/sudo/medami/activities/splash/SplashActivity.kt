package com.sudo.medami.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.sudo.medami.R
import com.sudo.medami.activities.main.MainActivity
import com.sudo.medami.common.Common
import com.sudo.medami.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSwitchToSignIn.setOnClickListener{
            Log.e("dir", "onCreate: ", )
            toMainActivity(Common.State.TO_SIGNIN)
        }
        binding.btnSwitchToSignUp.setOnClickListener{
            toMainActivity(Common.State.TO_SIGNUP)
        }

    }
    private fun toMainActivity(direction: Common.State) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("direction", direction.toString())
        Log.e("dir", "toMainActivity: switch", )
        startActivity(intent)
    }
}
