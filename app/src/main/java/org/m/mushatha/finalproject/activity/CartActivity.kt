package org.m.mushatha.finalproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.m.mushatha.finalproject.R
import org.m.mushatha.finalproject.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_cart)


    }
}