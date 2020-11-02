package com.adivid.roomdbstevedzak.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.adivid.roomdbstevedzak.R
import com.adivid.roomdbstevedzak.viewmodel.UserViewModel
import com.adivid.roomdbstevedzak.model.User
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.button.setOnClickListener {
            insertIntoDatabase()
        }
        return view
    }

    private fun insertIntoDatabase() {
        val firstName = et_f_name.text.toString()
        val lastName = et_l_name.text.toString()
        val age = et_age.text


        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),
                "Please fill out all fields", Toast.LENGTH_SHORT).show()

        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }

    companion object {
        private const val TAG = "AddFragment"
    }
}