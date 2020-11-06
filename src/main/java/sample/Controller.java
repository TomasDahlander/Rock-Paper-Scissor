package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class Controller {

// ROCK = 1 --> beats SCISSOR
// SCISSOR = 2 --> beats PAPER
// PAPER = 3 --> beats ROCK

    public Button rock;
    public Button paper;
    public Button scissor;

    public Label outcome;

    public ImageView imagePlayer;
    public ImageView imageNPC;

    public void setPlayerImage(String name){
        imagePlayer.setImage(new Image(name));
    }

    public void setNpcImage(String name){
        imageNPC.setImage(new Image(name));
    }

    public void check(int player){
        int npc = GameNPC.getNpcChoice();

        String npcChoice;
        if(npc == 1) npcChoice = "rock.JPG";
        else if(npc == 2) npcChoice = "scissor.JPG";
        else npcChoice = "paper.JPG";
        setNpcImage(npcChoice);
        String outCome = GameNPC.declareWinner(player,npc);
        outcome.setText(outCome);
    }

    public void click(ActionEvent e) throws IOException {
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
