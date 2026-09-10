import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        // Create Main Menu
        JFrame frame = new JFrame("Meal Plan Tracker");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Show Main Menu
        showMainMenu(frame);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static void showMainMenu(JFrame frame) {
        frame.getContentPane().removeAll();

        // Panel
        JPanel panel = new JPanel();

        // Create New Plan
        JButton createPlanButton = new JButton("Create New Plan");
        createPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Takes to create plan screen
                showCreatePlanScreen(frame);
            }
        });

        // Saved Plan
        JButton savedPlanButton = new JButton("Saved Plan");
        savedPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // I'll code this once I finish the program and then go back to make a way to save data
            }
        });

        panel.add(createPlanButton);
        panel.add(savedPlanButton);
        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showCreatePlanScreen(JFrame frame) {
        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Options
        JTextField nameField = new JTextField();
        JTextField balanceField = new JTextField();
        JTextField weeksField = new JTextField();
        JTextField currentWeekField = new JTextField();
        JTextField breakCostField = new JTextField();
        JTextField lunchCostField = new JTextField();
        JTextField dinnerCostField = new JTextField();

        formPanel.add(new JLabel("Name of plan:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Meal plan balance ($):"));
        formPanel.add(balanceField);

        formPanel.add(new JLabel("Weeks of school:"));
        formPanel.add(weeksField);

        formPanel.add(new JLabel("Current week:"));
        formPanel.add(currentWeekField);

        JPanel formPanel2 = new JPanel(new GridLayout(2, 3, 10, 10));
        formPanel2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        formPanel2.add(new JLabel("Breakfast Cost"));
        formPanel2.add(new JLabel("Lunch Cost"));
        formPanel2.add(new JLabel("Diner Cost"));

        formPanel2.add(breakCostField);
        formPanel2.add(lunchCostField);
        formPanel2.add(dinnerCostField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainMenu(frame);
            }
        });
        buttonPanel.add(backButton);

        // Continue Button
        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If all text fields filled - continue - else tell user to fill in all fields
                String name = nameField.getText().trim();
                String balance = balanceField.getText().trim();
                String weeks = weeksField.getText().trim();
                String currentWeek = currentWeekField.getText().trim();
                String breakfastCost = breakCostField.getText().trim();
                String lunchCost = lunchCostField.getText().trim();
                String dinnerCost = dinnerCostField.getText().trim();

                if (name.isEmpty() || balance.isEmpty() || weeks.isEmpty() || currentWeek.isEmpty() ||
                        breakfastCost.isEmpty() || lunchCost.isEmpty() || dinnerCost.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Please fill in all fields before continuing.",
                            "Missing Information",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!isNumeric(balance)) {
                    JOptionPane.showMessageDialog(frame,
                            "Please make sure balance is a number",
                            "Incorrect Input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!isNumeric(weeks)) {
                    JOptionPane.showMessageDialog(frame,
                            "Please make sure weeks is a whole number",
                            "Incorrect Input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!isNumeric(currentWeek)) {
                    JOptionPane.showMessageDialog(frame,
                            "Please make sure current week is a whole number",
                            "Incorrect Input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!isNumeric(breakfastCost)) {
                    JOptionPane.showMessageDialog(frame,
                            "Please make sure breakfast cost is a number",
                            "Incorrect Input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!isNumeric(lunchCost)) {
                    JOptionPane.showMessageDialog(frame,
                            "Please make sure lunch cost is a number",
                            "Incorrect Input",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!isNumeric(dinnerCost)) {
                    JOptionPane.showMessageDialog(frame,
                            "Please make sure dinner cost is a number",
                            "Incorrect Input",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // Create MealPlan Object and Continue to Next Screen
                    ArrayList<Integer> costs = new ArrayList<>();
                    costs.add(Integer.parseInt(breakfastCost));
                    costs.add(Integer.parseInt(lunchCost));
                    costs.add(Integer.parseInt(dinnerCost));
                    MealPlan plan = new MealPlan(name, Double.parseDouble(balance), Integer.parseInt(weeks),
                            Integer.parseInt(currentWeek), costs);
                    showPlanScreen(frame, plan);
                }
            }
        });
        buttonPanel.add(continueButton);

        // Update Frame
        mainPanel.add(formPanel);
        mainPanel.add(formPanel2);
        mainPanel.add(buttonPanel);
        frame.add(mainPanel);
        frame.revalidate();
        frame.repaint();
    }

    private static void showPlanScreen(JFrame frame, MealPlan plan) {
        frame.getContentPane().removeAll();

        JPanel titlePanel = new JPanel(new GridLayout(1, 1, 10, 10));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel titleLabel = new JLabel(plan.getName());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        titlePanel.add(titleLabel);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JLabel balanceLabel = new JLabel(String.format("Balance: $%.2f", plan.getBalance()));
        JLabel weekLabel = new JLabel(String.format("$/Week: $%.2f", plan.calculateWeeklySpend()));
        JLabel dayLabel = new JLabel(String.format("$/Day: $%.2f", plan.calculateDailySpend()));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        weekLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(balanceLabel);
        infoPanel.add(weekLabel);
        infoPanel.add(dayLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainMenu(frame);
            }
        });
        buttonPanel.add(backButton);

        // Log Payment Button
        JButton logPaymentButton = new JButton("Log Payment");
        logPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Take to log payment screen
                showLogPaymentScreen(frame, plan);
            }
        });
        buttonPanel.add(logPaymentButton);

        // Edit Payment Options Button
        JButton editPaymentOptionsButton = new JButton("Edit Payment Options");
        editPaymentOptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // WIP
            }
        });
        buttonPanel.add(editPaymentOptionsButton);

        // Edit Account Info Button
        JButton editAccountInfoButton = new JButton("Edit Account Info");
        editAccountInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // WIP
            }
        });
        buttonPanel.add(editAccountInfoButton);

        // Create MainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(titlePanel);
        mainPanel.add(infoPanel);
        mainPanel.add(buttonPanel);

        // Update Frame
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.revalidate();
        frame.repaint();
    }

    private static void showLogPaymentScreen(JFrame frame, MealPlan plan) {
        frame.getContentPane().removeAll();

        // Panel
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        // Log Payment Buttons
        JPanel leftButtonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JPanel rightButtonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JPanel centerButtonPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton logBreakfastButton = new JButton("Log Breakfast");
        logBreakfastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logs breakfast payment
                plan.logPayment(plan.getCosts().getFirst());
                showPlanScreen(frame, plan);
            }
        });

        JButton logLunchButton = new JButton("Log Lunch");
        logLunchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logs lunch payment
                plan.logPayment(plan.getCosts().get(1));
                showPlanScreen(frame, plan);
            }
        });

        JButton logDinnerButton = new JButton("Log Dinner");
        logDinnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logs dinner payment
                plan.logPayment(plan.getCosts().get(2));
                showPlanScreen(frame, plan);
            }
        });

        centerButtonPanel.add(logBreakfastButton);
        centerButtonPanel.add(logLunchButton);
        centerButtonPanel.add(logDinnerButton);

        // Custom Payment
        JPanel customPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        customPanel.add(new JLabel("Custom Payment:"));
        JTextField customField = new JTextField();
        customPanel.add(customField);

        buttonPanel.add(leftButtonPanel);
        buttonPanel.add(centerButtonPanel);
        buttonPanel.add(rightButtonPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(customPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}