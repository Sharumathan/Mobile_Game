package com.example.own_game
import android.content.SharedPreferences
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel

class GameEngine: ViewModel() {
    var index = 0
    var life = 3
    var score=0
    var highScore = 0
    var game_words = arrayOf("GAME","LOVE","GIRL","MALE","FEAL","MEAL","NAIL","GOAL","JUNE")
    val numbers = mutableListOf(0, 1, 2, 3)
    init {
        numbers.shuffle()
        game_words.shuffle()
    }
    val word = game_words[0]

    val first_letter = word[numbers[0]]
    val second_letter = word[numbers[1]]
    val third_letter = word[numbers[2]]
    val forth_letter = word[numbers[3]]

    fun game(button: Button,text:TextView,continueue: Button,text2:TextView){
        var tag = button.tag.toString()
        var letter = word[index].toString()
        if(letter==tag){
            button.setText(button.tag.toString())
            index++
            score++
            if(index == 4){
                text.setText("Done !!")
                continueue.visibility = View.VISIBLE
                index=0
                numbers.shuffle()
                game_words.shuffle()
            }
        }
        else{
            life--
            if(life>0){
                text.setText("$word")
                text2.setText("$life")
            }
            else{
                text.setText("Die")
            }

        }
    }
}