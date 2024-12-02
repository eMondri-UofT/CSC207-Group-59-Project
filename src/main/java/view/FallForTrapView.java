package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.fall_for_trap.FallForTrapController;
import interface_adapter.fall_for_trap.FallForTrapState;
import interface_adapter.fall_for_trap.FallForTrapViewModel;

/**
 * The View for when the user fell for a trap.
 */
public class FallForTrapView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final int HEIGHT = 10;

    private final String viewName = "fall for trap";
    private final FallForTrapViewModel fallForTrapViewModel;

    private final JLabel title;
    private final JLabel description;
    private final JLabel health;
    private final JButton moveOn;

    private final JPanel buttonPanel;

    private FallForTrapController fallForTrapController;

    public FallForTrapView(FallForTrapViewModel fallForTrapViewModel) {

        this.fallForTrapViewModel = fallForTrapViewModel;
        this.fallForTrapViewModel.addPropertyChangeListener(this);

        title = new JLabel();
        title.setAlignmentX(CENTER_ALIGNMENT);

        description = new JLabel();
        description.setAlignmentX(CENTER_ALIGNMENT);

        health = new JLabel();
        health.setAlignmentX(CENTER_ALIGNMENT);

        moveOn = new JButton("Move On");

        buttonPanel = new JPanel();
        buttonPanel.add(moveOn);
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);

        moveOn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(moveOn)) {
                            fallForTrapController.exitInteraction();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(Box.createRigidArea(new Dimension(0, HEIGHT)));
        this.add(title);
        this.add(description);
        this.add(health);
        this.add(Box.createRigidArea(new Dimension(0, HEIGHT)));
        this.add(buttonPanel);
        this.add(Box.createRigidArea(new Dimension(0, HEIGHT)));
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

        title.setText("You got hit by [" + state.getName() + "]!");
        description.setText(String.format("It dealt %d damage to you.", state.getDamage()));
        health.setText("Health: " + state.getHealth());
    }

    public String getViewName() {
        return viewName;
    }

    public void setFallForTrapController(FallForTrapController fallForTrapController) {
        this.fallForTrapController = fallForTrapController;
    }
}
