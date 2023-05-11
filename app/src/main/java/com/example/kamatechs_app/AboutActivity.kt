package com.example.kamatechs_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kamatechs_app.databinding.ActivityAboutBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class AboutActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAboutBinding>(this, R.layout.activity_about)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView : NavigationView = binding.navView

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
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
                R.id.FAQActivity -> {
                    startActivity(Intent(this, FAQActivity::class.java))
                    true
                }
                R.id.developersActivity -> {
                    startActivity(Intent(this, DevelopersActivity::class.java))
                    true
                }
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

                else -> false
            }
        }

        binding.bottomNavigation.selectedItemId = R.id.aboutActivity

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
                    true
                }
                else -> false
            }
        }


        val actionbar = supportActionBar
        actionbar!!.title = "About"
//        actionbar.setDisplayHomeAsUpEnabled(true)
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
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }
}