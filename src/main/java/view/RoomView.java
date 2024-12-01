package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

import interface_adapter.room_default.RoomDefaultController;
import interface_adapter.room_default.RoomDefaultState;
import interface_adapter.room_default.RoomDefaultViewModel;

/**
 * The View for displaying room interactions.
 */
public class RoomView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "room view";
    private final RoomDefaultViewModel roomViewModel;

    private final JLabel roomDescriptionLabel;

    private RoomDefaultController roomController;

    private final JButton interactButton;
    private final JButton nextRoomButton;
    private final JButton openInventoryButton;

    public RoomView(RoomDefaultViewModel roomViewModel) {
        this.roomViewModel = roomViewModel;
        this.roomViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Dungeon Room");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        roomDescriptionLabel = new JLabel();
        roomDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        interactButton = new JButton("Interact");
        nextRoomButton = new JButton("Next Room");
        openInventoryButton = new JButton("Open Inventory");

        final JPanel buttons = new JPanel();
        buttons.add(interactButton);
        buttons.add(nextRoomButton);
        buttons.add(openInventoryButton);
        buttons.setAlignmentX(CENTER_ALIGNMENT);

        interactButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(interactButton)) {
                            final RoomDefaultState currentState = roomViewModel.getState();
                            roomController.interactWithRoom(currentState.getRoomNumber());
                        }
                    }
                }
        );

        nextRoomButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(nextRoomButton)) {
                            roomController.goToNextRoom();
                        }
                    }
                }
        );

        openInventoryButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(openInventoryButton)) {
                            roomController.returnToMainMenu();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(title);
        this.add(roomDescriptionLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(buttons);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(Box.createVerticalGlue());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "General action triggered: " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final RoomDefaultState state = (RoomDefaultState) evt.getNewValue();
        updateView(state);
    }

    private void updateView(RoomDefaultState state) {
        roomDescriptionLabel.setText(state.getRoomDescription());
    }

    public String getViewName() {
        return viewName;
    }

    public void setRoomController(RoomDefaultController controller) {
        this.roomController = controller;
    }
}
