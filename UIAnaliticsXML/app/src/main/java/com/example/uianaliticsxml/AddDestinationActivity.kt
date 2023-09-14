package com.example.uianaliticsxml

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class AddDestinationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_destination)

        val destinationAddBtn = findViewById<Button>(R.id.destinationAddBtn)
        val destinationAddLongDescEdit = findViewById<EditText>(R.id.destinationAddLongDescEdit)
        val destinationAddShortDescEdit = findViewById<EditText>(R.id.destinationAddShortDescEdit)
        val destinationAddCountryEdit = findViewById<EditText>(R.id.destinationAddCountryEdit)
        val destinationAddNameEdit = findViewById<EditText>(R.id.destinationAddNameEdit)
        val destinationAddImg = findViewById<ImageView>(R.id.destinationAddImg)

        val randomImage = getRandomImage()
        destinationAddImg.setImageResource(randomImage)

        destinationAddBtn.setOnClickListener {
            val description = destinationAddLongDescEdit.text.toString()
            val shortDescription = destinationAddShortDescEdit.text.toString()
            val country = destinationAddCountryEdit.text.toString()
            val name = destinationAddNameEdit.text.toString()
            val destination = Destination((Math.random()*100).toInt(),name,shortDescription,description,country,randomImage,false)

            AppState.destinationList.add(destination)

            val intent = Intent(this@AddDestinationActivity, MainActivity::class.java)
            startActivity(intent)
        }




    }

    private fun getRandomImage(): Int {
        return listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
        ).random()
    }
}