package com.lear.game;

import com.lear.util.RollUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 游戏主题类
 * @author 天狗
 */
public class Game {

    private int playerNumber;
    private int topId;
    private List<Player> playerList;

    public Game() {
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
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

    public void initAward(int awardNumber, int index) {
        Award.awardNumber[index] = awardNumber;
    }

    public void initPlayer(int playerNumber) {
        this.playerNumber = playerNumber;
        playerList = new ArrayList<>(playerNumber);
        for (int i = 0; i < playerNumber; i++) {
            this.playerList.add(new Player());
        }
    }

    public int initPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入玩家人数:");
        initPlayer(scanner.nextInt());
        playerList = new ArrayList<>(playerNumber);
        for (int i = 0; i < playerNumber; i++) {
            playerList.add(new Player());
        }
        return playerNumber;
    }

    public int rollOnce(int playerIndex, int rollIndex) {
        Player player = playerList.get(playerIndex);
        Integer roll = RollUtil.roll();
        if (!Award.noAwardLeft(rollIndex+1)) {
            player.setRoll(rollIndex, roll);
//            if (rollIndex == 5) {
//                int awardNo = Award.getAwardNo(player.getRollList(), playerIndex);
//                if (awardNo < Award.ZHUANG_YUAN_1) {
//                    awardNo = Award.getAwardNo(player.getRollList(), playerIndex);
//                    System.out.println("恭喜"+playerIndex+"号玩家获得:["+award+"]");
//                    return awardNo;
//                } else {
//                    System.out.println("恭喜你成为状元,请等待有没有人抢走你的奖品(手动狗头)");
//                    return Award.ZHUANG_YUAN_1;
//                }
//            }
            return roll;
        }
        return -1;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < playerNumber; j++) {
                Player player = playerList.get(j);
                System.out.print("["+i+"]"+(j+1)+"号玩家请掷骰子(y or n):");
                String flag = scanner.nextLine();
                flag = flag.replaceAll(" ", "").toLowerCase();
                if ("y".equals(flag)) {
                    Integer roll = RollUtil.roll();
                    player.setRoll(i, roll);
                    System.out.println("恭喜"+(j+1)+"号玩家在第"+(i+1)+"次投出"+roll+"点!");
                    // 特殊处理最后一轮 - 计算奖品
                    if (Award.noAwardLeft()) {
                        System.out.println("奖品分发完毕！游戏结束~");
                        return;
                    }
                    if (i==5) {
//                        int award = Award.getAward(player.getRollList());
                        int awardNo = Award.calculateAward(player.getRollList());
                        if (awardNo<Award.ZHUANG_YUAN_1) {
                            String award = Award.getAward(player.getRollList(), i);
                            System.out.println("恭喜"+i+"号玩家获得:["+award+"]");
                        } else {
                            System.out.println("恭喜你成为状元,请等待有没有人抢走你的奖品(手动狗头)");
                        }
                    }
                } else {
                    continue;
                }
                playerList.set(j, player);
            }
            if (0 <= Award.topAward) {
                System.out.println("最终的状元是....."+Award.NO_AWARD+"号玩家!");
            }

        }

    }


}
