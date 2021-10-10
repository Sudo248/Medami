package com.sudo.medami.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.sudo.medami.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get direction
        switchFragment(intent)
    }

    private fun switchFragment(intent: Intent?) {
        intent?.let{
            val direction = it.getStringExtra("direction").toString()
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController
            if (direction == "sign_in"){
                navController.navigate(R.id.signIn)
            }
            else{
                navController.navigate(R.id.signUp)
            }
        }
    }

}