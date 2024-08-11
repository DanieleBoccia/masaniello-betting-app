package com.example.masaniellobettingapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SimulazioniActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulazioni)

        val capitalInput = findViewById<EditText>(R.id.capitalInputSimulazione)
        val eventsInput = findViewById<EditText>(R.id.eventsInputSimulazione)
        val startSimulationButton = findViewById<Button>(R.id.btn_start_simulation)
        val resultText = findViewById<TextView>(R.id.resultTextSimulazione)

        startSimulationButton.setOnClickListener {
            val capital = capitalInput.text.toString().toDoubleOrNull()
            val events = eventsInput.text.toString().toIntOrNull()

            if (capital != null && events != null && events > 0) {
                val risultato = eseguiSimulazione(capital, events)
                resultText.text = "Risultato della Simulazione: $risultato"
                resultText.visibility = TextView.VISIBLE
            } else {
                resultText.text = "Inserisci valori validi"
                resultText.visibility = TextView.VISIBLE
            }
        }
    }

    private fun eseguiSimulazione(capital: Double, events: Int): String {
        // Implementa qui la logica della simulazione
        // Per esempio, calcolo semplice che restituisce un risultato basato sul capitale e gli eventi
        val risultato = capital * events // Logica di esempio, modifica con la tua logica
        return "Capitale moltiplicato per eventi: €%.2f".format(risultato)
    }
}
