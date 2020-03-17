package com.example.appgit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appgit.network.GitRepo

class Adapter(val lista: List<GitRepo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.nombre.text = item.name
        holder.descr.text= item.description
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nombre: TextView = itemView.findViewById(R.id.textView2)
        val descr: TextView = itemView.findViewById(R.id.textView3)
    }
}

class GitRepoDiffCallback : DiffUtil.ItemCallback<GitRepo>() {
    override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
        return oldItem == newItem
    }
}