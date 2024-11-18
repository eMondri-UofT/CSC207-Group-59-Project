package view;

import interface_adapter.fall_for_trap.FallForTrapController;
import interface_adapter.fall_for_trap.FallForTrapState;
import interface_adapter.fall_for_trap.FallForTrapViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The View for when the user fell for a trap.
 */
public class FallForTrapView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "fall for trap";
    private final FallForTrapViewModel fallForTrapViewModel;

    private final JLabel title;
    private final JTextArea description;
    private final JButton moveOn;
    private FallForTrapController fallForTrapController;

    public FallForTrapView(FallForTrapViewModel fallForTrapViewModel) {

        this.fallForTrapViewModel = fallForTrapViewModel;
        this.fallForTrapViewModel.addPropertyChangeListener(this);

        title = new JLabel("You got hit by a [Poison Trap]!");
        title.setAlignmentX(CENTER_ALIGNMENT);

        description = new JTextArea("It dealt 2 damage to you and corroded your [Rusty Breastplate].", 3, 20);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(description);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.add(scrollPane);
        descriptionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        moveOn = new JButton("Move On");

        final JPanel buttons = new JPanel();
        buttons.add(moveOn);
        buttons.setAlignmentX(CENTER_ALIGNMENT);

        moveOn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(moveOn)) {
                            final FallForTrapState currentState = fallForTrapViewModel.getState();

                            fallForTrapController.execute();
                        }
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(title);
        this.add(descriptionPanel);
        this.add(buttons);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(Box.createVerticalGlue());

    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final FallForTrapState state = (FallForTrapState) evt.getNewValue();
    }

    public String getViewName() {
        return viewName;
    }

    public void setFallForTrapController(FallForTrapController fallForTrapController) {
        this.fallForTrapController = fallForTrapController;
    }
}