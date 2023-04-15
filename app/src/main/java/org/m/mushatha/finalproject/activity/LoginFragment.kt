package org.m.mushatha.finalproject.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.m.mushatha.finalproject.R
import org.m.mushatha.finalproject.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentLoginBinding.inflate(inflater, container, false)

        val preferences= this.requireActivity().getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
        if(preferences.getBoolean("is login",false)){
            startActivity(Intent(this.requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

        binding.btnlogin.setOnClickListener {
            if(binding.txUsername.text.isNotEmpty() && binding.txPassword1.text.isNotEmpty() ){
                val name=preferences.getString("name","")
                val password=preferences.getString("Password","")
                if(name==binding.txUsername.text.toString().trim()
                    && password==binding.txPassword1.text.toString().trim()){
//                    val i=Intent(this,)
                    val editor = preferences.edit()
                    editor.putBoolean("is login", binding.cbRememberMe.isChecked)
                    editor.apply()
                    editor.commit()

                    startActivity(Intent(this.requireContext(), MainActivity::class.java))
                }else
                    Toast.makeText(this.requireContext(),"Error userName or Password", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this.requireContext(),"Please fill the fields", Toast.LENGTH_SHORT).show()
        }

        binding.create.setOnClickListener {
            fragmentSwitcher(SignupFragment())
        }

        return binding.root
    }

    private fun fragmentSwitcher(fragment: Fragment){
        this.requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
    }
}