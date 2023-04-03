package com.example.kamatechs_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class StorageActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var storageRecyclerview : RecyclerView
    private lateinit var storageArrayList : ArrayList<Storage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        storageRecyclerview = findViewById(R.id.storageList)
        storageRecyclerview.layoutManager = LinearLayoutManager(this)
        storageRecyclerview.setHasFixedSize(true)
        storageArrayList = arrayListOf<Storage>()
        getStorageData()

        val actionbar = supportActionBar
        actionbar!!.title = "Storage Device Status"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    private fun getStorageData() {

        dbref = FirebaseDatabase.getInstance().getReference("test")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (storageSnapshot in snapshot.children) {
                        for (item in storageSnapshot.children) {
                            val storage = storageSnapshot.getValue(Storage::class.java)
                            storageArrayList.add(storage!!)
                        }
                    }

                    storageRecyclerview.adapter = MyAdapter(storageArrayList)


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