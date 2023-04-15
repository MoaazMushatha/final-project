package org.m.mushatha.finalproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.m.mushatha.finalproject.R
import org.m.mushatha.finalproject.adapter.CategoryAdapter
import org.m.mushatha.finalproject.adapter.FavoriteMealsAdapter
import org.m.mushatha.finalproject.databinding.ActivityMainBinding
import org.m.mushatha.finalproject.helper.DbHelper
import org.m.mushatha.finalproject.model.Category
import org.m.mushatha.finalproject.model.Food
import org.m.mushatha.finalproject.util.Constants.Companion.categories
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: DbHelper

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        handleAddFAB()
        handleAllMeals()

        dbHelper = DbHelper(this)

        displayFavoriteMeals()

//        val bitmap = (resources.getDrawable(R.drawable.pizza3) as BitmapDrawable).bitmap
//        val stream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
//        val image = stream.toByteArray()

        val data = ArrayList<Category>()
        data.add(Category(1, "Pizza", R.drawable.cat_1))
        data.add(Category(2, "Burger", R.drawable.cat_2))
        data.add(Category(3, "Hotdog", R.drawable.cat_3))
        data.add(Category(4, "Drink", R.drawable.cat_4))
        data.add(Category(5, "sweets", R.drawable.cat_5))

        categories.clear()
        for (category in data){
            categories.add(category.name)
        }

        val preferences= getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
        binding.tvHelloUser.text = "Hi "+preferences.getString("name","")


        binding.view1.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.view1.adapter = CategoryAdapter(this, data)

        binding.includedBottom.profileContainer.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handelShowAllMeals(){
        binding.tvAllItems.setOnClickListener {
            startActivity(Intent(this, AllMealsActivity::class.java))
        }
    }

    private fun displayFavoriteMeals(){
        val favoriteMeals =ArrayList<Food>()
        for (meal in dbHelper.getAllMeals()){
            if (meal.isFavorite == 1)
                favoriteMeals.add(meal)
        }

        binding.vew2.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.vew2.adapter = FavoriteMealsAdapter(this,favoriteMeals)
    }

    private fun handleAddFAB(){
        val intent = Intent(this, AddMealActivity::class.java)
        binding.fabAddMeal.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun handleAllMeals(){
        val intent = Intent(this, AllMealsActivity::class.java)
        binding.tvAllItems.setOnClickListener {
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
    }


}






