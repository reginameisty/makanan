package com.Magang.makanan

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.squareup.picasso.Picasso
import org.json.JSONArray


class Deskripsi : AppCompatActivity() {
    lateinit var gambar : ImageView
    lateinit var nama : TextView
    lateinit var resep : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deskripsi)

        gambar = findViewById(R.id.iconMakanan)
        nama = findViewById(R.id.namabrand)
        resep = findViewById(R.id.resep)

        AndroidNetworking.initialize(getApplicationContext());

        AndroidNetworking.get("https://playground-rest-api-vk3y7f3hta-et.a.run.app/foods")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    Picasso.get().load(response.getJSONObject(intent.getIntExtra("nama",0)).getString("image")).into(gambar);
                    nama.text = response.getJSONObject(intent.getIntExtra("nama",0)).getString("name")
                    resep.text = response.getJSONObject(intent.getIntExtra("nama",0)).getString("desc")
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })

    }
}