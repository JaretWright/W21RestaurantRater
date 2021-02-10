package com.example.w21restaurantrater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.w21restaurantrater.databinding.ActivityRestaurantListBinding
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRestaurantListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //connect to the DB
        val db = FirebaseFirestore.getInstance().collection("restaurants")
        db.get().addOnSuccessListener { documents ->
            for (document in documents)
            {
                //this will show our restaurants in the log
                Log.i("DB_RESPONSE", "${document.data}")

                //this will create an instance of the restaurant object
                val restaurant = document.toObject(Restaurant::class.java)

                val textView = TextView(this)
                textView.text = restaurant.name
                textView.textSize = 20f

                binding.linearLayout.addView(textView)
            }
        }

    }
}