
package org.m.mushatha.finalproject.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.m.mushatha.finalproject.databinding.ActivityAddMealBinding
import org.m.mushatha.finalproject.helper.DbHelper
import org.m.mushatha.finalproject.model.Food
import org.m.mushatha.finalproject.util.Constants.Companion.categories

class AddMealActivity : AppCompatActivity(),
    AdapterView.OnItemSelectedListener {

    private lateinit var binding:ActivityAddMealBinding
    private lateinit var  openFileLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var byteArray: ByteArray
    private lateinit var dbHelper: DbHelper
    private var category: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (this).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (this).supportActionBar!!.setDisplayShowHomeEnabled(true)

        byteArray = ByteArray(0)
        category = ""
        dbHelper = DbHelper(this)

        handleSpinner()
        getImage()

        var rndRate = 1

        binding.btnAddMeal.setOnClickListener {
            rndRate = (1..5).random()
            val name = binding.etNewMealName.text.toString()
            val price = binding.etNewMealPrice.text.toString().toDoubleOrNull()
            if (name != "" && price != null && byteArray.isNotEmpty() && category != ""){

             val isAdded = dbHelper.insertMeal(
                 Food(
                     id = 0,
                     name = name,
                     price = price,
                     image = byteArray,
                     categoty = category!!,
                     isFavorite = -1,
                     rate = rndRate
                 )
             )
                if (isAdded){
                    Toast.makeText(this,"Added Successful..",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }else
                    Toast.makeText(this, "Error...", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun getImage(){
        openFileLauncher =registerForActivityResult(ActivityResultContracts.OpenDocument()){ Uri->
            val inputStream =contentResolver.openInputStream(Uri!!)
            byteArray =inputStream?.readBytes()!!
            binding.ivChooseNew.setImageBitmap(BitmapFactory.decodeByteArray(byteArray,0,byteArray.size))
        }
        binding.ivChooseNew.setOnClickListener{
            openFileLauncher.launch(arrayOf("image/*"))
        }
    }

    private fun handleSpinner(){
        binding.svNewMealCategory.onItemSelectedListener = this

        val ad: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.svNewMealCategory.adapter = ad
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        category = categories[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
    override fun onSupportNavigateUp(): Boolean {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        return true
    }
}