package org.m.mushatha.finalproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.m.mushatha.finalproject.adapter.FoodAdapter
import org.m.mushatha.finalproject.databinding.ActivityAllMealsBinding
import org.m.mushatha.finalproject.helper.DbHelper
import org.m.mushatha.finalproject.model.Food

class AllMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllMealsBinding
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (this).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (this).supportActionBar!!.setDisplayShowHomeEnabled(true)

        dbHelper = DbHelper(this)
        displayMeals()
        val name = intent.getStringExtra("categoryName")

        if (name != "" && name != null){
            displayMealsFilter(name)
        }

    }

    private fun displayMealsFilter(name: String){
        val meals =ArrayList<Food>()
        for (meal in dbHelper.getAllMeals()){
            if (meal.categoty == name)
                meals.add(meal)
        }

        binding.rvAllMeals.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        binding.rvAllMeals.adapter = FoodAdapter(this,meals)
    }

    private fun displayMeals(){
        val meals =ArrayList<Food>()
        for (meal in dbHelper.getAllMeals()){
                meals.add(meal)
        }


        binding.rvAllMeals.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        binding.rvAllMeals.adapter = FoodAdapter(this,meals)

    }
    override fun onSupportNavigateUp(): Boolean {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        return true
    }

}
