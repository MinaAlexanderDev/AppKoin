package com.mina.localdatabaseapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmedtawfik.kotlinappnavigation.model.entity.User
import com.mina.localdatabaseapp.R

class UserRecyclerView : RecyclerView.Adapter<UserRecyclerView.UserViewHolder>() {

    var onListItemClick: OnListItemClick? = null
    var userList: List<User> = emptyList()

    fun setList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDelete: ImageView = itemView.findViewById(R.id.tv_delete)
        var tvEdit: ImageView = itemView.findViewById(R.id.tv_edit)
        var tvUserName: TextView = itemView.findViewById(R.id.tv_item_userName)
        var tvMessage: TextView = itemView.findViewById(R.id.tv_item_message)

        fun bind(user: User) {
//            iv_userImage.setImageResource(user.imageId)
            tvUserName.text = user.name
            tvMessage.text = user.message
            tvDelete.setOnClickListener{
                onListItemClick?.onItemDelete(user)
            }
            tvEdit.setOnClickListener {


            }
            tvEdit.setOnClickListener {
                onListItemClick?.onItemEdit(user)
            }
            itemView.setOnClickListener{
                onListItemClick?.onItemSelect(user)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var user: User = userList.get(position)
        holder.bind(user)
    }

}