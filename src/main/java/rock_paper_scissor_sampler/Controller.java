package rock_paper_scissor_sampler;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        if(npc == 1) npcChoice = "Pictures/rock.JPG";
        else if(npc == 2) npcChoice = "Pictures/scissor.JPG";
        else npcChoice = "Pictures/paper.JPG";
        drumRoll(npcChoice);

        Task sleeper = new Task() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1450);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                String outCome = declareWinner(player,npc);
                outcome.setText(outCome);
                tallyScore(outCome);
                setButtonStatus(false);
            }
        });
        new Thread(sleeper).start();
    }

    public int getNpcChoice(){
        Random rand = new Random();
        return rand.nextInt(3)+1;
    }

    private void drumRoll(String npcChoice){
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    setNpcImage("Pictures/rock.JPG");
                    Thread.sleep(75);
                    setNpcImage("Pictures/paper.JPG");
                    Thread.sleep(75);
                    setNpcImage("Pictures/scissor.JPG");
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    System.out.println("Det gick ej att göra en drumroll.");
                    e.printStackTrace();
                }
            }
            setNpcImage(npcChoice);
        }).start();
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

    private void setButtonStatus(boolean status){
        rock.setDisable(status);
        paper.setDisable(status);
        scissor.setDisable(status);
    }

    public void click(ActionEvent e) throws InterruptedException {
        outcome.setText("");
        if(e.getSource() == rock){
            setButtonStatus(true);
            setPlayerImage("Pictures/rock.JPG");
            check(1);
        }
        else if(e.getSource() == paper){
            setButtonStatus(true);
            setPlayerImage("Pictures/paper.JPG");
            check(3);
        }
        else if(e.getSource() == scissor){
            setButtonStatus(true);
            setPlayerImage("Pictures/scissor.JPG");
            check(2);
        }
    }
}
