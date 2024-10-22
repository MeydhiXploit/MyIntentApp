package com.example.myintentapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnMoveIntent: Button = findViewById(R.id.btnMoveIntent)
        val btnMoveWithData: Button = findViewById(R.id.btnMoveIntentData)
        val btnMoveWithObject: Button = findViewById(R.id.btn_move_activity_object)
        val btnDialPhone:Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)
        btnMoveIntent.setOnClickListener(this)
        btnMoveWithData.setOnClickListener(this)
        btnMoveWithObject.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnMoveIntent -> {
                val moveIntent = Intent(this@MainActivity,MoveAcitivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btnMoveIntentData -> {
                val moveIntentData = Intent(this@MainActivity,MoveWithDataActivity::class.java)
                moveIntentData.putExtra(MoveWithDataActivity.EXTRA_NAME,"Meydhi Ari Nugroho")
                moveIntentData.putExtra(MoveWithDataActivity.EXTRA_AGE,5)
              
                startActivity(moveIntentData)
            }
            R.id.btn_move_activity_object -> {
// membuat sebuah objek Person bernama person yang mana class tersebut adalah parcelable
                val person = Person(
                    "Dicoding Academy",
                    5,
                    "meydhimeydhi@gmail.com",
                    "Bandung"
                )
                //metode putExtra()
                val moveWithObjectIntent = Intent(this@MainActivity,MoveWithObjectActivity::class.java)
                //yang kita pilih saat ini adalah putExtra(String name,Parcelable value)
                //lalu EXTRA_PERSON merupakan variabel static bertipe data string dan bernilai
                //extra_person berfungsi sebagai key untuk mendapatkan value data yang di kirim
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON,person)
                startActivity(moveWithObjectIntent)
            }
            R.id.btn_dial_number -> {
                val phoneNumber = "0881036273186"
                //variabel action_dial menentukan intent filter dari aplikasi aplikasi yang bisa
                //menangani action tersebut dan uri sebuah untaian karakter yang di gunakan untuk mengidentifikasi nama
                //sumber atau layanan di internet pada Uri.parse("tel:"+phoneNumber), kita melakukan parsing uri dari bentuk
                //teks string menjadi sebuah objek uri dengan menggunakan metode static parse(String).
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
        }
    }
}