package view;

import interface_adapter.character_creation.CharacterCreationController;
import interface_adapter.character_creation.CharacterCreationState;
import interface_adapter.character_creation.CharacterCreationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is on the character creator
 */
public class CharacterCreationView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "character creation";
    private final CharacterCreationViewModel characterCreationViewModel;

    // Descriptions Labels
    private JLabel classDescriptionText;
    private JLabel raceDescriptionText;

    private final JButton next;
    private CharacterCreationController characterCreationController;

    public CharacterCreationView(CharacterCreationViewModel characterCreationViewModel) {

        this.characterCreationViewModel = characterCreationViewModel;
        this.characterCreationViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Character Creation Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create classes and races (+ titles) using helper methods
        final JLabel classesTitle = new JLabel("Select Class");
        classesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JPanel barbarianBox = createClassPanel("barbarian");
        final JPanel rogueBox = createClassPanel("rogue");
        final JPanel clericBox = createClassPanel("cleric");

        final JLabel racesTitle = new JLabel("Select Race");
        racesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JPanel humanBox = createRacePanel("human");
        final JPanel dwarfBox = createRacePanel("dwarf");
        final JPanel elfBox = createRacePanel("elf");
        final JPanel gnomeBox = createRacePanel("gnome");

        // Place all three class panels in one horizontal panel for nice-looking display.
        final JPanel classes = new JPanel();
        classes.setLayout(new BoxLayout(classes, BoxLayout.X_AXIS));
        classes.add(barbarianBox);
        classes.add(rogueBox);
        classes.add(clericBox);

        // Write the class description from the state.
        JPanel classDescription = new JPanel();
        classDescription.setLayout(new BoxLayout(classDescription, BoxLayout.Y_AXIS));
        JPanel classDescriptionBox = new JPanel();
        classDescriptionBox.setLayout(new BoxLayout(classDescriptionBox, BoxLayout.Y_AXIS));
        classDescriptionBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        classDescriptionText = new JLabel(characterCreationViewModel.getState().getPClassDescription());
        classDescriptionBox.add(classDescriptionText, SwingConstants.CENTER);
        classDescription.add(classDescriptionBox);

        // Place all four race panels in one horizontal panel for nice-looking display.
        final JPanel races = new JPanel();
        races.setLayout(new BoxLayout(races, BoxLayout.X_AXIS));
        races.add(humanBox);
        races.add(dwarfBox);
        races.add(elfBox);
        races.add(gnomeBox);

        // Write the race description from the state.
        JPanel raceDescription = new JPanel();
        raceDescription.setLayout(new BoxLayout(raceDescription, BoxLayout.Y_AXIS));
        JPanel raceDescriptionBox = new JPanel();
        raceDescriptionBox.setLayout(new BoxLayout(raceDescriptionBox, BoxLayout.Y_AXIS));
        raceDescriptionBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        raceDescriptionText = new JLabel(characterCreationViewModel.getState().getPRaceDescription());
        raceDescriptionBox.add(raceDescriptionText, SwingConstants.CENTER);
        raceDescription.add(raceDescriptionBox);

        // Create the close inventory button
        final JPanel buttons = new JPanel();
        next = new JButton("Continue");
        buttons.add(next);

        next.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(next)) {
                            characterCreationController.execute();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(classesTitle);
        this.add(classes);
        this.add(classDescription);
        this.add(racesTitle);
        this.add(races);
        this.add(raceDescription);
        this.add(buttons);
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
        final CharacterCreationState state = (CharacterCreationState) evt.getNewValue();
        classDescriptionText.setText(state.getPClassDescription());
        raceDescriptionText.setText(state.getPRaceDescription());
        if (state.getError() != "") {
            showFailureMessage(state.getError());
            // Return error message to none after showing failure popup.
            state.setError("");
        }
        revalidate(); // Ensure the layout updates
        repaint();    // Ensure the changes are visible
    }

    public String getViewName() {
        return viewName;
    }

    public void setCharacterCreationController(CharacterCreationController characterCreationController) {
        this.characterCreationController = characterCreationController;
    }

    public void showFailureMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    private JPanel createRacePanel(String raceName) {
        final JPanel raceBox = new JPanel();
        raceBox.setLayout(new BoxLayout(raceBox, BoxLayout.Y_AXIS));
        JButton raceNameButton = new JButton(raceName);
        raceNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        raceBox.add(raceNameButton);
        raceBox.setBorder(BorderFactory.createLineBorder(Color.black));

        raceNameButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(raceNameButton)) {
                            final CharacterCreationState currentState = characterCreationViewModel.getState();
                            currentState.setPRace(raceName);
                            characterCreationViewModel.setState(currentState);

                            characterCreationController.execute(currentState.getPClass(), currentState.getPRace());
                        }
                    }
                }
        );
        return raceBox;
    }

    private JPanel createClassPanel(String className) {
        final JPanel classBox = new JPanel();
        classBox.setLayout(new BoxLayout(classBox, BoxLayout.Y_AXIS));
        classBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton classNameButton = new JButton(className);
        classNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        classBox.add(classNameButton);
        classBox.setBorder(BorderFactory.createLineBorder(Color.black));

        classNameButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(classNameButton)) {
                            final CharacterCreationState currentState = characterCreationViewModel.getState();
                            currentState.setPClass(className);
                            characterCreationViewModel.setState(currentState);

                            characterCreationController.execute(currentState.getPClass(), currentState.getPRace());
                        }
                    }
                }
        );
        return classBox;
    }
}
