package com.jack.mysnake_demo.game_snake.snake_object

/**
 * Class for: 坐标对象 实体类
 * indexX:X坐标
 * indexY:y坐标
 * position:位于网格图中的第几个位置 横着数
 * isHead:这个坐标是不是头部                 todo 暂时没什么用  以后说不定有用
 * isBack:这个坐标是不是蛇尾                 todo 暂时没什么用  以后说不定有用
 * 2个参数构成一个点
 */
data class DataPosition(var position: Int, var isHead: Boolean, var isBack: Boolean, var indexX: Float, var indexY: Float) {

    //无参构造函数
    constructor() : this(0, false, false, 0f, 0f)


    //一个参构造函数
    constructor(position: Int, isHead: Boolean, isBack: Boolean) : this(position, isHead, isBack, 0f, 0f) {
        setXY(position)
    }


    //添加新一个蛇头
    fun addHeadPosition(p: Int): DataPosition {
        isHead = true
        isBack = false
        setXY(p)
        return this
    }


    //添加新一个蛇尾
    fun addBackPosition(p: Int): DataPosition {
        isBack = true
        isHead = false
        setXY(p)
        return this
    }


    private fun setXY(p: Int) {
        position = p
        indexX = SnakeConfig.getSingleRowX(position % SnakeConfig.GAME_COLUMN_COUNT)//计算x的位置
        indexY = SnakeConfig.getSingleColumnY(position / SnakeConfig.GAME_COLUMN_COUNT)//计算y的位置
    }
}