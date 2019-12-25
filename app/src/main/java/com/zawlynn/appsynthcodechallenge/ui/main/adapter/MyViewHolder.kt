package com.zawlynn.codigo.assignment.ui.main.viewholder


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zawlynn.appsynthcodechallenge.R
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.NotiInfo
import kotlinx.android.synthetic.main.noti_item.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class MyViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.noti_item, parent, false)) {
    val context : Context
    init {
        context=parent.context
    }
    fun bind(data: NotiInfo, position : Int) {
        itemView.tv_header.setText(data.text)
        itemView.tv_desc.setText(data.created.getDateWithServerTimeStamp())
        if(position%2==0){
            itemView.item_layout.background = ContextCompat.getDrawable(context,R.color.grey)
        }else{
            itemView.item_layout.background = ContextCompat.getDrawable(context,R.color.white)
        }
    }

    fun String.getDateWithServerTimeStamp(): String {
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        try {
            return dateFormat.parse(this).formatTo("dd MMM yyyy hh:mm a")
        } catch (e: ParseException) {
            return "error format"
        }
    }
    fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(this)
    }
}