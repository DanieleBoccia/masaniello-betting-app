package com.example.masaniellobettingapp

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class ImpostazioniActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_impostazioni)

        val switchNotifiche = findViewById<Switch>(R.id.switch_notifiche)

        // Carica lo stato della preferenza dal sistema di storage, ad esempio SharedPreferences
        val preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val notificationsEnabled = preferences.getBoolean("NOTIFICHE", false)
        switchNotifiche.isChecked = notificationsEnabled

        // Ascolta i cambiamenti di stato dello switch e salva la preferenza
        switchNotifiche.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit().putBoolean("NOTIFICHE", isChecked).apply()
        }
    }
}
