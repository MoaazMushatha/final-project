package org.m.mushatha.finalproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.m.mushatha.finalproject.R
import org.m.mushatha.finalproject.databinding.ActivityMealDetailsBinding
import org.m.mushatha.finalproject.helper.DbHelper
import java.net.URI


class MealDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailsBinding
    private lateinit var dbHelper: DbHelper

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (this).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (this).supportActionBar!!.setDisplayShowHomeEnabled(true)


        dbHelper = DbHelper(this)

        var isFavorite = 0
        val id = intent.getIntExtra("id", -1)
        val isF = intent.getIntExtra("isF", -1)

        val meal = dbHelper.getMeal(id = id)
        isFavorite = meal.isFavorite

        binding.Price.text = meal.price.toString()
        binding.foodPic.setImageBitmap(
            BitmapFactory.decodeByteArray(
                meal.image,
                0,
                meal.image.size
            )
        )
        binding.nameNew.text = meal.name
        binding.totalPriceTxt.text = meal.price.toString()
        //  binding.descriptionTxt.text=meal.descriptionTxt.toString()


        Toast.makeText(this,"$isFavorite ,,$isF ",Toast.LENGTH_SHORT).show()
        if (isFavorite == 1) {
            binding.ivFavoriteState.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.ivFavoriteState.setImageResource(R.drawable.ic_isnt_favorite)
        }

        binding.ivFavoriteState.setOnClickListener {


            isFavorite *= -1
            if (isFavorite == 1) {
                binding.ivFavoriteState.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite
                    )
                )
            } else {
                binding.ivFavoriteState.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_isnt_favorite
                    )
                )
            }
            dbHelper.updateMealsFavorite(id = id, isFavorite = isFavorite)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val i = Intent(this, AllMealsActivity::class.java)
        startActivity(i)
        return true
    }

}