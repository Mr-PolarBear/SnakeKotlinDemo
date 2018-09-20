package com.jack.mysnake_demo.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.jack.mysnake_demo.MainActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;

/**
 * Class for:
 * Created by   jack.马
 * Date: 2018/9/11
 * Time: 14:55
 */
public class test1 extends Activity {

    public static void main(String[] args) {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MainActivity.class));
        Random random = new Random();
        int[] ints = new int[]{};
        int a = ints[10];

    }


}
