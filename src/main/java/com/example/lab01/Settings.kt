package com.example.lab01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val spinner = findViewById<Spinner>(R.id.triesNumber) as Spinner
        val button: Button = findViewById<Button>(R.id.applyButton) as Button
        val list: MutableList<String> = ArrayList()
        list.add("1")
        list.add("3")
        list.add("5")

        val adp1 = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, list
        )
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adp1)

        button.setOnClickListener()
        {
            val tries: String = spinner.selectedItem.toString()
            Log.i(localClassName, "Tries: $tries")
            Intent(this, MainActivity::class.java).also {
                it.putExtra("tries", tries)
                startActivity(it)
            }
        }
    }
}