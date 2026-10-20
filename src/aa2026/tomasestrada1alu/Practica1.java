package aa2026.tomasestrada1alu;

import tracks.ArcadeMachine;

import java.util.Random;

import static tools.Utils.readGames;

public class Practica1 {
    public static void main(String[] args) {

        String p0 = "aa2026.tomasestrada1alu.Agente";

        String spGamesCollection = "examples/all_games_sp.csv";
        String[][] games = readGames(spGamesCollection);

        boolean visuals = true;
        int seed = new Random().nextInt();

        int gameIdx = 117;
        int levelIdx = 2;

        String gameName = games[gameIdx][1];
        String game = games[gameIdx][0];
        String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);

         ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);
         //ArcadeMachine.playOneGame(game, level1, null, seed);

        System.exit(0);
    }

}
