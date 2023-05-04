package com.example.kamatechs_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class JicamaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jicama)

        val actionbar = supportActionBar
        actionbar!!.title = "Jicama"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}