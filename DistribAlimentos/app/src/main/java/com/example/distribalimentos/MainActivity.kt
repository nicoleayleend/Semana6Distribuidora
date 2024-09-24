package com.example.distribalimentos

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import android.Manifest
import android.util.Log

private lateinit var fusedLocationClient: FusedLocationProviderClient
private lateinit var database: DatabaseReference

class MainActivity : AppCompatActivity() {

    companion object {
        var latitude: Double = 0.0;
        var longitude: Double = 0.0;
    }

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnLogin: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        database = FirebaseDatabase.getInstance().reference

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestLocationPermission()

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btnLogin)

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        if (TextUtils.isEmpty(emailInput) || TextUtils.isEmpty(passwordInput)) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    saveLocationToFirebase(latitude, longitude, mAuth.currentUser?.uid.toString() )
                    val user = mAuth.currentUser
                    updateUI(user)

                } else {
                    Toast.makeText(this, "Error en autenticación.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // Ir a MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        } else {

            getCurrentLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permiso concedido
                getCurrentLocation()
            } else {
                // Permiso denegado
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    latitude = it.latitude
                    longitude = it.longitude
                    Log.i("MainActivity", "Ubicacion obtenida con exito: $latitude : $longitude")

                }
            }
            .addOnFailureListener {
                Log.i("MainActivity", "Error al obtener ubicacion")
            }
    }

    private fun saveLocationToFirebase(latitude: Double, longitude: Double, userId: String) {
        val locationData = mapOf(
            "latitude" to latitude,
            "longitude" to longitude,
            "timestamp" to System.currentTimeMillis()
        )

        Log.d("MainActivity", "Entre a saveLocationToFirebase")

        database.child("users").child(userId).child("location").setValue(locationData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("MainActivity", "Ubicacion guardada de forma exitosa")
                } else {
                    Log.d("MainActivity", "No se pudo guardar la ubicacion")
                }
            }
    }


}
