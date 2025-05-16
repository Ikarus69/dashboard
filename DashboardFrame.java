package com.mpfmtmis.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class DashboardFrame extends JFrame {
    private static final int SIDEBAR_WIDTH = 130;
    private static final Color SIDEBAR_BG_COLOR = new Color(39, 114, 28);
    private static final Color PANEL_BG_COLOR = Color.WHITE;

    private final JPanel mainContentPanel;

    public DashboardFrame() {
        setTitle("Modern Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createSidebar(), BorderLayout.WEST);

        mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.add(createDashboardContent(), BorderLayout.CENTER);
        mainPanel.add(mainContentPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 0));
        sidebar.setBackground(SIDEBAR_BG_COLOR);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(SIDEBAR_BG_COLOR);

        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(createMenuButton("☰"));
        contentPanel.add(Box.createVerticalStrut(40));

        String[] items = {"Dashboard", "Meal Plan", "Food", "Progress Tracker", "Schedule", "Notification"};
        for (String item : items) {
            contentPanel.add(createSidebarButton(item));
            contentPanel.add(Box.createVerticalStrut(30));
        }

        contentPanel.add(Box.createVerticalGlue());

        sidebar.add(contentPanel, BorderLayout.NORTH);
        return sidebar;
    }

    private JButton createMenuButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("SansSerif", Font.BOLD, 24));
        btn.setForeground(Color.WHITE);
        btn.setBackground(SIDEBAR_BG_COLOR);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        return btn;
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(SIDEBAR_BG_COLOR);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }

    private JPanel createDashboardContent() {
        JPanel content = new JPanel();
        content.setBackground(PANEL_BG_COLOR);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(20, 20, 20, 20));

        content.add(Box.createVerticalStrut(30));
        content.add(createTopBar());
        content.add(Box.createVerticalStrut(20));

        JLabel categoriesLabel = new JLabel("Categories");
        categoriesLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        categoriesLabel.setBorder(new EmptyBorder(0, 10, 5, 0));
        categoriesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        categoriesLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, categoriesLabel.getPreferredSize().height));
        content.add(categoriesLabel);

        content.add(Box.createVerticalStrut(10));
        content.add(createCategoryPanel());

        content.add(Box.createVerticalStrut(30));
        content.add(createFoodGrid());

        content.add(Box.createVerticalStrut(50));
        content.add(createScrollableNotificationPanel());

        return content;
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(PANEL_BG_COLOR);
        topBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftPanel.setBackground(PANEL_BG_COLOR);

        JLabel backIcon = new JLabel("←");
        backIcon.setFont(new Font("SansSerif", Font.BOLD, 18));
        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));

        leftPanel.add(backIcon);
        leftPanel.add(title);
        topBar.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightPanel.setBackground(PANEL_BG_COLOR);

        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(900, 30));
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        JLabel profileIcon = new JLabel("👤");
        profileIcon.setFont(new Font("SansSerif", Font.PLAIN, 20));

        rightPanel.add(searchField);
        rightPanel.add(profileIcon);
        topBar.add(rightPanel, BorderLayout.EAST);

        return topBar;
    }

    private JPanel createCategoryPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 20, 0));
        panel.setBackground(PANEL_BG_COLOR);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

        String[] categories = {"Breakfast", "Lunch", "Dinner", "Snack"};
        String[] images = {"/egg.jpg", "/lunch.jfif", "/dinner.jfif", "/snack.jfif"};

        for (int i = 0; i < categories.length; i++) {
            panel.add(createCategoryCard(categories[i], images[i]));
        }

        return panel;
    }

    private JPanel createCategoryCard(String title, String imagePath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(PANEL_BG_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel imgLabel = createScaledImageLabel(imagePath, 280, 120);
        imgLabel.setVerticalAlignment(SwingConstants.TOP);
        card.add(imgLabel, BorderLayout.CENTER);

        JLabel textLabel = new JLabel(title, SwingConstants.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textLabel.setBorder(new EmptyBorder(8, 0, 0, 0));
        card.add(textLabel, BorderLayout.SOUTH);

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // When clicked, open FoodPage
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                mainContentPanel.removeAll();

                // Use FoodPage panel
                FoodPage foodPage = new FoodPage(title, () -> {
                    // Back action: show dashboard again
                    showDashboardContent();
                });

                mainContentPanel.add(foodPage, BorderLayout.CENTER);
                mainContentPanel.revalidate();
                mainContentPanel.repaint();
            }
        });

        return card;
    }

    private void showDashboardContent() {
        mainContentPanel.removeAll();
        mainContentPanel.add(createDashboardContent(), BorderLayout.CENTER);
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private JPanel createFoodGrid() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 20, 0));
        panel.setBackground(PANEL_BG_COLOR);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 280));
        String[] foodImages = {"/snack.jfif", "/dinner.jfif", "/lunch.jfif", "/egg.jpg"};
        for (String img : foodImages) {
            panel.add(createFoodCard(img));
        }
        return panel;
    }

    private JPanel createFoodCard(String imagePath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(PANEL_BG_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
        JLabel imgLabel = createScaledImageLabel(imagePath, 280, 250);
        card.add(imgLabel, BorderLayout.CENTER);
        return card;
    }

    private JScrollPane createScrollableNotificationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_BG_COLOR);
        for (int i = 1; i <= 10; i++) {
            JLabel notif = new JLabel("\u2022 Notification " + i);
            notif.setFont(new Font("SansSerif", Font.PLAIN, 16));
            notif.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel.add(notif);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(0, 180));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        return scrollPane;
    }

    private JLabel createScaledImageLabel(String imagePath, int width, int height) {
        URL imgUrl = getClass().getResource(imagePath);
        if (imgUrl == null) {
            JLabel placeholder = new JLabel("Image Not Found", SwingConstants.CENTER);
            placeholder.setPreferredSize(new Dimension(width, height));
            placeholder.setForeground(Color.RED);
            return placeholder;
        }
        ImageIcon icon = new ImageIcon(imgUrl);
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setPreferredSize(new Dimension(width, height));
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DashboardFrame::new);
    }
}