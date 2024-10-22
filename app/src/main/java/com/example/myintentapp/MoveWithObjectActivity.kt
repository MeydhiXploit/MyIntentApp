package com.example.myintentapp

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoveWithObjectActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_move_with_object)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvObject:TextView = findViewById(R.id.tv_object_received)
        val person = if (Build.VERSION.SDK_INT >= 33) {
            //karena objek ini terdiri dari beragam tipe data pada atribut atau propertinya
            //kita cukup membungkus itu semua ke dalam objek Parcelable.Melalui
            //getIntent().getParcelableExtra(Key) jadi kita mendapat nilai objek personnya
            //yang sebelumnya yelah di kirim hanya dengan satu variabel

            //jadi bayangkan jika kita tidak menggunakan Parcelable pasti kita
            //mengirim data satu persatu tapi kalau datanya sedikit itu gak masalah
            //tetapi jika datanya puluhan tentu akan merepotkan bukan?
            intent.getParcelableExtra<Person>(EXTRA_PERSON, Person::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Person>(EXTRA_PERSON)
        }
        //lalu bagaimana jika kita ingin mengirimkan kumpulan objek Parcelable
        //ke activity lain? Untuk mengirimkan kumpulan data,kita bisa memanfaatkan ArrayList dan
        //metode putParcelableArrayListExtra
        if (person != null) {
            val text = "Name : ${person.name.toString()},\nEmail : ${person.email},\nAge : ${person.age},\nLocation : ${person.city}"
            tvObject.text = text
        }
    }
}