package com.jack.mysnake_demo.game_snake.snake_object

import android.graphics.Canvas
import android.graphics.Paint
import java.util.*
import android.graphics.RectF


/**
 * 因为蛇的组成是块状的，此对象就是组成蛇的块集合
 * */
class Obj_Snake : GameModel {

    var mFirstLength = 15//初始的长度

    @Volatile
    var mHeadPosition = mFirstLength - 1//头部的位置

    @Volatile
    var mBackPosition = 0//尾部的位置

    @Volatile
    var mNextPosition = 0//下一个移动的位置  每移动一次更新一次

    @Volatile
    var mCurrentOrientation = EnumMoveType.PAUSE//初始方向为暂停 因为从第一行开始 第一次跑 只能向右或者向下


    //蛇身体的位置集合
    var mSnakePositons = LinkedList<DataPosition>()


    var mCrash: OnCrashListener? = null

    var mEatFood: OnEatenFoodListener? = null

    /**
     * 蛇撞墙 和吃到自己
     */
    interface OnCrashListener {
        fun onCrash(message: String)
    }

    /**
     * 贪吃蛇吃到食物的回调
     * */
    interface OnEatenFoodListener {
        fun onEaten()
    }


    constructor() {
        if (mSnakePositons == null) mSnakePositons = LinkedList()

        //todo 第一步 构建一条皮皮蛇
        for (i in mFirstLength - 1 downTo 0) {
            var data = DataPosition(i, false, false)

            if (i == mFirstLength - 1)//如果下标是最大的 则是蛇头
                data.addHeadPosition(i)

            if (i == 0)
                data.addBackPosition(i)//如果下标是0 则是蛇尾

            mSnakePositons.add(data)

        }

    }

    //获取蛇的肉体
    fun getPositionList(): LinkedList<DataPosition> {
        if (mSnakePositons == null)
            mSnakePositons = LinkedList()
        return mSnakePositons
    }


    //画蛇第一步
    @Synchronized
    fun drawSnake(canvas: Canvas, type: EnumMoveType, paintHead: Paint, paintBody: Paint, mBallPosition: Int) {

        //1.移动方向暂停则不走
        if (type == EnumMoveType.PAUSE) {
            mCurrentOrientation = type
            drawList(canvas, paintHead, paintBody)
            return
        }

        //2.检查一 ,方向不包含自己身体  可以移动才将最新的方向赋给全局变量 对nextposition进行了操作
        if (checkPosition(type))
            mCurrentOrientation = type


        //3.检查二 ,检查是否撞墙 是的话over
        isCrashWall()

        //4.检查三 ,检查是否吃到了自己 吃到的话直接over
        checkEatMySelf()

        //5.检查四,检查下一个位置是不是球
        if (mNextPosition == mBallPosition) {
            mEatFood?.onEaten()
        } else {
            getPositionList().removeLast()//移除旧的蛇尾
            getPositionList().last.isBack = true //设置新的蛇尾
        }



        getPositionList().first.isHead = false//吧旧的蛇头置为false

        var head = DataPosition()
        getPositionList().addFirst(head.addHeadPosition(mNextPosition))//建一个新的蛇头

        mHeadPosition = getPositionList().first.position
        mBackPosition = getPositionList().last.position


        drawList(canvas, paintHead, paintBody)

    }


    //画蛇第二步
    private fun drawList(canvas: Canvas, paintHead: Paint, paintBody: Paint) {

        getPositionList().forEach {
            draw(canvas, it.indexX, it.indexY, if (it.isHead) paintHead else paintBody)
        }


    }


    /**
     * 检查一 ,检查要移动的方向不能是反的
     */
    fun checkPosition(type: EnumMoveType): Boolean {

        var isCanMove = true//是否可以向某个方向移动

        mNextPosition = getNext(type)//获取位置

        //比较第二块是否跟下一个点一样 一样则说明是反的 则不改变方向
        if (getPositionList().get(1).position == mNextPosition)
            isCanMove = false


        //不能移动则使用上一个移动方向的点
        if (!isCanMove)
            mNextPosition = getNext(mCurrentOrientation) //获取位置

        return isCanMove
    }


    /**
     * 检查二 ,检查是否撞墙
     */
    private fun isCrashWall() {

        //上下是否撞墙    i in [0, 399) 排除了 399
        var isTop_Bottom = mNextPosition !in 0 until SnakeConfig.GAME_ROW_COUNT * SnakeConfig.GAME_COLUMN_COUNT

        //左边是否撞墙
        var isLeft = getPositionList().first.position % 20 == 0 && mNextPosition % 20 == SnakeConfig.GAME_COLUMN_COUNT - 1

        //右边是否撞墙
        var isRight = getPositionList().first.position % 20 == SnakeConfig.GAME_COLUMN_COUNT - 1 && mNextPosition % 20 == 0

        if (isTop_Bottom || isLeft || isRight) {
            mNextPosition = getPositionList().first.position
            mCrash?.onCrash("你碰到了墙壁")
        }
    }

    /**
     * 检查三 ,检查是否碰到了自己
     * 只要碰到除了第二个位置以外的身体就算吃到自己(第三个位置也碰不到 不过无所谓)
     */
    private fun checkEatMySelf() {
        var theTwoPosition = getPositionList().get(1).position//第二个位置的index

        //这里用foreach形式是因为  遍历速度 快于普通i++方式 (linklist)
        getPositionList().forEach {
            if (!it.isHead && it.position != theTwoPosition && it.position == mNextPosition) {
                mCrash?.onCrash("你吃到了自己") //gameover
                return
            }
        }

    }


    //获取下一个移动的坐标点
    private fun getNext(type: EnumMoveType): Int {
        when (type) {
            EnumMoveType.LEFT -> return mHeadPosition - 1
            EnumMoveType.RIGHT -> return mHeadPosition + 1
            EnumMoveType.UP -> return mHeadPosition - 20
            EnumMoveType.DOWN -> return mHeadPosition + 20
        }
        return 0
    }


}