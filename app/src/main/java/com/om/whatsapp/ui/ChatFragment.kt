package com.om.whatsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.om.whatsapp.R
import com.om.whatsapp.adapter.ChatAdapter
import com.om.whatsapp.databinding.FragmentChatBinding
import com.om.whatsapp.modle.UserModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


     lateinit var binding:FragmentChatBinding
    private lateinit var database:FirebaseDatabase
    private lateinit var userList:ArrayList<UserModel>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentChatBinding.inflate(layoutInflater)
        database= FirebaseDatabase.getInstance()
        userList=ArrayList()
        database!!.reference.child("users")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                   userList.clear()
                    for(snapshot1 in snapshot.children){
                        val user=snapshot1.getValue(UserModel::class.java)
                        if(user!!.uid!=FirebaseAuth.getInstance().uid){
                            Log.d("TAG", "OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM")
                            userList.add(user)
                            Log.d("TAG", "${userList.size}")
                        }
                    }

                    binding.userListRecyclerView.adapter=ChatAdapter(requireContext(),userList)

                    binding.userListRecyclerView.adapter?.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("TAG", "Database error: ${error.message}")

                }
            })



        return binding.root
    }



}