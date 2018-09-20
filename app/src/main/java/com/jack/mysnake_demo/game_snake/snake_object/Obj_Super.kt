package com.jack.mysnake_demo.game_snake.snake_object


import android.graphics.Canvas
import android.graphics.Paint
import com.jack.mysnake_demo.game_snake.snake_object.SnakeConfig.GRID_HEIGHT
import com.jack.mysnake_demo.game_snake.snake_object.SnakeConfig.GRID_WIDTH

/**
 * Class for: 游戏对象父类与子类
 * Created by   jack.马
 * Date: 2018/8/23
 * Time: 11:17
 *  open  //这个类具有`open`属性，可以被其他类继承
 */
open class GameModel {

    //这个类没什么意义 用于学习kt 和可能用于转化子类
    var row: Int = SnakeConfig.GAME_ROW_COUNT
    var column: Int = SnakeConfig.GAME_COLUMN_COUNT


    constructor() : super()

    constructor(row: Int, column: Int) : super() {
        this.row = row
        this.column = column;
    }

    //`open`属性的方法，可以被继承和覆写 没有则不可被儿子复写 //`final`修饰一个原本具有`open`属性的方法，使其变得不可再被覆写
    open fun draw(canvas: Canvas, x: Float, y: Float, paint: Paint) {
        canvas.drawRect(x, y, x + GRID_WIDTH, y + GRID_HEIGHT, paint)

    }
}
