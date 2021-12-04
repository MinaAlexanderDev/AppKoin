package com.mina.localdatabaseapp.ui.adapter

import com.mina.localdatabaseapp.model.entitymodel.User


interface OnListItemClick {
    fun onItemDelete(user: User)
    fun onItemEdit(user: User)
//    fun onItemSelect(user: User)
    fun onItemSearchUser(user: User)
}