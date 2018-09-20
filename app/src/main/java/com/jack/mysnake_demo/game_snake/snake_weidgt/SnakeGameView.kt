package com.jack.mysnake_demo.game_snake.snake_weidgt

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.jack.mysnake_demo.game_snake.snake_object.EnumMoveType
import com.jack.mysnake_demo.game_snake.snake_object.Obj_Food
import com.jack.mysnake_demo.game_snake.snake_object.Obj_Snake

/**
 * Class for: 游戏的顶层布局
 * Created by   jack.马
 * Date: 2018/8/23
 * Time: 17:57
 */
class SnakeGameView : View {

    //定义食物对象
    var mFood: Obj_Food? = null

    //定义蛇对象
    var mSnake: Obj_Snake? = null

    //定义初始移动方向
    var mMoveType: EnumMoveType = EnumMoveType.PAUSE

    var mIsGameOver = false

    //球画笔
    var mFoodPaint: Paint = Paint().apply {
        setStyle(Paint.Style.FILL)//铺满
        setColor(Color.RED)
        setAntiAlias(true)
        setDither(true)
    }

    //蛇头画笔
    var mSnakeHeadPaint: Paint = Paint().apply {
        setStyle(Paint.Style.FILL)
        setColor(Color.DKGRAY)
        setAntiAlias(true)
        setDither(true)
    }
    var mSnakeBodyPaint: Paint = Paint().apply {
        //蛇身画笔
        setStyle(Paint.Style.FILL)
        setColor(Color.GRAY)
        setAntiAlias(true)
        setDither(true)
    }


    private var mTime = 200//多久移动一次

    private var mRun = Handler()
    private var mRunnable: Runnable? = null

    private var dialog: AlertDialog? = null


    constructor(context: Context) : this(context, null) {
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initCycle()
    }


    override fun onDraw(canvas: Canvas) {
        mSnake?.drawSnake(canvas, mMoveType, mSnakeHeadPaint, mSnakeBodyPaint, mFood!!.mPosition)
//
        mFood?.resetFood(canvas, mFoodPaint, mSnake!!.getPositionList())


    }


    /**
     * 手动初始化蛇和球
     */
    fun initGameObj() {
        if (mFood != null && mSnake != null) return

        mFood = Obj_Food()
        mSnake = Obj_Snake()

        //碰撞回调
        mSnake?.mCrash = object : Obj_Snake.OnCrashListener {
            override fun onCrash(message: String) {
                mMoveType = EnumMoveType.PAUSE
                mIsGameOver = true
                dialog?.setMessage("游戏结束：" + message)
                dialog?.show()

            }
        }

        //吃到食物回调
        mSnake?.mEatFood = object : Obj_Snake.OnEatenFoodListener {
            override fun onEaten() {
                if (mFood != null)
                    mFood?.onEatFood()
            }
        }


    }

    //主要方法 设置溜蛇的方向
    @Synchronized
    fun StartMove(enum: EnumMoveType) {
        mMoveType = enum
    }


    //初始化轮训
    private fun initCycle() {

        dialog = AlertDialog.Builder(context).setMessage("游戏结束")
                .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()

                        //直接关闭页面 简单粗暴
                        (context as Activity).finish()
                        //失败则重新初始化
//                        mFood = Obj_Food()
//                        mSnake = Obj_Snake()
//                        invalidate()
                    }
                }).create()

        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)

        //循环的时候做什么
        mRunnable = Runnable {
            if (context == null || mIsGameOver) return@Runnable
            mRun.postDelayed(mRunnable, mTime.toLong())
            StartMove(mMoveType)

            if (mMoveType != EnumMoveType.PAUSE)
                invalidate()
        }
        //开始循环
        mRun.post(mRunnable)


    }


    override fun onDetachedFromWindow() {
        mRun?.removeCallbacks(mRunnable)
        super.onDetachedFromWindow()
    }

    /**
     * dp 转 px
     */
    private fun dp2px(dip: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip.toFloat(), resources.displayMetrics).toInt()
    }


}
