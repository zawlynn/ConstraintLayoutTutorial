package com.zawlynn.codigo.assignment.ui.main


import android.view.ViewGroup

import androidx.annotation.NonNull
import androidx.recyclerview.widget.ListAdapter
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.NotiInfo
import com.zawlynn.appsynthcodechallenge.ui.main.adapter.NotiItemCallback

import com.zawlynn.codigo.assignment.ui.main.viewholder.MyViewHolder



class RecAdapter : ListAdapter<NotiInfo, MyViewHolder>(NotiItemCallback()) {
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, i: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun onBindViewHolder(@NonNull holder: MyViewHolder, i: Int) {
        holder.bind(getItem(i),i)
    }
}
