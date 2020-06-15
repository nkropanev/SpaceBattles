package com.scope.game;

import javax.swing.*;

public class Game {

    public static final String NAME = "Space battles";

    public static void main(String[] args) {

        JFrame window = new JFrame(NAME);
        window.add(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

}
