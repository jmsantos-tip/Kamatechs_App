package com.example.kamatechs_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class StorageDetailsActivity : AppCompatActivity() {

    private lateinit var tvDateTime: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvHumid: TextView
    private lateinit var tvRoomTemp: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_details)

        initView()
        setValuesToViews()

        val actionbar = supportActionBar
        actionbar!!.title = "Storage Information"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        tvDateTime = findViewById(R.id.tvDateTime)
        tvTemp = findViewById(R.id.tvTemp)
        tvHumid = findViewById(R.id.tvHumid)
        tvRoomTemp = findViewById(R.id.tvRoomTemp)

    }

    private fun setValuesToViews() {
        tvDateTime.text = intent.getStringExtra("dateTimeVal")
        tvTemp.text = intent.getStringExtra("tempVal")
        tvHumid.text = intent.getStringExtra("humidVal")
        tvRoomTemp.text = intent.getStringExtra("roomTempVal")

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}