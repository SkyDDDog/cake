package com.lear.game;

import java.util.Arrays;

/**
 * 奖品类
 * @author 天狗
 */
public class Award {

    public static final String[] NAME = {"状元", "对堂", "三红", "四进", "二举", "一秀"};
    public static int[] awardNo = new int[NAME.length];
    public static int topNo;

    public static final int YI_XIU = 1;
    public static final int ER_JU = 2;
    public static final int SI_JIN = 3;
    public static final int SAN_HONG = 4;
    public static final int DUI_TANG = 5;
    public static final int ZHUANG_YUAN_1 = 6;
    public static final int ZHUANG_YUAN_2 = 7;
    public static final int ZHUANG_YUAN_3 = 8;
    public static final int ZHUANG_YUAN_4 = 9;

    public static final int DICE_1 = 0;
    public static final int DICE_2 = 1;
    public static final int DICE_3 = 2;
    public static final int DICE_4 = 3;
    public static final int DICE_5 = 4;
    public static final int DICE_6 = 5;

    public static int getAward(int[] rollList) {
        int award = 0;
        int[] numberCount = new int[6];
        for (int roll : rollList) {
            switch (roll) {
                case 1:
                    numberCount[DICE_1]++;
                    break;
                case 2:
                    numberCount[DICE_2]++;
                    break;
                case 3:
                    numberCount[DICE_3]++;
                    break;
                case 4:
                    numberCount[DICE_4]++;
                    break;
                case 5:
                    numberCount[DICE_5]++;
                    break;
                case 6:
                    numberCount[DICE_6]++;
                    break;
                default:
                    break;
            }
        }
        if (numberCount[DICE_1]==1) {
            // 一秀
            award = YI_XIU;
        }
        if (numberCount[DICE_3]==2) {
            // 二举
            award = ER_JU;
        }
        if (numberCount[DICE_3]==4) {
            // 四进
            award = SI_JIN;
        }
        if (numberCount[DICE_3]==3) {
            // 三红
            award = SAN_HONG;
        }
        if (numberCount[DICE_1]==1 && numberCount[DICE_2]==1 && numberCount[DICE_3]==1 && numberCount[DICE_4]==1 && numberCount[DICE_5]==1 && numberCount[DICE_6]==1) {
            // 对堂
            award = DUI_TANG;
        }
        if (numberCount[DICE_4]==4) {
            // 状元
            award = ZHUANG_YUAN_1;
        }
        if (numberCount[DICE_3]==5) {
            // 状元(五子登科)
            award = ZHUANG_YUAN_2;
        }
        if (numberCount[DICE_4]==6) {
            // 状元(六杯红)
            award = ZHUANG_YUAN_3;
        }
        if (numberCount[DICE_1]==2 && numberCount[DICE_4]==4) {
            // 状元插金花
            award = ZHUANG_YUAN_4;
        }
        return award;
    }

    public static String getAwardName(int award) {
        return NAME[award];
    }

    public static String getAwardName(int[] rollList) {
        return getAwardName(getAward(rollList));
    }

}