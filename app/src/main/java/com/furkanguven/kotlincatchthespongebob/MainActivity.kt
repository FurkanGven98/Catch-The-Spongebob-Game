package com.furkanguven.kotlincatchthespongebob

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score =0
    var imageArray = ArrayList<ImageView>()
    var runnable : Runnable = Runnable {  }
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageArray.add(Sponge)
        imageArray.add(Sponge2)
        imageArray.add(Sponge3)
        imageArray.add(Sponge4)
        imageArray.add(Sponge5)
        imageArray.add(Sponge6)
        imageArray.add(Sponge7)
        imageArray.add(Sponge8)
        imageArray.add(Sponge9)

        hideImages()


        object : CountDownTimer(15500,1000){
            override fun onFinish() {
                textView.text="Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart Game?")
                alert.setPositiveButton("Yes"){dialogInterface:DialogInterface, i: Int->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialogInterface:DialogInterface,i:Int->
                    Toast.makeText(this@MainActivity,"GameOver",Toast.LENGTH_LONG).show()
                }
                alert.show()


            }
            override fun onTick(millisUntilFinished: Long) {
              textView.text="Time: "+millisUntilFinished/1000
            }



        }.start()
    }
    fun hideImages(){
        runnable =object :Runnable{
            override fun run(){
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random = Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)

    }
    fun increaseScore(view: View){
        score=score+1
        textView2.text="Score: $score"
    }
}