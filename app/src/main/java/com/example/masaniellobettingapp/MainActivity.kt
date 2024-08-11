package com.example.masaniellobettingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var saldoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)  // Inizializza Firebase

        // Inizializzazione Firebase Auth e Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            setContentView(R.layout.activity_main)

            // Inizializzazione della TextView per il saldo
            saldoTextView = findViewById(R.id.saldoTextView)

            // Caricamento del saldo dell'utente
            loadSaldo()

            // Setup dei pulsanti e della navigazione
            val calculateBetButton = findViewById<Button>(R.id.btn_calculate_bet)
            val simulationsButton = findViewById<Button>(R.id.btn_simulations)
            val reportsButton = findViewById<Button>(R.id.btn_reports)
            val settingsButton = findViewById<Button>(R.id.btn_settings)

            calculateBetButton.setOnClickListener {
                val intent = Intent(this, CalcolaPuntataActivity::class.java)
                startActivity(intent)
            }

            simulationsButton.setOnClickListener {
                val intent = Intent(this, SimulazioniActivity::class.java)
                startActivity(intent)
            }

            reportsButton.setOnClickListener {
                val intent = Intent(this, ReportisticaActivity::class.java)
                startActivity(intent)
            }

            settingsButton.setOnClickListener {
                val intent = Intent(this, ImpostazioniActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun loadSaldo() {
        val userId = auth.currentUser?.uid

        if (userId != null) {
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val saldo = document.getDouble("saldo") ?: 0.0
                        saldoTextView.text = "Saldo: €%.2f".format(saldo)
                    }
                }
                .addOnFailureListener { e ->
                    saldoTextView.text = "Errore nel caricamento del saldo"
                }
        }
    }

    // Metodo per aggiornare il saldo
    private fun updateSaldo(newAmount: Double) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("users").document(userId)
                .update("saldo", newAmount)
                .addOnSuccessListener {
                    saldoTextView.text = "Saldo: €%.2f".format(newAmount)
                }
                .addOnFailureListener {
                    // Gestire l'errore
                }
        }
    }
}
