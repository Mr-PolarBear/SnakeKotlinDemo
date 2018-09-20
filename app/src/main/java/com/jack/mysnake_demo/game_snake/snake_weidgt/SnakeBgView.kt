package com.jack.mysnake_demo.game_snake.snake_weidgt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View

import com.jack.mysnake_demo.game_snake.snake_object.SnakeConfig

/**
 * Class for: 游戏的底层布局 暂时没用kotlin写
 * Created by   jack.马
 * Date: 2018/9/11
 * Time: 14:17
 */
class SnakeBgView constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {


    private var mGridWidth = 0f//每个方格的宽
    private var mGridHeight = 0f//每个方格的长

    private var mPaintLine: Paint? = null//画线

    var mCallback: onOverCallback? = null//初始化接口

    init {
        initPaint()
    }

    private fun initPaint() {
        //设置背景色画笔 蓝色
        mPaintLine = Paint()
        mPaintLine?.style = Paint.Style.FILL//铺满
        mPaintLine?.color = Color.parseColor("#FF82B8E9")
        mPaintLine?.isAntiAlias = true
        mPaintLine?.isDither = true
    }


    override fun onDraw(canvas: Canvas) {
        drawBg(canvas)
    }

    //画背景方格
    private fun drawBg(canvas: Canvas) {

        mGridWidth = (width / SnakeConfig.GAME_COLUMN_COUNT).toFloat()
        mGridHeight = mGridWidth

        SnakeConfig.GRID_WIDTH = mGridWidth
        SnakeConfig.GRID_HEIGHT = mGridHeight

        drawLine(canvas, SnakeConfig.GAME_COLUMN_COUNT, SnakeConfig.GAME_ROW_COUNT)
    }


    /**
     * 将横竖画线分开 便于以后出现行列数量不一致的时候好更改
     *
     * @param canvas
     * @param columnCount 列数
     * @param rowCount    行数
     */
    private fun drawLine(canvas: Canvas, columnCount: Int, rowCount: Int) {

        //根据行数画对应数量的横线 底部需要画一条 所以加1
        for (i in 0 until rowCount + 1) {
            //画横线
            canvas.drawLine(0f, i * mGridHeight, width.toFloat(), i * mGridHeight, mPaintLine!!)
            //把一列中每一格的y坐标存起来
            SnakeConfig.setSingleColumnY(i, i * mGridHeight)
        }

        //根据列数画对应数量的竖线
        for (i in 0 until columnCount) {
            //画竖线
            canvas.drawLine(i * mGridWidth, 0f, i * mGridWidth, mGridWidth * columnCount, mPaintLine!!)
            //把一行中每一格的x坐标存起来
            SnakeConfig.setSingleRowX(i, i * mGridWidth)
        }

        mCallback?.onOver(true)
    }


    //初始化完成之后的回调
    interface onOverCallback {
        fun onOver(isOk: Boolean)
    }

}
