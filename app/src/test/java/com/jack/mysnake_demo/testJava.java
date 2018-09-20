package com.jack.mysnake_demo;

import android.util.SparseIntArray;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class for:
 * Created by   jack.马
 * Date: 2018/9/12
 * Time: 14:26
 */
public class testJava {

    @Test
    public void test1() {
        LinkedList<String> link = getLink();
        long long1 = System.currentTimeMillis();

        for (String str : link) {
            str = "fuck";
        }
        System.out.println("foreach耗时" + (System.currentTimeMillis() - long1));

        long long2 = System.currentTimeMillis();
        for (int i = 0; i < link.size(); i++) {
            String a = link.get(i);
            a = "fuck";
        }
        System.out.println("i++循环耗时" + (System.currentTimeMillis() - long2));


    }

    //模拟数据
    private LinkedList<String> getLink() {
        long long1 = System.currentTimeMillis();
        LinkedList<String> link = new LinkedList<>();
        for (int i = 0; i < 50000; i++)
            link.add(i + "我的天啊");
        System.out.println("填充数据耗时" + (System.currentTimeMillis() - long1));


        return link;

    }

    //模拟数据
    private ArrayList<String> getArray() {
        long long1 = System.currentTimeMillis();
        ArrayList<String> link = new ArrayList<>();
        for (int i = 0; i < 10000; i++)
            link.add(i + "我的天啊");
        System.out.println("填充数据耗时" + (System.currentTimeMillis() - long1));
        return link;

    }


    @Test
    public void test2() {
        ArrayList<String> array = getArray();
        LinkedList<String> link = getLink();


        long long1 = System.currentTimeMillis();
        Iterator<String> iterator = link.iterator();
        while (iterator.hasNext()) {
            String a = iterator.next();
            iterator.remove();
        }
        System.out.println("link的移除时间" + (System.currentTimeMillis() - long1));

        long long2 = System.currentTimeMillis();
        Iterator<String> iterator2 = array.iterator();
        while (iterator2.hasNext()) {
            String a = iterator2.next();
            iterator2.remove();
        }
        System.out.println("array的移除时间" + (System.currentTimeMillis() - long2));
        System.out.println("link的数量" + link.size() + "  array的数量" + array.size());

    }
}
