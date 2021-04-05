package com.example.visualmath.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.example.visualmath.R
import com.example.visualmath.data.AppDB
import com.example.visualmath.data.learn.Quiz
import com.example.visualmath.data.learn.Theory
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

//    private lateinit var navController: NavController
//    private lateinit var navHostFragment: NavHostFragment

    private val scope = CoroutineScope(Dispatchers.Main)

    private lateinit var db: AppDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//        navController.navigate(R.id.mainMenu)

        db = Room.databaseBuilder(
            applicationContext,
            AppDB::class.java, "database"
        ).build()


        val tv = findViewById<TextView>(R.id.textView)
        val btnCreate = findViewById<MaterialButton>(R.id.btn_create)
        val btnGet = findViewById<MaterialButton>(R.id.btn_get)


        scope.launch {
            withContext(Dispatchers.Default) {
                db.quizDao().insertAll(Quiz(Random.nextInt(), "fjsdjf"))
                db.theoryDao().insertAll(Theory(Random.nextInt(), ""))
            }

            val quiz = withContext(Dispatchers.Default) {
                db.quizDao().getAll()
            }.size

            val theory = withContext(Dispatchers.Default) {
                db.theoryDao().getAll()
            }.size

            tv.text = (quiz + theory).toString()
        }
    }
}