package com.example.kamatechs_app

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kamatechs_app.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView


class HomeActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(
            this,
            R.layout.activity_home
        )
        supportActionBar?.title = "Home"

        binding.btnStorage.setOnClickListener {
            startActivity(Intent(this, StorageActivity::class.java))
            true
        }

        binding.btnFV.setOnClickListener {
            startActivity(Intent(this, FruitsVegsActivity::class.java))
            true
        }


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

                R.id.storageActivity -> {
                    startActivity(Intent(this, StorageActivity::class.java))
                    true
                }

                R.id.fruitsVegsActivity -> {
                    startActivity(Intent(this, FruitsVegsActivity::class.java))
                    true
                }
                R.id.aboutFragment -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.FAQFragment -> {
                    replaceFragment(FAQFragment())
                    true
                }
                R.id.developersFragment -> {
                    replaceFragment(DevelopersFragment())
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){

                R.id.homeActivity -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.aboutFragment -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.FAQFragment -> {
                    replaceFragment(FAQFragment())
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
            R.id.aboutFragment -> {
                replaceFragment(AboutFragment())
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