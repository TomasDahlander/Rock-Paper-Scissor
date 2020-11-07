package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.Random;

public class Controller {

// ROCK = 1 --> beats SCISSOR
// SCISSOR = 2 --> beats PAPER
// PAPER = 3 --> beats ROCK

    public Button rock;
    public Button paper;
    public Button scissor;

    public Label outcome;
    public Label playerScore;
    public Label npcScore;

    public ImageView imagePlayer;
    public ImageView imageNPC;

    private void setPlayerImage(String name){
        imagePlayer.setImage(new Image(name));
    }

    private void setNpcImage(String name){
        imageNPC.setImage(new Image(name));
    }


    private void check(int player) throws InterruptedException {
        int npc = getNpcChoice();
        String npcChoice;
        if(npc == 1) npcChoice = "rock.JPG";
        else if(npc == 2) npcChoice = "scissor.JPG";
        else npcChoice = "paper.JPG";

        drumRoll(npcChoice);

        String outCome = declareWinner(player,npc);
        outcome.setText(outCome);
        tallyScore(outCome);
    }

    public int getNpcChoice(){
        Random rand = new Random();
        int x;
        x = rand.nextInt(3)+1;
        return x;
    }

    private void drumRoll(String npcChoice){
        new Thread(){
            public void run() {
                for (int i = 0; i < 6; i++) {
                    try {
                        setNpcImage("rock.JPG");
                        Thread.sleep(75);
                        setNpcImage("paper.JPG");
                        Thread.sleep(75);
                        setNpcImage("scissor.JPG");
                        Thread.sleep(75);
                    } catch (InterruptedException e) {
                        System.out.println("Det gick ej att göra en drumroll.");
                        e.printStackTrace();
                    }
                }
                setNpcImage(npcChoice);
                
            }
        }.start();
    }

    public String declareWinner(int player, int npc){
        if(player == npc) return "It's a draw!";
        else if(player == 1 && npc == 2) return "Player Wins!";
        else if(player == 2 && npc == 3) return "Player Wins!";
        else if(player == 3 && npc == 1) return "Player Wins!";
        else return "NPC Wins!";
    }

    private void tallyScore(String outcome){
        if(outcome.equalsIgnoreCase("Player Wins!")){
            int x = Integer.valueOf(playerScore.getText());
            x++;
            playerScore.setText(String.valueOf(x));
        }
        else if(outcome.equalsIgnoreCase("NPC Wins!")){
            int x = Integer.valueOf(npcScore.getText());
            x++;
            npcScore.setText(String.valueOf(x));
        }
    }

    public void click(ActionEvent e) throws InterruptedException {
        if(e.getSource() == rock){
            setPlayerImage("rock.JPG");
            check(1);
        }
        else if(e.getSource() == paper){
            setPlayerImage("paper.JPG");
            check(3);
        }
        else if(e.getSource() == scissor){
            setPlayerImage("scissor.JPG");
            check(2);
        }
    }
}
