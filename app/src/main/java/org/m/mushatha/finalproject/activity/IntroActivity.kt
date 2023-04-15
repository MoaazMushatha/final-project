package org.m.mushatha.finalproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import android.view.WindowManager
import org.m.mushatha.finalproject.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
   private  val IntroActivityimeout:Long = 2500


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

          Handler().postDelayed({


        val i = Intent(this, LoggingActivity::class.java)
        startActivity(i)
        finishAffinity()

         },IntroActivityimeout)

        binding.strtbtn.setOnClickListener {
    }

    fun onPause() {
        super.onPause()
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
    }
}


}
