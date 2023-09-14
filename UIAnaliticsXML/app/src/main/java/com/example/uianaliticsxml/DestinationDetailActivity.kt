package com.example.uianaliticsxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView

class DestinationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)

        val destinationId =
            intent.getIntExtra("destinationClicked",0)
        val destination = AppState.destinationList.firstOrNull { it.id == destinationId }

        val detailImg = findViewById<ImageView>(R.id.detailImg)
        val titleTxt = findViewById<TextView>(R.id.detailTitle)
        val countryTxt = findViewById<TextView>(R.id.detailCountry)
        val shortDescTxt = findViewById<TextView>(R.id.detailShortDesc)
        val detailLongDescTxt = findViewById<TextView>(R.id.detailLongDesc)
        val haveVisitedSwitch = findViewById<Switch>(R.id.haveVisitedSwitch)

        haveVisitedSwitch.isChecked = destination?.isVisited ?: false

        haveVisitedSwitch.setOnCheckedChangeListener { compoundButton, value ->
            AppState.destinationList.firstOrNull { it.id == destinationId }?.let {
                it.isVisited = value
            }
        }

        destination?.let {
            detailImg.setImageResource(it.imageRes)
        }
        titleTxt.text = destination?.name
        countryTxt.text = destination?.country
        shortDescTxt.text = destination?.shortDescription
        detailLongDescTxt.text = destination?.description
    }
}