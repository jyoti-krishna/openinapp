package com.example.openinapp

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openinapp.api.retrofitHelper
import com.example.openinapp.api.userService
import com.example.openinapp.models.TopLink
import com.example.openinapp.repo.userRepo
import com.example.openinapp.viewModels.mainViewModel
import com.example.openinapp.viewModels.vmFactory
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*
import kotlin.collections.ArrayList



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        setContentView(R.layout.activity_main)


        //declarations

        val textView = findViewById<TextView>(R.id.text_view_id)
        val click = findViewById<TextView>(R.id.links)
        val rv=findViewById<RecyclerView>(R.id.rv)
        val loc = findViewById<TextView>(R.id.loc)
        val barChart = findViewById<BarChart>(R.id.barchart)
        val dash = findViewById<TextView>(R.id.text_view_id1)
        val grt=findViewById<TextView>(R.id.grt)
        val userService= retrofitHelper.getInstance().create(userService::class.java)
        val db = ArrayList<BarEntry>()
        val chartPairs: MutableList<Pair<String, Int>> = mutableListOf()
        val repo=userRepo(userService)
        val mainViewModel=ViewModelProvider(this,vmFactory(repo)).get(mainViewModel::class.java)

        //observing viewmodel to get the name and other infos using api
        mainViewModel.user.observe(this, Observer {
            textView.text = it.support_whatsapp_number.toString()+"\uD83D\uDC4B"
            click.text=it.today_clicks.toString()
            loc.text=it.top_location
        })

        //greeting based on local time
        val greeting = getGreetingBasedOnTime()
        grt.text = greeting

        //toplink list by using recyclerview
        rv.layoutManager=LinearLayoutManager(this)
        val adapter=toplinkadapter(this)
        rv.adapter=adapter
        mainViewModel.user.observe(this, Observer {
            adapter.updatelist(it.data.top_links as ArrayList<TopLink>)
        })

        //chart
        db.add(BarEntry(2f,3f))
        db.add(BarEntry(3f,6f))
        db.add(BarEntry(4f,5f))
        db.add(BarEntry(5f,7f))
        db.add(BarEntry(6f,3f))
        db.add(BarEntry(7f,1f))
        db.add(BarEntry(8f,12f))
        db.add(BarEntry(9f,5f))
        val barDataSet = BarDataSet(db, "clicks per day")
        barDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)
        barChart.setFitBars(true)
        barChart.data = barData
        barChart.animateY(10)



    }
    fun getGreetingBasedOnTime(): String {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

        return if (hourOfDay in 0..11) {
            "Good Morning"
        } else {
            "Good Evening"
        }
    }
}