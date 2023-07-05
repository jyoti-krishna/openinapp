package com.example.openinapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openinapp.models.TopLink

class toplinkadapter(private val context: Context): RecyclerView.Adapter<toplinkadapter.toplinkholder>() {
    val toplinklist= ArrayList<TopLink>()
    inner class toplinkholder(itemView: View):RecyclerView.ViewHolder(itemView){
        val linkname=itemView.findViewById<TextView>(R.id.textView3)
        val link=itemView.findViewById<TextView>(R.id.textView2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): toplinkholder {

        return toplinkholder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: toplinkholder, position: Int){
        val currentLink=toplinklist[position]
        holder.link.text=currentLink.app
        holder.linkname.text=currentLink.web_link
    }

    override fun getItemCount(): Int {
        return toplinklist.size
    }
    fun updatelist(newList:ArrayList<TopLink>){
        toplinklist.clear()
        toplinklist.addAll(newList)
        notifyDataSetChanged()
    }
}
