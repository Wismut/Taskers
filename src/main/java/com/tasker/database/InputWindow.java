package com.tasker.database;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class InputWindow extends JFrame {

    public InputWindow() {
        super("Select the file and time");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());

        final JLabel path_program = new JLabel();
        path_program.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(path_program);

        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        final JTextField start_date = new JTextField();

        final JLabel format_date = new JLabel("Please, insert date in the format YYYY-MM-DD HH:MM:SS");
        format_date.setAlignmentX(CENTER_ALIGNMENT);

        JButton BtnExe = new JButton("Add exe file");
        BtnExe.setAlignmentX(CENTER_ALIGNMENT);


        JButton BtnAdd = new JButton("OK");
        BtnAdd.setAlignmentX(CENTER_ALIGNMENT);

        BtnExe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    path_program.setText(file.getAbsolutePath());
                }
            }
        });

        BtnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataBase dataBase = new DataBase();
                try {
                    dataBase.InsertData(path_program.getText(), start_date.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Incorrect date or time", "Warning!", JOptionPane.INFORMATION_MESSAGE);
                }
                new RunPrograms(start_date.getText(), path_program.getText());
            }
        });

        panel.add(BtnExe);
        panel.add(format_date);
        panel.add(start_date);
        panel.add(BtnAdd);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(400, 160));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void runWindow() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                new InputWindow();
            }
        });
    }
}
