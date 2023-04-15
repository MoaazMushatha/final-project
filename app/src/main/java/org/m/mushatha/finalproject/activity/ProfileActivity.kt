package org.m.mushatha.finalproject.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import org.m.mushatha.finalproject.R
import org.m.mushatha.finalproject.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var prefs: SharedPreferences
    
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var date: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (this).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (this).supportActionBar!!.setDisplayShowHomeEnabled(true)

        //actionBar?.setIcon(android.R.color.transparent)

        binding.includedBottom.bottomBtnHome.setImageResource(R.drawable.ic_home_gray)
        binding.includedBottom.bottomBtnProfile.setImageResource(R.drawable.ic_profile_orange)

        binding.includedBottom.homeContainer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        prefs = getSharedPreferences("MyPref", MODE_PRIVATE)
        
        name = prefs.getString("name", "")!!
        email = prefs.getString("email1", "")!!
        date = prefs.getString("date", "")!!
        password = prefs.getString("Password", "")!!
        
        binding.txname.setText(name)
        binding.txemail1.setText(email)
        binding.txdate.setText(date)
        binding.txPassword.setText(password)

        binding.btnEditProfile.setOnClickListener {
            val name = binding.txname.text.toString().trim()
            val email = binding.txemail1.text.toString().trim()
            val date = binding.txdate.text.toString().trim()
            val password = binding.txPassword.text.toString().trim()
            
            if(valid(name, email, date, password)) {
                val editor = prefs.edit()
                editor.putString("name", name)
                editor.putString("email1", email)
                editor.putString("date", date)
                editor.putString("Password", password)
                val isUpdated = editor.commit()

                if (isUpdated) {
                    Toast.makeText(this, "Updated Successfully.", Toast.LENGTH_SHORT).show()
                    finish()
                } else
                    Toast.makeText(this, "Update Failed.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Please Change at Least One Thing.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun valid(name: String, email: String, date: String, password: String): Boolean {
        if(this.name == name && 
                this.email == email &&
                this.date == date &&
                this.password == password)
            return false
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logOut -> {
                val editor = prefs.edit()
                editor.putBoolean("is login", false)
                if(editor.commit()){
                    val intent = Intent(this, LoggingActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        return true
    }

}