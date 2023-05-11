package com.example.kamatechs_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.kamatechs_app.databinding.ActivityMelonBinding
import com.google.firebase.auth.FirebaseAuth

class MelonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMelonBinding>(this, R.layout.activity_melon)

        val actionbar = supportActionBar
        actionbar!!.title = "Melon"
        actionbar.setDisplayHomeAsUpEnabled(true)

        binding.bottomNavigation.selectedItemId = R.id.fruitsVegsActivity

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){

                R.id.homeActivity -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.fruitsVegsActivity -> {
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
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}