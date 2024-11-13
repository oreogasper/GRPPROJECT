package view;

import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View for the Statistics Use Case.
 */
public class StatsView extends JPanel {
    private final String viewName = "stats";

    private StatisticsController statisticsController;

    private final JButton cancel;
    private final JButton leaderboard;
    JTable j;

    public StatsView(interface_adapter.statistics.StatisticsViewModel statisticsViewModel) {
        // welcomeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(StatisticsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        leaderboard = new JButton(StatisticsViewModel.TO_LEADERBOARD_BUTTON_LABEL);
        buttons.add(leaderboard);
        cancel = new JButton(StatisticsViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        // TEMPORARY BUTTONS
        final JTable info = new JTable();
        String[][] data = {
                {"Tokens", "0"},
                {"Wins", "10"},
                {"Losses", "20"},
                {"Win percentage", "30"},
                {"Games", "40"}
        };

        // Column Names
        String[] columnNames = {"Name", "Roll Number"};

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(0, 10, getWidth(), getHeight());

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        statisticsController.switchToWelcomeView();
                    }
                }
        );

        leaderboard.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        statisticsController.switchToWelcomeView();
                    }
                }
        );

        // TEMPORARY BUTTON ACTION LISTENERS

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
        this.add(j);
    }

    public String getViewName() {
        return viewName;
    }

    public void setStatisticsController(StatisticsController controller) {
        this.statisticsController = controller;
    }
}
