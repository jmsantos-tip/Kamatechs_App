package com.example.kamatechs_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kamatechs_app.databinding.ActivityStorageDetailsBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.signOutActivity -> {
                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                mAuth.signOut()
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Signed out successfully.", Toast.LENGTH_SHORT).show()
                true

            }
        }
        return super.onOptionsItemSelected(item)
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