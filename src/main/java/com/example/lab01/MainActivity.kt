package com.example.lab01

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    var tries: Int = 1
    var thistry: Int = 0
    var clickable: Boolean = true
    var sum1: Int = 0
    var sum2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.rollButton)
        val button2 = findViewById<FloatingActionButton>(R.id.rollButtonFloat)
        val dice1Img: ImageView = findViewById<ImageView>(R.id.dice1Img)
        val dice2Img: ImageView = findViewById<ImageView>(R.id.dice2Img)
        button.setOnClickListener()
        {
            if(clickable)
            {
                Log.i(localClassName, "Try $thistry, total $tries")
                if(thistry < tries) thistry++
                else
                {
                    thistry = 1
                    sum1 = 0
                    sum2 = 0
                }
                rollDice()
            }
        }

        button2.setOnClickListener()
        {
            if(clickable)
            {
                Log.i(localClassName, "Try $thistry, total $tries")
                if(thistry < tries) thistry++
                else
                {
                    thistry = 1
                    sum1 = 0
                    sum2 = 0
                }
                rollDice()
            }
        }

        dice1Img.setOnClickListener()
        {
            clickable = !clickable
            Log.i(localClassName, "Clickable $clickable")
        }
        dice2Img.setOnClickListener()
        {
            clickable = !clickable
            Log.i(localClassName, "Clickable $clickable")
        }
    }

    private fun rollDice()
    {
        val dice = Dice()
        val roll = dice.roll()
        val roll2 = dice.roll(false)
        sum1 += roll
        sum2 += roll2
        val crash = findViewById<ConstraintLayout>(R.id.crash_test)
        val screenH = crash?.height
        updateText(roll, roll2)
        updateImg(roll, roll2)
    }

    private fun updateImg(roll: Int, roll2: Int)
    {
        val dice1Img: ImageView = findViewById<ImageView>(R.id.dice1Img)
        val dice2Img: ImageView = findViewById<ImageView>(R.id.dice2Img)
        dice1Img.setImageResource(resolveDrawable(roll))
        dice2Img.setImageResource(resolveDrawable(roll2))
    }

    private fun resolveDrawable(value: Int): Int
    {
        return when(value)
        {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_1
        }
    }

    private fun updateText(roll: Int, roll2: Int)
    {
        val rollResultText = findViewById<TextView>(R.id.rollResultText)
        rollResultText.text = "Try: $thistry Rolled: ${roll} & ${roll2} Sum: $sum1 & $sum2"
        var r: Int = (0..255).random()
        var g: Int = (((roll + roll2) / 12.0) *255).toInt()
        var b: Int = (((roll + roll2) / 36.0) *255).toInt()
        rollResultText.setTextColor(Color.rgb(r,g,b))
        Log.i(localClassName, "Text color: R:$r, G:$g B:$b)")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menuSettings -> startSettingsActivity()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startSettingsActivity()
    {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    override fun onStart()
    {
        super.onStart()
            Log.i(localClassName, "onSTart")
    }

    override fun onResume()
    {
        super.onStart()
        Log.i(localClassName, "onResume")
        val temp: String? = getIntent().getStringExtra("tries")
        Log.i(localClassName, "Intent temp: $temp")
        if (temp != null) {
            tries = temp.toInt()
        }
    }

    override fun onPause()
    {
        super.onStart()
        Log.i(localClassName, "onPause")
    }

    override fun onStop()
    {
        super.onStart()
        Log.i(localClassName, "onStop")
    }

    override fun onDestroy()
    {
        super.onStart()
        Log.i(localClassName, "onDestroy")
    }
}