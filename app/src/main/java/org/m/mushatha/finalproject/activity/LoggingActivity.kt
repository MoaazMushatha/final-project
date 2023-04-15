package org.m.mushatha.finalproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import org.m.mushatha.finalproject.R
import org.m.mushatha.finalproject.databinding.ActivityLoggingBinding
import org.m.mushatha.finalproject.databinding.ActivityMainBinding

class LoggingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoggingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)


        fragmentSwitcher(LoginFragment())

//        binding.btnFrag1.setOnClickListener {
//            fragmentSwitcher(FirstFragment())
//        }
//
//        binding.btnFrag2.setOnClickListener {
//            fragmentSwitcher(SecondFragment())
//        }
    }

    private fun fragmentSwitcher(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
    }

    override fun onPause() {
        super.onPause()
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
    }
}