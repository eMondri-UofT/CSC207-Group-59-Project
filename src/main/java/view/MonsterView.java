package view;

import interface_adapter.monster.FightMonsterController;
import interface_adapter.monster.FightMonsterState;
import interface_adapter.monster.FightMonsterViewModel;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
The View for when the player is in a Fight with a Monster
 */
public class MonsterView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "monster";
    private final FightMonsterViewModel fightMonsterViewModel;
    private final FightMonsterState fightMonsterState = new FightMonsterState();

    private final JButton fightButton;
    private final JButton nextButton;

    private FightMonsterController fightMonsterController;

    public MonsterView(FightMonsterViewModel fightMonsterViewModel) {
        final JLabel monsterDescriptionLabel = new JLabel("You encountered a " + fightMonsterState.name + "!");
        monsterDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel monsterHealth = new JLabel("Health: " + fightMonsterState.health);
        monsterHealth.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel playerHealth = new JLabel("");
        playerHealth.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel damageDone = new JLabel("");
        damageDone.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel monsterDamage = new JLabel("");
        monsterDamage.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel victoryLabel = new JLabel("");
        victoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel victoryResources = new JLabel("");
        victoryResources.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel monsterVictory = new JLabel("");
        monsterVictory.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.fightMonsterViewModel = fightMonsterViewModel;
        this.fightMonsterViewModel.addPropertyChangeListener(this);
        Player player = Player.getInstance();
        int initHealth = fightMonsterState.health;
        final int[] pHealth = {player.getHealth()};

        fightButton = new JButton("Fight");
        nextButton = new JButton("Next");

        final JPanel buttons = new JPanel();
        buttons.add(nextButton);
        buttons.add(fightButton);

        fightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(fightButton)) {
                    nextButton.setVisible(false);
                    int damage = fightMonsterState.hit(player.getTotalDamage());
                    int attack = fightMonsterState.damage(player.getTotalArmor());
                    pHealth[0] -= attack;
                    player.setHealth(pHealth[0]);
                    monsterHealth.setText("Health: " + fightMonsterState.health);
                    playerHealth.setText("Remaining Health: " + player.getHealth());
                    damageDone.setText("Did " + damage + " damage!");
                    monsterDamage.setText(fightMonsterState.name + " did " + attack + " damage!");

                    if (fightMonsterState.health <= 0) {
                        nextButton.setVisible(true);
                        fightButton.setVisible(false);
                        monsterDescriptionLabel.setText("");
                        monsterHealth.setText("");
                        playerHealth.setText("");
                        damageDone.setText("");
                        monsterDamage.setText("");
                        victoryLabel.setText("Congratulations! You defeated the " + fightMonsterState.name + "!");
                        victoryResources.setText("You gain " + (initHealth/10 + 1) + " gold");
                    }

                    else if (player.getHealth() <= 0) {
                        nextButton.setVisible(true);
                        fightButton.setVisible(false);
                        monsterDescriptionLabel.setText("");
                        playerHealth.setText("");
                        monsterHealth.setText("");
                        damageDone.setText("");
                        monsterDamage.setText("");
                        monsterVictory.setText("You lost.");
                    }

                    fightMonsterController.hit();
                }
            }

        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(nextButton)) {
                    final FightMonsterState currentState = fightMonsterViewModel.getState();

                    fightMonsterController.execute();
                }
            }

        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(monsterDescriptionLabel);
        this.add(monsterHealth);
        this.add(playerHealth);
        this.add(damageDone);
        this.add(monsterDamage);
        this.add(victoryLabel);
        this.add(victoryResources);
        this.add(monsterVictory);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void propertyChange(PropertyChangeEvent evt) {
        final FightMonsterState state = (FightMonsterState) evt.getNewValue();
    }

    public String getViewName() {
        return viewName;
    }

    public void setFightMonsterController(FightMonsterController controller) {
        this.fightMonsterController = controller;
    }
}