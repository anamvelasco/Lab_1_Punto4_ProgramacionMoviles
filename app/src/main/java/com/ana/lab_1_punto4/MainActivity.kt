package com.ana.lab_1_punto4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.ana.lab_1_punto4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    private val colorNumeroMap = mapOf(
        "Negro" to 0,
        "Marrón" to 1,
        "Rojo" to 2,
        "Naranja" to 3,
        "Amarillo" to 4,
        "Verde" to 5,
        "Azul" to 6,
        "Purpura" to 7,
        "Gris" to 8,
        "Blanco" to 9
    )

    private val multiplicadorMap = mapOf(
        "Negro" to 1.0,
        "Marrón" to 10.0,
        "Rojo" to 100.0,
        "Naranja" to 1000.0,
        "Amarillo" to 10000.0,
        "Verde" to 100000.0,
        "Azul" to 1000000.0,
        "Dorado" to 0.1,
        "Plateado" to 0.01
    )

    private val toleranciaMap = mapOf(
        "Marrón" to "±1%",
        "Rojo" to "±2%",
        "Dorado" to "±5%",
        "Plateado" to "±10%"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        val coloresArrayU = resources.getStringArray(R.array.colores_resistencia_uno)
        val coloresArray = resources.getStringArray(R.array.colores_resistencia)
        val coloresArrayTres = resources.getStringArray(R.array.colores_resistencia_tres)
        val coloresArrayTolerancia = resources.getStringArray(R.array.colores_resistencia_t)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, coloresArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterTres = ArrayAdapter(this, android.R.layout.simple_spinner_item, coloresArrayTres)
        adapterTres.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterU = ArrayAdapter(this, android.R.layout.simple_spinner_item, coloresArrayU)
        adapterU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adapterTolerancia = ArrayAdapter(this, android.R.layout.simple_spinner_item, coloresArrayTolerancia)
        adapterTolerancia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mainBinding.spinner.adapter = adapterU
        mainBinding.spinner2.adapter = adapter
        mainBinding.spinner3.adapter = adapterTres
        mainBinding.spinner4.adapter = adapterTolerancia

        mainBinding.calcularButton.setOnClickListener {
            val coloresSeleccionados = listOf(
                mainBinding.spinner.selectedItem.toString(),
                mainBinding.spinner2.selectedItem.toString(),
                mainBinding.spinner3.selectedItem.toString()
            )

            val numerosAsignados = coloresSeleccionados.joinToString("") {
                val color = colorNumeroMap[it] ?: 0
                color.toString()
            }.toDouble()

            val multiplicadorSeleccionado = mainBinding.spinner3.selectedItem.toString()
            val multiplicador = multiplicadorMap[multiplicadorSeleccionado] ?: 1.0

            val resultado = numerosAsignados * multiplicador

            val toleranciaSeleccionada = mainBinding.spinner4.selectedItem.toString()
            val tolerancia = toleranciaMap[toleranciaSeleccionada] ?: ""

            val resultadoFinal = "La resistencia es de: $resultado Ω\nCon una tolerancia de: $tolerancia"

            mainBinding.resultadoTextView.text = resultadoFinal
        }
    }
}
