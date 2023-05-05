package com.example.kamatechs_app

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kamatechs_app.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth





class HomeActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(
            this,
            R.layout.activity_home
        )
        supportActionBar?.title = "Home"

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

        binding.bottomNavigation.selectedItemId = R.id.homeActivity

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){

                R.id.homeActivity -> {
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
            R.id.aboutActivity -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
        }
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private  fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.homelayout,fragment)
        fragmentTransaction.addToBackStack(null).commit()
    }

}