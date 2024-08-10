package com.example.masaniellobettingapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val capitalInput = findViewById<EditText>(R.id.capitalInput)
        val eventsInput = findViewById<EditText>(R.id.eventsInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        calculateButton.setOnClickListener {
            val capital = capitalInput.text.toString().toDoubleOrNull()
            val events = eventsInput.text.toString().toIntOrNull()

            if (capital != null && events != null && events > 0) {
                val puntata = calcolaPuntata(capital, events)
                resultText.text = "Puntata: $puntata"
                resultText.visibility = TextView.VISIBLE
            } else {
                resultText.text = "Inserisci valori validi"
                resultText.visibility = TextView.VISIBLE
            }
        }
    }

    private fun calcolaPuntata(capital: Double, events: Int): Double {
        // Logica semplice per calcolare la puntata
        return capital / events
    }
}