package com.karan.alert_box

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.karan.alert_box.databinding.ActivityMainBinding
import java.util.function.ToIntFunction

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val number=findViewById<EditText>(R.id.number)

        val result=findViewById<TextView>(R.id.result)
        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences=getSharedPreferences(resources.getString(R.string.app_name), MODE_PRIVATE)
        editor=sharedPreferences.edit()
        if (sharedPreferences.getBoolean("Dark",false))
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        binding.alertTitle.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("are you want to change the them")
                setPositiveButton("Night")
                { _, _ ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    editor.putBoolean("Dark",true)
                    editor.apply()

                }
                setNegativeButton("Day")
                {
                _,_->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    editor.putBoolean("Dark",false)
                    editor.apply()


                }
//                setNeutralButton("RESET")
//                {
//                    _,_->
//                    val result:TextView=findViewById(R.id.result)
//                    result.text="0"
//                }
                setCancelable(false)

            }
                .show()
        }
    }
}