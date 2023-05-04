package com.example.kamatechs_app

import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import android.Manifest


class SelectDeviceActivity : AppCompatActivity() {
    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_device)

        val actionbar = supportActionBar
        actionbar!!.title = "Select Device"
        actionbar.setDisplayHomeAsUpEnabled(true)

        // Bluetooth Setup
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        // Check if Bluetooth is enabled
        if (bluetoothAdapter.isEnabled) {
            // Check for BLUETOOTH_SCAN permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission if not granted
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.BLUETOOTH_SCAN,
                        Manifest.permission.BLUETOOTH_CONNECT // Add BLUETOOTH_CONNECT permission
                    ),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                // Permission already granted, perform Bluetooth scanning
                performBluetoothScanning()
            }
        } else {
            // Bluetooth is not enabled, show a message to the user
            Toast.makeText(this, "Activate Bluetooth or pair a Bluetooth device", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, perform Bluetooth scanning
                performBluetoothScanning()
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(
                    this,
                    "BLUETOOTH_SCAN permission denied. Unable to perform Bluetooth scanning.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun performBluetoothScanning() {
        // Get List of Paired Bluetooth Devices
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val pairedDevices = bluetoothAdapter.bondedDevices
        val deviceList: MutableList<Any> = ArrayList()
        if (pairedDevices.size > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (device in pairedDevices) {
                val deviceName = device.name
                val deviceHardwareAddress = device.address // MAC address
                val deviceInfoModel = DeviceInfoModel(deviceName, deviceHardwareAddress)
                deviceList.add(deviceInfoModel)
            }
            // Display paired devices using recyclerView
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewDevice)
            recyclerView.layoutManager = LinearLayoutManager(this)
            val deviceListAdapter = DeviceListAdapter(this, deviceList)
            recyclerView.adapter = deviceListAdapter
            recyclerView.itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}