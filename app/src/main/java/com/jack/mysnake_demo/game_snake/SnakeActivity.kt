package com.jack.mysnake_demo.game_snake

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jack.mysnake_demo.R
import com.jack.mysnake_demo.game_snake.snake_object.EnumMoveType
import com.jack.mysnake_demo.game_snake.snake_weidgt.SnakeBgView
import kotlinx.android.synthetic.main.activity_snake.*

//贪吃蛇游戏页面
class SnakeActivity : AppCompatActivity() {

    @Volatile
    private var mMoveType = EnumMoveType.RIGHT

    @Volatile
    private var mIsStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake)


        //背景初始化完成之后初始化前景对象
        sbv_snake.mCallback = object : SnakeBgView.onOverCallback {
            override fun onOver(isOk: Boolean) {
                //初始化蛇皮
                sgv_snake.initGameObj()
            }
        }

        initClick()

    }


    //初始化点击事件
    private fun initClick() {
        //开始按钮
        tv_snake_start.setOnClickListener {
            mIsStart = !mIsStart
            tv_snake_start.setText(if (mIsStart) "暂停" else "开始")
            start(mMoveType)
        }
        //左
        bt_snake_left.setOnClickListener { start(EnumMoveType.LEFT) }
        //右
        bt_snake_righrt.setOnClickListener { start(EnumMoveType.RIGHT) }
        //上
        bt_snake_top.setOnClickListener { start(EnumMoveType.UP) }
        //下
        bt_snake_down.setOnClickListener { start(EnumMoveType.DOWN) }

    }

    fun start(enumMoveType: EnumMoveType) {
        if (mIsStart) {
            mMoveType = enumMoveType
            sgv_snake.StartMove(mMoveType)
        } else {

            sgv_snake.StartMove(EnumMoveType.PAUSE)
        }
    }
}
