package com.example.own_game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var data = ViewModelProvider(this).get(GameEngine::class.java)
        val receivedValue = intent.getIntExtra("score", 0)
        data.score=receivedValue


        var button1: Button = findViewById(R.id.button)
        var button2: Button = findViewById(R.id.button3)
        var button3: Button = findViewById(R.id.button4)
        var button4: Button = findViewById(R.id.button2)
        var button5: Button = findViewById(R.id.button5)

        var myScore:TextView = findViewById(R.id.textView3)
        var text: TextView = findViewById(R.id.textView)
        var text2:TextView = findViewById(R.id.textView9)
        var high_score: TextView = findViewById(R.id.textView2)

        var ms = "Score : ${data.score}"
        myScore.setText(ms.toString())

        val sharedPref = getSharedPreferences("sharuDb", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        if(sharedPref.getInt("highScore",0) == 0){
            editor.apply {
                putInt("highScore",0)
                apply()
            }
        }

        val score = sharedPref.getInt("highScore",0).toString()
        high_score.setText("High Score : $score")

        text.setText("${data.word}")
        text2.setText("${data.life}")

        button5.visibility = View.GONE
        button1.setTag(data.first_letter)
        button2.setTag(data.second_letter)
        button3.setTag(data.third_letter)
        button4.setTag(data.forth_letter)



        button1.setOnClickListener {
            data.game(button1,text,button5,text2)
            var ms = "Score : ${data.score}"
            myScore.setText(ms.toString())
            if(data.life<=0){
                val intent = Intent(this,Dummy::class.java)
                startActivity(intent)
            }
        }
        button2.setOnClickListener {
            data.game(button2,text,button5,text2)
            var ms = "Score : ${data.score}"
            myScore.setText(ms.toString())
            if(data.life<=0){
                val intent = Intent(this,Dummy::class.java)
                startActivity(intent)
            }

        }
        button3.setOnClickListener {
            data.game(button3,text,button5,text2)
            var ms = "Score : ${data.score}"
            myScore.setText(ms.toString())
            if(data.life<=0){
                val intent = Intent(this,Dummy::class.java)
                startActivity(intent)
            }

        }
        button4.setOnClickListener {
            data.game(button4,text,button5,text2)
            var ms = "Score : ${data.score}"
            myScore.setText(ms.toString())
            if(data.life<=0){
                val intent = Intent(this,Dummy::class.java)
                startActivity(intent)
            }

        }

        button5.setOnClickListener {
            val highScore = sharedPref.getInt("highScore",0)
            if(highScore<data.score){
                editor.apply {
                    putInt("highScore",data.score)
                    apply()
                }
                // data = ViewModelProvider(this).get(GameEngine(data.score)::class.java)
            }
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("score",data.score)
            startActivity(intent)
        }

    }
    }