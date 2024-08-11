package com.example.masaniellobettingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReportisticaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportistica)

        val recyclerViewReports = findViewById<RecyclerView>(R.id.recyclerViewReports)
        val generateReportButton = findViewById<Button>(R.id.btn_generate_report)

        // Configura il RecyclerView con un LayoutManager e un Adapter
        recyclerViewReports.layoutManager = LinearLayoutManager(this)
        recyclerViewReports.adapter = ReportAdapter(getDummyReports()) // Usa una lista di dummy data per esempio

        // Gestione del click sul bottone per generare/esportare rapporti
        generateReportButton.setOnClickListener {
            // Logica per generare un nuovo rapporto o esportare dati
        }
    }

    private fun getDummyReports(): List<Report> {
        // Restituisce una lista di report di esempio
        return listOf(
            Report("Report 1", "Descrizione del report 1"),
            Report("Report 2", "Descrizione del report 2"),
            // Aggiungi altri report di esempio qui
        )
    }
}

// Data class per rappresentare un Report
data class Report(val title: String, val description: String)

// Adapter per gestire la visualizzazione dei report nel RecyclerView
class ReportAdapter(private val reports: List<Report>) : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(reports[position])
    }

    override fun getItemCount(): Int = reports.size

    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.reportTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.reportDescription)

        fun bind(report: Report) {
            titleTextView.text = report.title
            descriptionTextView.text = report.description
        }
    }
}
