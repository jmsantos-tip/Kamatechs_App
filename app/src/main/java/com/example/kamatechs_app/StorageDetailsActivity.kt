package com.example.kamatechs_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.kamatechs_app.databinding.ActivityStorageDetailsBinding

class StorageDetailsActivity : AppCompatActivity() {

    private lateinit var tvDateTime: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvHumid: TextView
    private lateinit var tvRoomTemp: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityStorageDetailsBinding>(this, R.layout.activity_storage_details)

        initView()
        setValuesToViews()

        val actionbar = supportActionBar
        actionbar!!.title = "Storage Information"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        binding.bottomNavigation.selectedItemId = R.id.storageActivity

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){

                R.id.homeActivity -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.fruitsVegsActivity -> {
                    startActivity(Intent(this, FruitsVegsActivity::class.java))
                    true
                }
                R.id.storageActivity -> {
                    startActivity(Intent(this, StorageActivity::class.java))
                    true
                }
                R.id.aboutActivity -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    true
                }
                else -> false
            }
        }
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