package com.lear.gui;

import com.lear.game.Award;
import com.lear.game.Game;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBody extends JFrame {

    private static final long serialVersionUID = -5159330521192113057L;

    JPanel root;
    JLabel playerNumberLabel, awardNumberLabel;
    JLabel[] awardLabelList = new JLabel[Award.NAME.length];
    JTextField[] awardInputList = new JTextField[Award.NAME.length];
    JLabel[] leftAwardList = new JLabel[Award.NAME.length];
    JTextField playerInput;
    JButton startButton, testButton;

    JLabel rollResult, awardResult;


    Game game;
    int rollCount = 0;


    public GameBody() {
        game = new Game();
        root = new JPanel();
        this.setTitle("博饼游戏~");
        this.setContentPane(root);
        this.setLayout(null);

        // 初始化游戏配置
        // 玩家人数标签
        playerNumberLabel = new JLabel("玩家人数: ");
        playerNumberLabel.setBounds(30,20,70,20);
        root.add(playerNumberLabel);
        // 玩家人数输入框
        playerInput = new JTextField(5);
        playerInput.setBounds(100, 20, 50, 20);
        root.add(playerInput);
        // 游戏结果展示label
        rollResult = new JLabel();
        rollResult.setBounds(180, 180, 180, 20);
        root.add(rollResult);
        awardResult = new JLabel();
        awardResult.setBounds(180,200,180,20);
        root.add(awardResult);

        // 奖品数标签
        awardNumberLabel = new JLabel("初始化奖品数量:");
        awardNumberLabel.setBounds(30,40,120,20);
        root.add(awardNumberLabel);
        for (int i = 1; i < Award.NAME.length; i++) {
            awardLabelList[i] = new JLabel(Award.NAME[i]);
            awardLabelList[i].setBounds(30, 40+i*20, 70, 20);
            root.add(awardLabelList[i]);
            awardInputList[i] = new JTextField(5);
            awardInputList[i].setBounds(100, 40+i*20, 50, 20);
            root.add(awardInputList[i]);
        }

        startButton = new JButton("载入配置~");
        startButton.setBounds(30, 180, 120 ,20 );
        startButton.addMouseListener(new GameStartAction());
        root.add(startButton);

        testButton = new JButton("测试");
        testButton.setBounds(30, 200, 120 ,20);
        testButton.addMouseListener(new RollMouseListener());
//        testButton.addMouseListener(new TestMouseListener());
        root.add(testButton);
//        leftAwardList[1].setText("test");
//        root.updateUI();


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(400, 300, 400, 300);
        this.setVisible(true);
    }

    private void updateLeftAward() {
        for (int i = 1; i < Award.NAME.length; i++) {
            awardInputList[i].setText(Integer.parseInt(awardInputList[i].getText())+"");
        }
        root.updateUI();
    }

    class GameStartAction implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // 动态展示目前配置
            JLabel player = new JLabel("玩家人数: ");
            player.setBounds(180, 20, 70, 20);
            root.add(player);
            // 初始化玩家人数
            game.initPlayer(Integer.parseInt(playerInput.getText()));
            JLabel pNumber = new JLabel(game.getPlayerNumber()+"");
            pNumber.setBounds(250, 20, 50, 20);
            root.add(pNumber);

            // 初始化奖品数
            for (int i = 1; i < Award.NAME.length; i++) {
                JLabel title = new JLabel("剩余" + Award.NAME[i] + "数:");
                title.setBounds(180, 20+20*i,70,20);
                root.add(title);
                // 初始化每个奖品数量
                game.initAward(Integer.parseInt(awardInputList[i].getText()), i);
                leftAwardList[i] = new JLabel(Award.awardNumber[i]+"");
                leftAwardList[i].setBounds(260, 20+20*i,50,20);
                root.add(leftAwardList[i]);
            }
            root.updateUI();
            startButton.removeMouseListener(this);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }


    class TestMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            String value = leftAwardList[1].getText();
            leftAwardList[1].setText(Integer.parseInt(value) + 1 + "");
            root.updateUI();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class RollMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int playerIndex = rollCount%game.getPlayerNumber();
            int rollIndex = rollCount/game.getPlayerNumber();
            System.out.println("rollCount: " + rollCount);
            System.out.println("playerIndex: "+playerIndex);
            System.out.println("rollIndex: "+rollIndex);
            int roll = game.rollOnce(playerIndex, rollIndex);
            if (0 < roll) {
                rollResult.setText("恭喜"+(playerIndex+1)+"号玩家在第"+(rollIndex+1)+"次投出"+roll+"点!");
                System.out.println("骰点: "+ roll);
                root.updateUI();
            } else {
                System.out.println("骰失败");
            }
            // 最后一轮投掷，计算奖品结果
            if (rollIndex==5) {
                int awardNo = Award.getAwardNo(game.getPlayerList().get(playerIndex).getRollList(), playerIndex);
                if (awardNo < Award.ZHUANG_YUAN_1) {
                    String award = Award.getAward(game.getPlayerList().get(playerIndex).getRollList(), playerIndex);
                    awardResult.setText("恭喜"+(playerIndex+1)+"号玩家获得:["+award+"]");
                    System.out.println("恭喜"+playerIndex+"号玩家获得:["+award+"]");

                } else {
                    awardResult.setText("恭喜"+(playerIndex+1)+"号玩家成为状元,请等待有没有人抢走你的奖品(手动狗头)");
                    System.out.println("恭喜你成为状元,请等待有没有人抢走你的奖品(手动狗头)");
                }
                root.updateUI();

                System.out.println("awardNo: "+awardNo);
                if (playerIndex+1 == game.getPlayerNumber()) {
                    System.out.println("游戏结束");
                    testButton.removeMouseListener(this);
                    JLabel ending = new JLabel("游戏结束！");
                    ending.setBounds(30, 230, 300, 20);

                    root.add(ending);
                }
                updateLeftAward();
            }

            rollCount++;
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}