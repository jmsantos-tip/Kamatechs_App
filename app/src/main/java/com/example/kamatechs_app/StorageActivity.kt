package com.example.kamatechs_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kamatechs_app.databinding.ActivityStorageBinding
import com.google.firebase.database.*

class StorageActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var storageRecyclerview : RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var storageArrayList : ArrayList<Storage>


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