package com.adivid.roomdbstevedzak.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adivid.roomdbstevedzak.R
import com.adivid.roomdbstevedzak.model.User
import kotlinx.android.synthetic.main.list_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_layout,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.tv_id.text = currentItem.id.toString()
        holder.itemView.tv_f_name.text = currentItem.firstName
        holder.itemView.tv_l_name.text = currentItem.lastName
        holder.itemView.tv_age.text = currentItem.age.toString()

        holder.itemView.listLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount() = userList.size

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}