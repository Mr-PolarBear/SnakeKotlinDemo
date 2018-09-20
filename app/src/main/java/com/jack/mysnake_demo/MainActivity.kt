package com.jack.mysnake_demo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import com.jack.mysnake_demo.game_snake.SnakeActivity
import com.jack.mysnake_demo.game_snake.snake_object.SnakeConfig
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tv_start.setOnClickListener {
            startActivity(Intent(this, SnakeActivity::class.java))
        }
    }


}
