package com.example.a80797.bookhotel_recognize.view.water;

import android.content.Context;

import java.util.Random;

/**

 */

public class Utils {

    private static Random random = new Random();

    public static int dp2px(Context context, int values) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

    /**
     * 区间随机
     *
     * @param lower
     * @param upper
     * @return
     */
    public static float getRandom(float lower, float upper) {
        float min = Math.min(lower, upper);
        float max = Math.max(lower, upper);
        return getRandom(max - min) + min;
    }

    /**
     * 上界随机
     *
     * @param upper
     * @return
     */
    public static float getRandom(float upper) {
        return random.nextFloat() * upper;
    }

    /**
     * 上界随机
     *
     * @param upper
     * @return
     */
    public static int getRandom(int upper) {
        return random.nextInt(upper);
    }
}
