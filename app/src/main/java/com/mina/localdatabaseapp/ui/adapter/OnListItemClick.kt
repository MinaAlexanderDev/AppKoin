package com.mina.localdatabaseapp.ui.adapter

import com.ahmedtawfik.kotlinappnavigation.model.entity.User


interface OnListItemClick {
    fun onItemDelete(user: User)
    fun onItemEdit(user: User)
    fun onItemSelect(user: User)
}