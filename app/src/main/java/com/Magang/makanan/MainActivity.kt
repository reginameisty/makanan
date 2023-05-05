package com.Magang.makanan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var Listing : RecyclerView
    lateinit var Datacontext : Data
    lateinit var NamaListing : ArrayList<String>
    lateinit var Brand : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        NamaListing = ArrayList()
        Brand = ArrayList()
        Listing = findViewById(R.id.dataList)
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.get("https://playground-rest-api-vk3y7f3hta-et.a.run.app/foods")
            .addHeaders("application","application/json")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                    if (response != null) {
                        for (i in 1..response.length()) {
                            NamaListing.add(response.getJSONObject(i - 1).getString("name"))
                            Brand.add(response.getJSONObject(i - 1).getString("image"))
                            Datacontext = Data(NamaListing, Brand, applicationContext)
                            Listing.adapter = Datacontext
                        }
                    }
                }

                override fun onError(error: ANError) {

                }
            })



    }
}