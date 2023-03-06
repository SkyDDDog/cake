package com.lear.game;

import java.util.Scanner;

/**
 * 游戏主题类
 * @author 天狗
 */
public class Game {

    private int playerNumber;
    private int topId;

    public Game() {
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getTopId() {
        return topId;
    }

    public void setTopId(int topId) {
        this.topId = topId;
    }

    public void initAward() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入奖品数~");

        for (int i = 1; i < Award.NAME.length; i++) {
            System.out.print(Award.NAME[i]+"数:");
            Award.awardNumber[i] = scanner.nextInt();
        }

    }

    public void initPlayer(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int initPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入玩家人数:");
        initPlayer(scanner.nextInt());
        return playerNumber;
    }


}
