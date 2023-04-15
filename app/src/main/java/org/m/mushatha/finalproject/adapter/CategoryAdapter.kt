package org.m.mushatha.finalproject.adapter

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.m.mushatha.finalproject.activity.AllMealsActivity
import org.m.mushatha.finalproject.activity.MealDetailsActivity
import org.m.mushatha.finalproject.databinding.ViewholderCategoryBinding
import org.m.mushatha.finalproject.model.Category

class CategoryAdapter(var activity: Activity, var data: ArrayList<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(var binding: ViewholderCategoryBinding)
        : RecyclerView.ViewHolder(binding.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ViewholderCategoryBinding.inflate(activity.layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = data[position]
        holder.binding.categoryPic.setImageResource(item.image)
        holder.binding.categoryName.text = item.name

        val intent = Intent(activity, AllMealsActivity::class.java)
        holder.binding.mainLayout.setOnClickListener {
            intent.putExtra("categoryName", item.name)

            activity.startActivity(intent)

        }

        holder.binding.mainLayout.setOnLongClickListener {
           true
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}