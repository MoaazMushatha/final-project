package org.m.mushatha.finalproject.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.m.mushatha.finalproject.model.Category
import org.m.mushatha.finalproject.model.Food

class DbHelper (context: Context)
    : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){

    private val db = writableDatabase
   // private val dbRead = readableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(Food.TABLE_CREATE)
        //db.execSQL(Food.TABLE_CREATE_CATEGORY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop TABLE IF EXISTS ${Food.TABLE_NAME}")
        onCreate(db)
    }

//-----------------------------------------------------------------------------------------------

    fun insertMeal(food:Food):Boolean{
        val cv= ContentValues()
        cv.put(Food.COL_NAME,food.name)
        cv.put(Food.COL_IMAGE,food.image)
        cv.put(Food.COL_CATEGORY,food.categoty)
        cv.put(Food.COL_PRICE,food.price)
        cv.put(Food.COL_RATE,food.rate)

        return  db.insert(Food.TABLE_NAME,null, cv) >0
    }

    fun insertCategory(categories: ArrayList<Category>):Boolean{
        val cv= ContentValues()
        for (category in categories){
            cv.put(Food.COL_NAME,category.name)
            cv.put(Food.COL_IMAGE,category.image)
        }


        return  db.insert(Food.TABLE_CREATE_CATEGORY,null, cv) >0
    }

    fun getAllMeals():ArrayList<Food>{
        val food=ArrayList<Food>()

        val c = db.rawQuery("SELECT * FROM ${Food.TABLE_NAME} ORDER BY ${Food.COL_ID} DESC" ,null)

        if(c!=null && c.count > 0)
        {
            if (c.moveToFirst())
            {
                do {
                    val s = Food(
                        id = c.getInt(c.getColumnIndexOrThrow(Food.COL_ID)),
                        name = c.getString(c.getColumnIndexOrThrow(Food.COL_NAME)),
                        image = c.getBlob(c.getColumnIndexOrThrow(Food.COL_IMAGE)),
                        price = c.getDouble(c.getColumnIndexOrThrow(Food.COL_PRICE)),
                        categoty = c.getString(c.getColumnIndexOrThrow(Food.COL_CATEGORY)),
                        isFavorite = c.getInt(c.getColumnIndexOrThrow(Food.COL_IS_FAVORITE)),
                        rate = c.getInt(c.getColumnIndexOrThrow(Food.COL_RATE))
                    ) //rate = c.getInt(c.getColumnIndexOrThrow(Food.COL_RATE))
                    food.add(s)
                } while (c.moveToNext())
            }
        }
        c.close()
        return food
    }

    fun deleteMeals(id:Int):Boolean{
        return  db.delete(Food.TABLE_NAME,"${Food.COL_ID} = $id",null)  >0
    }

    fun updateMeals(food:Food) :Boolean{
        val cv= ContentValues()
        cv.put(Food.COL_NAME,food.name)
        cv.put(Food.COL_IMAGE,food.image)
        cv.put(Food.COL_PRICE,food.price)

        return  db.update(Food.TABLE_NAME,cv,"${Food.COL_ID} = ${food.id}",null) >0
    }

    fun updateMeal(food:Food):Boolean{
        val cv= ContentValues()
        cv.put(Food.COL_NAME,food.name)
        cv.put(Food.COL_IMAGE,food.image)
        cv.put(Food.COL_CATEGORY,food.categoty)
        cv.put(Food.COL_PRICE,food.price)
        cv.put(Food.COL_RATE,food.rate)

        return  db.update(Food.TABLE_NAME,cv,"${Food.COL_ID} = ${food.id}",null) >0
    }

    fun updateMealsFavorite(id: Int, isFavorite: Int) :Boolean{
        val cv= ContentValues()
        cv.put(Food.COL_IS_FAVORITE,isFavorite)

        return  db.update(Food.TABLE_NAME,cv,"${Food.COL_ID} = $id",null) >0
    }

    fun getMeal(id: Int): Food {
        val c= db.rawQuery("SELECT * FROM ${Food.TABLE_NAME} WHERE ${Food.COL_ID} = $id" ,null)
        c.moveToFirst()

        val food =Food(
            id = c.getInt(0),
            name = c.getString(1),
            image = c.getBlob(2),
            price = c.getDouble(3),
            categoty = c.getString(4),
            isFavorite = c.getInt(5),
            rate = c.getInt(6)
        )
        c.close()
          return food
    }

    companion object{
        const val DATABASE_NAME="FoodDb"
        const val DATABASE_VERSION=2
    }
}