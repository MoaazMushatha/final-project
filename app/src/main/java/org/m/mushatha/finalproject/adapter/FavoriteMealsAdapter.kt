package org.m.mushatha.finalproject.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.m.mushatha.finalproject.activity.MealDetailsActivity
import org.m.mushatha.finalproject.activity.UpdateActivity
import org.m.mushatha.finalproject.databinding.ViewholderRecommendedBinding
import org.m.mushatha.finalproject.model.Food

class FavoriteMealsAdapter (var  activity: Activity, var data:ArrayList<Food>) :
    RecyclerView.Adapter<FavoriteMealsAdapter.FoodViewHolder>(){
    class FoodViewHolder (var binding: ViewholderRecommendedBinding)
        : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding= ViewholderRecommendedBinding.inflate(activity.layoutInflater,parent,false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item=data[position]

        holder.binding.FoodPic.setImageBitmap(BitmapFactory.decodeByteArray(item.image,0,item.image.size))
        holder.binding.FoodName.text = item.name
        holder.binding.FoodFee.text = item.price.toString()

        val intent = Intent(activity, MealDetailsActivity::class.java)
        holder.binding.favoriteCard.setOnClickListener {
            intent.putExtra("name", item.name)
            intent.putExtra("id", item.id)

            activity.startActivity(intent)
        }

        holder.binding.favoriteCard.setOnLongClickListener {
            intent.putExtra("idForUpdate", item.id)
            activity.startActivity(Intent(activity, UpdateActivity ::class.java ))
            true
        }

    }

    override   fun getItemCount(): Int {
        return data.size
    }
}