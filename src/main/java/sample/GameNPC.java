package sample;

import java.util.*;

public class GameNPC {

// ROCK = 1 --> beats SCISSOR
// SCISSOR = 2 --> beats PAPER
// PAPER = 3 --> beats ROCK

    public static int getNpcChoice(){
        Random rand = new Random();
        int x;
        x = rand.nextInt(3)+1;
        return x;
    }

    public static String declareWinner(int player, int npc){
        if(player == npc) return "It's a draw!";
        else if(player == 1 && npc == 2) return "Player Wins!";
        else if(player == 2 && npc == 3) return "Player Wins!";
        else if(player == 3 && npc == 1) return "Player Wins!";
        else return "NPC Wins!";
    }

}
