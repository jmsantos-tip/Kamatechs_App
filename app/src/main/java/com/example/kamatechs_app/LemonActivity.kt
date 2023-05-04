package com.example.kamatechs_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LemonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lemon)

        val actionbar = supportActionBar
        actionbar!!.title = "Lemon"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}