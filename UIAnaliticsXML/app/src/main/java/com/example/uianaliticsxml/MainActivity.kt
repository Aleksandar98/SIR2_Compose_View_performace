package com.example.uianaliticsxml

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var adapter:DestinationAdapter
    override fun onResume() {
        adapter.setNewDataList(AppState.destinationList)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyView = findViewById<RecyclerView>(R.id.recycler_view)

        val bottomNav = findViewById(R.id.bottom_navigation) as BottomNavigationView


        adapter = DestinationAdapter(mutableListOf(), object : DestinationAdapter.DestinationClickListener {
            override fun onDestinationClicked(destination: Destination) {
                val intent = Intent(this@MainActivity, DestinationDetailActivity::class.java)
                intent.putExtra("destinationClicked", destination)
                startActivity(intent)
            }

        })

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.traveled_screen -> {
                    adapter.setNewDataList(AppState.destinationList.filter { it.isVisited })
                    true
                }
                R.id.add_destination_screen -> {
                    val intent = Intent(this@MainActivity, AddDestinationActivity::class.java)
                    startActivity(intent)
                    true
                }
                else->{true}
            }
        }

        recyView.layoutManager = LinearLayoutManager(this)
        recyView.adapter = adapter
    }


}