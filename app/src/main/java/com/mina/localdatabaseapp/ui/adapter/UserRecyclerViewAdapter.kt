package com.mina.localdatabaseapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mina.localdatabaseapp.model.entitymodel.User
import com.mina.localdatabaseapp.R

class UserRecyclerViewAdapter : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {

    var onListItemClick: OnListItemClick? = null
    var userList: List<User> = emptyList()

    fun setList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var btnDelete: ImageView = itemView.findViewById(R.id.bt_delete)
        private var btnEdit: ImageView = itemView.findViewById(R.id.bt_edit)
        private var tvUserName: TextView = itemView.findViewById(R.id.tv_item_userName)
        private var tvMessage: TextView = itemView.findViewById(R.id.tv_item_title)
        private var tvId: TextView = itemView.findViewById(R.id.tv_item_id)
        fun bind(user: User) {
            tvUserName.text = user.name
            tvMessage.text = user.message
            tvId.text = user.id.toString()

            btnDelete.setOnClickListener {
                onListItemClick?.onItemDelete(user)
            }

            btnEdit.setOnClickListener {
                onListItemClick?.onItemEdit(user)
            }
//            itemView.setOnClickListener {
//                onListItemClick?.onItemSelect(user)
//
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = userList.get(position)
        holder.bind(user)
    }

}