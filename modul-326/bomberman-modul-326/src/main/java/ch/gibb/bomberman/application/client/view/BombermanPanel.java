package ch.gibb.bomberman.application.client.view;

import ch.gibb.bomberman.application.client.control.ControlFactory;
import ch.gibb.bomberman.application.client.control.JoinGameControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BombermanPanel extends JPanel {
    private JTextField playerNameTextField = new JTextField();
    private JTextArea messageTextArea = new JTextArea();
    public BombermanPanel() {
        setLayout(new BorderLayout());
        playerNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JoinGameControl control = ControlFactory.instance().createJoinGameControl;
                control.JoinGame(playerNameTextField.getText());
            }
        });
        add(playerNameTextField, BorderLayout.NORTH);
        messageTextArea.setRows(4);
        messageTextArea.setEditable(false);
        add(messageTextArea, BorderLayout.SOUTH);
    }

    public void displayMessage(String message)
    {
        messageTextArea.append(message + "\n");
    }
}
