package com.mpfmtmis.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FoodPage extends JPanel {
    private Runnable backAction;

    public FoodPage(String category, Runnable backAction) {
        this.backAction = backAction;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Top bar with back button and title
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);

        JButton backBtn = new JButton("← Back");
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> {
            if (backAction != null) backAction.run();
        });

        topBar.add(backBtn, BorderLayout.WEST);

        JLabel title = new JLabel("Food - " + category);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(0, 10, 0, 0));
        topBar.add(title, BorderLayout.CENTER);

        add(topBar, BorderLayout.NORTH);

        // Center message
        JLabel message = new JLabel("Food page for \"" + category + "\" is not implemented yet.", SwingConstants.CENTER);
        message.setFont(new Font("SansSerif", Font.PLAIN, 18));
        message.setBorder(new EmptyBorder(50, 10, 10, 10));

        add(message, BorderLayout.CENTER);
    }
}