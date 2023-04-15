package org.m.mushatha.finalproject.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.m.mushatha.finalproject.activity.MealDetailsActivity
import org.m.mushatha.finalproject.activity.UpdateActivity
import org.m.mushatha.finalproject.databinding.ViewholderGeneralMealLayoutBinding
import org.m.mushatha.finalproject.model.Food

class FoodAdapter(var  activity: Activity ,var data:ArrayList<Food>) :RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){
    class FoodViewHolder (var binding:ViewholderGeneralMealLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding=ViewholderGeneralMealLayoutBinding.inflate(activity.layoutInflater,parent,false)
        return FoodViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = data[position]

        holder.binding.FoodName.text = item.name
        holder.binding.FoodFee.text ="$${item.price.toString().toDoubleOrNull()}"
        holder.binding.FoodPic.setImageBitmap(BitmapFactory.decodeByteArray(item.image,0,item.image.size))
        holder.binding.rate.rating = item.rate.toFloat()

        val intent = Intent(activity, MealDetailsActivity::class.java)

        holder.binding.cvGeneralLayout.setOnClickListener {
            Toast.makeText(activity,"${item.id}", Toast.LENGTH_SHORT).show()
            intent.putExtra("isF", item.isFavorite)
            intent.putExtra("id", item.id)

            activity.startActivity(intent)
        }

        holder.binding.cvGeneralLayout.setOnLongClickListener {
            val intent = Intent(activity,UpdateActivity ::class.java )
            intent.putExtra("idForUpdate", item.id)
            activity.startActivity(intent)
            true
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}