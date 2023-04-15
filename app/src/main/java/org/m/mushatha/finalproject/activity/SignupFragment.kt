package org.m.mushatha.finalproject.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.m.mushatha.finalproject.R
import org.m.mushatha.finalproject.databinding.FragmentSignupBinding
import java.util.*

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.btnSignUp.setOnClickListener {
            val preferences = this.requireActivity().getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
            val editor = preferences.edit()
            val name = binding.txname.text.toString().trim()
            val password = binding.txPassword.text.toString().trim()
            val email1 = binding.txemail1.text.toString().trim()
            val date = binding.txdate.text.toString().trim()

            if(
                binding.txname.text.isNotEmpty() &&
                binding.txPassword.text.isNotEmpty() &&
                binding.txemail1.text.isNotEmpty() &&
                binding.txdate.text.isNotEmpty()
                    ){
                    editor.putString("name", name)
                    editor.putString("Password", password)
                    editor.putString("email1", email1)
                    editor.putString("date", date)
                    editor.putBoolean("is login", false)
                    editor.apply()
                    editor.commit()
                Toast.makeText(this.requireContext(),"Account is created.",Toast.LENGTH_SHORT).show()
                fragmentSwitcher(LoginFragment())
            }else
                Toast.makeText(this.requireContext(),"Please fill the fields",Toast.LENGTH_SHORT).show()
        }

        binding.btnSignin.setOnClickListener {
            fragmentSwitcher(LoginFragment())
        }

        binding.txdate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val picker = DatePickerDialog(this.requireContext(),
                { _, y, m, d ->
                    binding.txdate.setText("$y / ${m + 1} / $d")
                }, year, month, day
            )
            picker.show()
        }

        return binding.root

    }


    private fun fragmentSwitcher(fragment: Fragment){
        this.requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()

    }
      



}