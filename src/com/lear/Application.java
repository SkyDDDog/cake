package com.lear;

import com.lear.game.Award;
import com.lear.game.Game;
import com.lear.game.Player;
import com.lear.util.RollUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 主启动类
 * @author 天狗
 */
public class Application {


    public static void main(String[] args) {
//        List<Integer> rollList = RollUtil.multiRoll();
//        rollList.forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);

        Game game = new Game();
        game.initAward();
        int playerNumber = game.initPlayer();
        ArrayList<Player> playList = new ArrayList<>(playerNumber);
        for (int i = 0; i < playerNumber; i++) {
            playList.add(new Player());
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < playerNumber; j++) {
                Player player = playList.get(j);
                System.out.print("["+i+"]"+(j+1)+"号玩家请掷骰子(y or n):");
                String flag = scanner.nextLine();
                flag = flag.replaceAll(" ", "").toLowerCase();
                if ("y".equals(flag)) {
                    Integer roll = RollUtil.roll();
                    player.setRoll(i, roll);
                    System.out.println("恭喜"+(j+1)+"号玩家在第"+(i+1)+"次投出"+roll+"点!");
                    // 特殊处理最后一轮 - 计算奖品
                    if (!Award.noAwardLeft()) {
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
                playList.set(j, player);
            }
            if (0 <= Award.topAward) {
                System.out.println("最终的状元是....."+Award.NO_AWARD+"号玩家!");
            }

        }



        scanner.close();
    }


}
