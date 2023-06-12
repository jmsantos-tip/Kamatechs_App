package com.example.kamatechs_app

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kamatechs_app.databinding.ActivityFruitsVegsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class FruitsVegsActivity : AppCompatActivity() {

    private var deviceName: String? = null
    private var deviceAddress: String? = null
    private lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFruitsVegsBinding>(this, R.layout.activity_fruits_vegs)

        val actionbar = supportActionBar
        actionbar!!.title = "Storage Configuration"
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


        // UI Initialization
        val buttonConnect = findViewById<Button>(R.id.buttonConnect)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle = findViewById<Button>(R.id.buttonToggle)
        buttonToggle.isEnabled = false
        val imageView2 = findViewById<ImageView>(R.id.imageView2)
        imageView2.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle2 = findViewById<Button>(R.id.buttonToggle2)
        buttonToggle2.isEnabled = false
        val imageView4 = findViewById<ImageView>(R.id.imageView4)
        imageView4.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle4 = findViewById<Button>(R.id.buttonToggle4)
        buttonToggle4.isEnabled = false
        val imageView5 = findViewById<ImageView>(R.id.imageView16)
        imageView5.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle5 = findViewById<Button>(R.id.buttonToggle14)
        buttonToggle5.isEnabled = false
        val imageView6 = findViewById<ImageView>(R.id.imageView15)
        imageView6.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle6 = findViewById<Button>(R.id.buttonToggle15)
        buttonToggle6.isEnabled = false
        val imageView7 = findViewById<ImageView>(R.id.imageView14)
        imageView7.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle7 = findViewById<Button>(R.id.buttonToggle16)
        buttonToggle7.isEnabled = false
        val imageView8 = findViewById<ImageView>(R.id.imageView13)
        imageView8.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle8 = findViewById<Button>(R.id.buttonToggle13)
        buttonToggle8.isEnabled = false
        val imageView9 = findViewById<ImageView>(R.id.imageView33)
        imageView9.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle9 = findViewById<Button>(R.id.buttonToggle33)
        buttonToggle9.isEnabled = false
        val imageView10 = findViewById<ImageView>(R.id.imageView34)
        imageView10.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle10 = findViewById<Button>(R.id.buttonToggle36)
        buttonToggle10.isEnabled = false
        val imageView11 = findViewById<ImageView>(R.id.imageView35)
        imageView11.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle11 = findViewById<Button>(R.id.buttonToggle35)
        buttonToggle11.isEnabled = false
        val imageView12 = findViewById<ImageView>(R.id.imageView37)
        imageView12.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle12 = findViewById<Button>(R.id.buttonToggle39)
        buttonToggle12.isEnabled = false
        val imageView13 = findViewById<ImageView>(R.id.imageView38)
        imageView13.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle13 = findViewById<Button>(R.id.buttonToggle38)
        buttonToggle13.isEnabled = false
        val imageView14 = findViewById<ImageView>(R.id.imageView39)
        imageView14.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle14 = findViewById<Button>(R.id.buttonToggle37)
        buttonToggle14.isEnabled = false
        val imageView15 = findViewById<ImageView>(R.id.imageView40)
        imageView15.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle15 = findViewById<Button>(R.id.buttonToggle40)
        buttonToggle15.isEnabled = false
        val imageView16 = findViewById<ImageView>(R.id.imageView36)
        imageView16.setBackgroundColor(resources.getColor(R.color.colorOff))
        val buttonToggle16 = findViewById<Button>(R.id.buttonToggle34)
        buttonToggle16.isEnabled = false

        //Clickable fruits
        imageView.setOnClickListener {
            startActivity(Intent(this, RipeTomatoActivity::class.java))
            true
        }

        imageView2.setOnClickListener {
            startActivity(Intent(this, GreenTomatoActivity::class.java))
            true
        }

        imageView4.setOnClickListener {
            startActivity(Intent(this, CucumberActivity::class.java))
            true
        }

        imageView5.setOnClickListener {
            startActivity(Intent(this, GreenBananaActivity::class.java))
            true
        }

        imageView6.setOnClickListener {
            startActivity(Intent(this, RipeBananaActivity::class.java))
            true
        }

        imageView7.setOnClickListener {
            startActivity(Intent(this, WatermelonActivity::class.java))
            true
        }

        imageView8.setOnClickListener {
            startActivity(Intent(this, GingerRootActivity::class.java))
            true
        }

        imageView9.setOnClickListener {
            startActivity(Intent(this, JicamaActivity::class.java))
            true
        }

        imageView10.setOnClickListener {
            startActivity(Intent(this, SweetPotatoActivity::class.java))
            true
        }

        imageView11.setOnClickListener {
            startActivity(Intent(this, CoconutActivity::class.java))
            true
        }

        imageView12.setOnClickListener {
            startActivity(Intent(this, MelonActivity::class.java))
            true
        }

        imageView13.setOnClickListener {
            startActivity(Intent(this, PumpkinActivity::class.java))
            true
        }

        imageView14.setOnClickListener {
            startActivity(Intent(this, BasilActivity::class.java))
            true
        }

        imageView15.setOnClickListener {
            startActivity(Intent(this, LemonActivity::class.java))
            true
        }

        imageView16.setOnClickListener {
            startActivity(Intent(this, GrapeFruitActivity::class.java))
            true
        }


        // If a bluetooth device has been selected from SelectDeviceActivity
        deviceName = intent.getStringExtra("deviceName")
        if (deviceName != null) {
            // Get the device address to make BT Connection
            deviceAddress = intent.getStringExtra("deviceAddress")
            // Show progree and connection status
            toolbar.subtitle = "Connecting to $deviceName..."
            progressBar.visibility = View.VISIBLE
            buttonConnect.isEnabled = false

            /*
            This is the most important piece of code. When "deviceName" is found
            the code will call a new thread to create a bluetooth connection to the
            selected device (see the thread code below)
             */
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            createConnectThread = CreateConnectThread(bluetoothAdapter, deviceAddress)
            createConnectThread!!.start()
        }

        /*
        Second most important piece of Code. GUI Handler
         */handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    CONNECTING_STATUS -> when (msg.arg1) {
                        1 -> {
                            toolbar.subtitle = "Connected to $deviceName"
                            progressBar.visibility = View.GONE
                            buttonConnect.isEnabled = true
                            buttonToggle.isEnabled = true
                            buttonToggle2.isEnabled = true
                            buttonToggle4.isEnabled = true
                            buttonToggle5.isEnabled = true
                            buttonToggle6.isEnabled = true
                            buttonToggle7.isEnabled = true
                            buttonToggle8.isEnabled = true
                            buttonToggle9.isEnabled = true
                            buttonToggle10.isEnabled = true
                            buttonToggle11.isEnabled = true
                            buttonToggle12.isEnabled = true
                            buttonToggle13.isEnabled = true
                            buttonToggle14.isEnabled = true
                            buttonToggle15.isEnabled = true
                            buttonToggle16.isEnabled = true
                        }
                        -1 -> {
                            toolbar.subtitle = "Device fails to connect"
                            progressBar.visibility = View.GONE
                            buttonConnect.isEnabled = true
                        }
                    }
                    MESSAGE_READ -> {
                        val arduinoMsg = msg.obj.toString() // Read message from Arduino
                        when (arduinoMsg.lowercase(Locale.getDefault())) {
                            "ripe tomato 1" -> {
                                imageView.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "ripe tomato 0" -> {
                                imageView.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "mature green 1" -> {
                                imageView2.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "mature green 0" -> {
                                imageView2.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "cucumber 1" -> {
                                imageView4.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "cucumber 0" -> {
                                imageView4.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "green banana 1" -> {
                                imageView5.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "green banana 0" -> {
                                imageView5.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "ripe banana 1" -> {
                                imageView6.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "ripe banana 0" -> {
                                imageView6.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "watermelon 1" -> {
                                imageView7.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "watermelon 0" -> {
                                imageView7.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "ginger root 1" -> {
                                imageView8.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "ginger root 0" -> {
                                imageView8.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "jicama 1" -> {
                                imageView9.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "jicama 0" -> {
                                imageView9.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "sweet potato 1" -> {
                                imageView10.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "sweet potato 0" -> {
                                imageView10.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "coconut 1" -> {
                                imageView11.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "coconut 0" -> {
                                imageView11.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "melon 1" -> {
                                imageView12.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "melon 0" -> {
                                imageView12.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "pumpkin 1" -> {
                                imageView13.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "pumpkin 0" -> {
                                imageView13.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "basil 1" -> {
                                imageView14.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "basil 0" -> {
                                imageView14.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "lemon 1" -> {
                                imageView15.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "lemon 0" -> {
                                imageView15.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                            "grape fruit 1" -> {
                                imageView16.setBackgroundColor(resources.getColor(R.color.colorOn))
                            }
                            "grape fruit 0" -> {
                                imageView16.setBackgroundColor(resources.getColor(R.color.colorOff))
                            }
                        }
                    }
                }
            }
        }

        // Select Bluetooth Device
        buttonConnect.setOnClickListener { // Move to adapter list
            val intent = Intent(this@FruitsVegsActivity, SelectDeviceActivity::class.java)
            startActivity(intent)
        }

        // Button to ON/OFF LED on Arduino Board
        buttonToggle.setOnClickListener {
            var cmdText: String? = null
            val btnState = buttonToggle.text.toString().lowercase(Locale.getDefault())
            when(btnState) {
                "ripe tomato" -> {
                    buttonToggle.text = "Selected"
                    cmdText = "<ripe tomato 1>"
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle.text = "Ripe Tomato"
                    cmdText = "<ripe tomato 0>"
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            // Send command to Arduino board
            connectedThread!!.write(cmdText)
        }
        buttonToggle2.setOnClickListener {
            var cmdText: String? = null
            val btnState2 = buttonToggle2.text.toString().lowercase(Locale.getDefault())
            when(btnState2){
                "mature green tomato" -> {
                    buttonToggle2.text = "Selected"
                    cmdText = "<mature green 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle2.text = "Mature Green Tomato"
                    cmdText = "<mature green 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle4.setOnClickListener {
            var cmdText: String? = null
            val btnState4 = buttonToggle4.text.toString().lowercase(Locale.getDefault())
            when(btnState4){
                "cucumber" -> {
                    buttonToggle4.text = "Selected"
                    cmdText = "<cucumber 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle4.text = "Cucumber"
                    cmdText = "<cucumber 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle5.setOnClickListener {
            var cmdText: String? = null
            val btnState5 = buttonToggle5.text.toString().lowercase(Locale.getDefault())
            when(btnState5){
                "green banana" -> {
                    buttonToggle5.text = "Selected"
                    cmdText = "<green banana 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle5.text = "Green Banana"
                    cmdText = "<green banana 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle6.setOnClickListener {
            var cmdText: String? = null
            val btnState6 = buttonToggle6.text.toString().lowercase(Locale.getDefault())
            when(btnState6){
                "ripe banana" -> {
                    buttonToggle6.text = "Selected"
                    cmdText = "<ripe banana 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle6.text = "Ripe Banana"
                    cmdText = "<ripe banana 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle7.setOnClickListener {
            var cmdText: String? = null
            val btnState7 = buttonToggle7.text.toString().lowercase(Locale.getDefault())
            when(btnState7){
                "watermelon" -> {
                    buttonToggle7.text = "Selected"
                    cmdText = "<watermelon 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle7.text = "Watermelon"
                    cmdText = "<watermelon 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle8.setOnClickListener {
            var cmdText: String? = null
            val btnState8 = buttonToggle8.text.toString().lowercase(Locale.getDefault())
            when(btnState8){
                "ginger root" -> {
                    buttonToggle8.text = "Selected"
                    cmdText = "<ginger root 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle8.text = "Ginger Root"
                    cmdText = "<ginger root 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle9.setOnClickListener {
            var cmdText: String? = null
            val btnState9 = buttonToggle9.text.toString().lowercase(Locale.getDefault())
            when(btnState9){
                "jicama" -> {
                    buttonToggle9.text = "Selected"
                    cmdText = "<jicama 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle9.text = "Jicama"
                    cmdText = "<jicama 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle10.setOnClickListener {
            var cmdText: String? = null
            val btnState10 = buttonToggle10.text.toString().lowercase(Locale.getDefault())
            when(btnState10){
                "sweet potato" -> {
                    buttonToggle10.text = "Selected"
                    cmdText = "<sweet potato 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle10.text = "Sweet Potato"
                    cmdText = "<sweet potato 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle11.setOnClickListener {
            var cmdText: String? = null
            val btnState11 = buttonToggle11.text.toString().lowercase(Locale.getDefault())
            when(btnState11){
                "coconut" -> {
                    buttonToggle11.text = "Selected"
                    cmdText = "<coconut 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle11.text = "Coconut"
                    cmdText = "<coconut 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle12.setOnClickListener {
            var cmdText: String? = null
            val btnState12 = buttonToggle12.text.toString().lowercase(Locale.getDefault())
            when(btnState12){
                "melon" -> {
                    buttonToggle12.text = "Selected"
                    cmdText = "<melon 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle12.text = "melon"
                    cmdText = "<melon 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle13.setOnClickListener {
            var cmdText: String? = null
            val btnState13 = buttonToggle13.text.toString().lowercase(Locale.getDefault())
            when(btnState13){
                "pumpkin" -> {
                    buttonToggle13.text = "Selected"
                    cmdText = "<pumpkin 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle13.text = "Pumpkin"
                    cmdText = "<pumpkin 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle14.setOnClickListener {
            var cmdText: String? = null
            val btnState14 = buttonToggle14.text.toString().lowercase(Locale.getDefault())
            when(btnState14){
                "basil" -> {
                    buttonToggle14.text = "Selected"
                    cmdText = "<basil 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle15.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle14.text = "Basil"
                    cmdText = "<basil 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle15.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle15.setOnClickListener {
            var cmdText: String? = null
            val btnState15 = buttonToggle15.text.toString().lowercase(Locale.getDefault())
            when(btnState15){
                "lemon" -> {
                    buttonToggle15.text = "Selected"
                    cmdText = "<lemon 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle16.isEnabled = false
                }
                "selected" -> {
                    buttonToggle15.text = "lemon"
                    cmdText = "<lemon 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle16.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
        buttonToggle16.setOnClickListener {
            var cmdText: String? = null
            val btnState16 = buttonToggle16.text.toString().lowercase(Locale.getDefault())
            when(btnState16){
                "grape fruit" -> {
                    buttonToggle16.text = "Selected"
                    cmdText = "<grape fruit 1>"
                    buttonToggle.isEnabled = false
                    buttonToggle2.isEnabled = false
                    buttonToggle4.isEnabled = false
                    buttonToggle5.isEnabled = false
                    buttonToggle6.isEnabled = false
                    buttonToggle7.isEnabled = false
                    buttonToggle8.isEnabled = false
                    buttonToggle9.isEnabled = false
                    buttonToggle10.isEnabled = false
                    buttonToggle11.isEnabled = false
                    buttonToggle12.isEnabled = false
                    buttonToggle13.isEnabled = false
                    buttonToggle14.isEnabled = false
                    buttonToggle15.isEnabled = false
                }
                "selected" -> {
                    buttonToggle16.text = "Grape fruit"
                    cmdText = "<grape fruit 0>"
                    buttonToggle.isEnabled = true
                    buttonToggle2.isEnabled = true
                    buttonToggle4.isEnabled = true
                    buttonToggle5.isEnabled = true
                    buttonToggle6.isEnabled = true
                    buttonToggle7.isEnabled = true
                    buttonToggle8.isEnabled = true
                    buttonToggle9.isEnabled = true
                    buttonToggle10.isEnabled = true
                    buttonToggle11.isEnabled = true
                    buttonToggle12.isEnabled = true
                    buttonToggle13.isEnabled = true
                    buttonToggle14.isEnabled = true
                    buttonToggle15.isEnabled = true
                }
            }
            connectedThread!!.write(cmdText)
        }
    }


    /* ============================ Thread to Create Bluetooth Connection =================================== */
    class CreateConnectThread(bluetoothAdapter: BluetoothAdapter, address: String?) : Thread() {
        init {
            /*
            Use a temporary object that is later assigned to mmSocket
            because mmSocket is final.
             */
            val bluetoothDevice = bluetoothAdapter.getRemoteDevice(address)
            var tmp: BluetoothSocket? = null
            val uuid = bluetoothDevice.uuids[0].uuid
            try {
                /*
                Get a BluetoothSocket to connect with the given BluetoothDevice.
                Due to Android device varieties,the method below may not work fo different devices.
                You should try using other methods i.e. :
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
                 */
                tmp = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid)
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "Socket's create() method failed", e)
            }
            mmSocket = tmp
        }

        override fun run() {
            // Cancel discovery because it otherwise slows down the connection.
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            bluetoothAdapter.cancelDiscovery()
            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket!!.connect()
                Log.e("Status", "Device connected")
                handler!!.obtainMessage(CONNECTING_STATUS, 1, -1).sendToTarget()
            } catch (connectException: IOException) {
                // Unable to connect; close the socket and return.
                try {
                    mmSocket!!.close()
                    Log.e("Status", "Cannot connect to device")
                    handler!!.obtainMessage(CONNECTING_STATUS, -1, -1).sendToTarget()
                } catch (closeException: IOException) {
                    Log.e(ContentValues.TAG, "Could not close the client socket", closeException)
                }
                return
            }

            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
            connectedThread = ConnectedThread(mmSocket)
            connectedThread!!.run()
        }

        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                mmSocket!!.close()
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "Could not close the client socket", e)
            }
        }
    }

    /* =============================== Thread for Data Transfer =========================================== */
    class ConnectedThread(private val mmSocket: BluetoothSocket?) : Thread() {
        private val mmInStream: InputStream?
        private val mmOutStream: OutputStream?

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = mmSocket!!.inputStream
                tmpOut = mmSocket.outputStream
            } catch (e: IOException) {
            }
            mmInStream = tmpIn
            mmOutStream = tmpOut
        }

        override fun run() {
            val buffer = ByteArray(1024) // buffer store for the stream
            var bytes = 0 // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    /*
                    Read from the InputStream from Arduino until termination character is reached.
                    Then send the whole String message to GUI Handler.
                     */
                    buffer[bytes] = mmInStream!!.read().toByte()
                    var readMessage: String
                    if (buffer[bytes] == '\n'.code.toByte()) {
                        readMessage = String(buffer, 0, bytes)
                        Log.e("Arduino Message", readMessage)
                        handler!!.obtainMessage(MESSAGE_READ, readMessage).sendToTarget()
                        bytes = 0
                    } else {
                        bytes++
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    break
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        fun write(input: String?) {
            val bytes = input!!.toByteArray() //converts entered String into bytes
            try {
                mmOutStream!!.write(bytes)
            } catch (e: IOException) {
                Log.e("Send Error", "Unable to send message", e)
            }
        }

        /* Call this from the main activity to shutdown the connection */
        fun cancel() {
            try {
                mmSocket!!.close()
            } catch (e: IOException) {
            }
        }
    }

    /* ============================ Terminate Connection at BackPress ====================== */

    companion object {
        var handler: Handler? = null
        var mmSocket: BluetoothSocket? = null
        var connectedThread: ConnectedThread? = null
        var createConnectThread: CreateConnectThread? = null
        private const val CONNECTING_STATUS =
            1 // used in bluetooth handler to identify message status
        private const val MESSAGE_READ = 2 // used in bluetooth handler to identify message update
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