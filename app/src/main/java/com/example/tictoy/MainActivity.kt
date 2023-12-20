package com.example.tictoy

import android.annotation.SuppressLint
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random as Random

class MainActivity : AppCompatActivity() {
    lateinit var cell1: TextView
    lateinit var cell2: TextView
    lateinit var cell3: TextView
    lateinit var cell4: TextView
    lateinit var cell5: TextView
    lateinit var cell6: TextView
    lateinit var cell7: TextView
    lateinit var cell8: TextView
    lateinit var cell9: TextView
    var cellnumber: Int = 0
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activeplayer = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cell1 = findViewById(R.id.cell1)
        cell2 = findViewById(R.id.cell2)
        cell3 = findViewById(R.id.cell3)
        cell4 = findViewById(R.id.cell4)
        cell5 = findViewById(R.id.cell5)
        cell6 = findViewById(R.id.cell6)
        cell7 = findViewById(R.id.cell7)
        cell8 = findViewById(R.id.cell8)
        cell9 = findViewById(R.id.cell9)
    }

    fun press(view: View) {

        when (view) {
            cell1 -> cellnumber = 1
            cell2 -> cellnumber = 2
            cell3 -> cellnumber = 3
            cell4 -> cellnumber = 4
            cell5 -> cellnumber = 5
            cell6 -> cellnumber = 6
            cell7 -> cellnumber = 7
            cell8 -> cellnumber = 8
            cell9 -> cellnumber = 9


        }
        play(cellnumber, view as TextView)
    }

    fun play(cellnumber: Int, view: TextView) {
        if (activeplayer == 1) {
            view.text = "X"
            view.setBackgroundResource(R.color.blue)
            player1.add(cellnumber)
            activeplayer = 2
            autoplay()
        } else {
            view.text = "O"
            view.setBackgroundResource(R.color.red)
            player2.add(cellnumber)
            activeplayer = 1
        }
        view.isEnabled = false
        win()

    }

    fun autoplay() {
        val emptyc = ArrayList<Int>()
        for (cellnumber in 1..9) {
            if (!(player1.contains(cellnumber) || player2.contains(cellnumber)))
                emptyc.add(cellnumber)
        }
        if (!emptyc.isEmpty()) {
            var r = Random
            var randomnu = r.nextInt(emptyc.size - 0) + 0
            var selecteditem: TextView?
            when (emptyc[randomnu]) {
                1 -> selecteditem = cell1
                2 -> selecteditem = cell2
                3 -> selecteditem = cell3
                4 -> selecteditem = cell4
                5 -> selecteditem = cell5
                6 -> selecteditem = cell6
                7 -> selecteditem = cell7
                8 -> selecteditem = cell8
                9 -> selecteditem = cell9
                else -> selecteditem = cell1

            }
            play(emptyc[randomnu], selecteditem)


        }

    }

    fun win() {
        if ((player1.contains(1) && player1.contains(2) && player1.contains(3)) ||
            (player1.contains(4) && player1.contains(5) && player1.contains(6)) ||
            player1.contains(7) && player1.contains(8) && player1.contains(9) ||
            player1.contains(1) && player1.contains(4) && player1.contains(7) ||
            player1.contains(2) && player1.contains(5) && player1.contains(8) ||
            player1.contains(3) && player1.contains(6) && player1.contains(9))
        {
            var intent = Intent(this, WINNER::class.java)
            startActivity(intent)
        }
        if ((player2.contains(1) && player2.contains(2) && player2.contains(3)) ||
            (player2.contains(4) && player2.contains(5) && player2.contains(6)) ||
            player2.contains(7) && player2.contains(8) && player2.contains(9) ||
            player2.contains(1) && player2.contains(4) && player2.contains(7) ||
            player2.contains(2) && player2.contains(5) && player2.contains(8) ||
            player2.contains(3) && player2.contains(6) && player2.contains(9)
        ) {
            var intent = Intent(this, LOSER::class.java)
            startActivity(intent)
        }


    }

    fun reset(view: View) {
        finish()
        startActivity(getIntent())
    }


}