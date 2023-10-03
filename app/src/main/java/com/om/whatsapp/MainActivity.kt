package com.om.whatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.om.whatsapp.activity.NumberActivity
import com.om.whatsapp.adapter.ViewPagerAdapter
import com.om.whatsapp.databinding.ActivityMainBinding
import com.om.whatsapp.ui.CallFragment
import com.om.whatsapp.ui.ChatFragment
import com.om.whatsapp.ui.StatusFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()


        if (auth.currentUser==null){
            val intent= Intent(this, NumberActivity::class.java)
            startActivity(intent)
            finish()
        }
        val fragmentArrayList=ArrayList<Fragment>()

        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())

        val adapter= ViewPagerAdapter(this,supportFragmentManager,fragmentArrayList)
        binding.viewpager.adapter=adapter
        binding.tabs.setupWithViewPager(binding.viewpager)



    }
}