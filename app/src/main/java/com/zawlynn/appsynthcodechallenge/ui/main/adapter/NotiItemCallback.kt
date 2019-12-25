package com.zawlynn.appsynthcodechallenge.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.NotiInfo
class NotiItemCallback : DiffUtil.ItemCallback<NotiInfo>() {
    override fun areItemsTheSame(oldItem: NotiInfo, newItem: NotiInfo): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: NotiInfo, newItem: NotiInfo): Boolean {
        return oldItem == newItem
    }
}
