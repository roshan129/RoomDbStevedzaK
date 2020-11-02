package com.adivid.roomdbstevedzak.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adivid.roomdbstevedzak.R
import com.adivid.roomdbstevedzak.model.User
import com.adivid.roomdbstevedzak.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.et_f_name_update.setText(args.currentUser.firstName)
        view.et_l_name_update.setText(args.currentUser.lastName)
        view.et_age_update.setText(args.currentUser.age.toString())

        view.button_update.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val firstName = et_f_name_update.text.toString()
        val lastName = et_l_name_update.text.toString()
        val age = Integer.parseInt(et_age_update.text.toString())

        if (inputCheck(firstName, lastName, et_age_update.text)) {
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            userViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
            AlertDialog.Builder(requireContext()).apply {
                setPositiveButton("Yes") { _, _ ->

                    userViewModel.deleteUser(args.currentUser)
                    Toast.makeText(requireContext(), "Successfully Deleted: ${args.currentUser.firstName}",
                        Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                }
                setNegativeButton("No"){_,_->}
                setTitle("Delete ${args.currentUser.firstName}?")
                setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")

            }.create().show()



    }

    companion object {

    }
}