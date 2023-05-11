package com.example.kamatechs_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kamatechs_app.databinding.ActivityStorageBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class StorageActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var storageRecyclerview : RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var storageArrayList : ArrayList<Storage>

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityStorageBinding>(this, R.layout.activity_storage)

        storageRecyclerview = findViewById(R.id.storageList)
        storageRecyclerview.layoutManager = LinearLayoutManager(this)
        storageRecyclerview.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        storageArrayList = arrayListOf<Storage>()
        getStorageData()

        val actionbar = supportActionBar
        actionbar!!.title = "Storage Device Status"
        actionbar.setDisplayHomeAsUpEnabled(true)

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
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getStorageData() {

        storageRecyclerview.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbref = FirebaseDatabase.getInstance().getReference("test")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                storageArrayList.clear()

                if (snapshot.exists()) {

                    for (storageSnapshot in snapshot.children) {
                        for (item in storageSnapshot.children) {
                            val storage = storageSnapshot.getValue(Storage::class.java)
                            storageArrayList.add(storage!!)
                        }
                    }
                    val mAdapter = MyAdapter(storageArrayList)
                    storageRecyclerview.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@StorageActivity, StorageDetailsActivity::class.java)
                            intent.putExtra("dateTimeVal", storageArrayList[position].dateTime.toString())
                            intent.putExtra("tempVal", storageArrayList[position].temperature.toString())
                            intent.putExtra("humidVal", storageArrayList[position].humidity.toString())
                            intent.putExtra("roomTempVal", storageArrayList[position].room_temperature.toString())
                            startActivity(intent)
                        }
                    })
                    storageRecyclerview.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}