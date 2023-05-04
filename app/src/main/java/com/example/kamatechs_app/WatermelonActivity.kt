package com.example.kamatechs_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WatermelonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watermelon)

        val actionbar = supportActionBar
        actionbar!!.title = "Watermelon"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}