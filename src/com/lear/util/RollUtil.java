package com.lear.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 骰点工具类
 * @author 天狗
 */
public class RollUtil {

    /**
     * 默认骰点(1~6)
     * @return 随机骰点
     */
    public static Integer roll() {
        return roll(1, 6);
    }

    /**
     * 六次默认骰点
     * @return 六次随机骰点结果
     */
    public static List<Integer> multiRoll() {
        return multiRoll(6);
    }

    /**
     * 多次默认骰点
     * @param time 骰掷次数
     * @return 随机骰点list
     */
    public static List<Integer> multiRoll(int time) {
        ArrayList<Integer> result = new ArrayList<>(time);
        for (int i = 0; i < time; i++) {
            result.add(roll());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 指定范围骰点
     * @param max 最大值
     * @param min 最小值
     * @return 随机骰点
     */
    public static int roll(int min, int max){
        Date date = new Date();
        long seed = Long.parseLong(String.format("%tN", date));
        Random r = new Random(seed);
        return min + r.nextInt(max - min);
    }


}
