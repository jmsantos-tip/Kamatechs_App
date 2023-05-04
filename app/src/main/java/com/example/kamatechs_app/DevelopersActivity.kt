package com.example.kamatechs_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DevelopersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developers)

        val actionbar = supportActionBar
        actionbar!!.title = "Developers"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}