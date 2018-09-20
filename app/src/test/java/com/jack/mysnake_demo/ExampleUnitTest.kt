package com.jack.mysnake_demo

import android.util.SparseIntArray
import org.junit.Test
import java.util.*
import javax.xml.transform.Source

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun addition_isCorrect() {

//        for (i in 2 - 1 downTo 0) {
//            println(i)
//        }

        println(20 / 20)
        println(20 % 20)
        println(19 / 20)
        println(19 % 20)

//            var sparryList = SparseIntArray()
//            sparryList.put(121, 1)
//            println(sparryList.get(121))
//            println(sparryList.get(40))
        println("分割线")


        val list: MutableList<String> = mutableListOf("A", "B", "C")
        val change: Any
        change = list.let {
            it.add("D")
            it.add("E")
            it.size

        }
        println("list = $list")
        println("change = $change")


    }

    @Test
    fun testLink() {
        var link = getLink()

        var long1 = System.currentTimeMillis()
        for (str in link) {
        }
        println(System.currentTimeMillis() - long1)


        var long2 = System.currentTimeMillis()
        for (i in link.indices) {
            link.get(i)
        }
        println(System.currentTimeMillis() - long2)
    }


    //模拟数据
    private fun getLink(): LinkedList<String> {
        var link = LinkedList<String>()
        for (i in 0..60000)
            link.add(i.toString() + "我的天啊")
        return link

    }
}
