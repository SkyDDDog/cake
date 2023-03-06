package com.lear.game;

import com.lear.util.RollUtil;

import java.util.Arrays;

/**
 * 玩家类
 * @author 天狗
 */
public class Player {

    private int award;

    private final int[] rollList;

    public Player() {
        rollList = new int[6];
    }

    public Player(int prize) {
        this.award = prize;
        rollList = new int[6];
    }

    public int getPrize() {
        return award;
    }

    public void setPrize(int prize) {
        this.award = prize;
    }

    public int[] getRollList() {
        return rollList;
    }

    public void setRoll(int index, int roll) {
        rollList[index] = roll;
    }

    public void roll(int index) {
        this.setRoll(index, RollUtil.roll());
    }

    @Override
    public String toString() {
        return "Player{" +
                "award=" + award +
                ", rollList=" + Arrays.toString(rollList) +
                '}';
    }
}
