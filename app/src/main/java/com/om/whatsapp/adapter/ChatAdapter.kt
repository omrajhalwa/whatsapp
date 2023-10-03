package com.om.whatsapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.om.whatsapp.R
import com.om.whatsapp.databinding.ChatUserItemLayoutBinding
import com.om.whatsapp.modle.UserModel

//class ChatAdapter(var context:Context,var list: ArrayList<UserModel>): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {



    class ChatAdapter(var context: Context, var list: ArrayList<UserModel> = ArrayList()) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(){








    inner class ChatViewHolder(view: View):RecyclerView.ViewHolder(view){
        var binding :ChatUserItemLayoutBinding= ChatUserItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        return ChatViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_user_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        var user=list[position]

        Glide.with(context).load(user.imageUrl).into(holder.binding.userImage)
        holder.binding.userName?.text = user.name
       // holder.binding.userName!!.text=user.name
    }

    override fun getItemCount(): Int {

        return list.size
    }
}