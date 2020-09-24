package games.coingame;

import games.game.Game;

public class CoinGame {

    public static void main(String[] args) {
//        Game game = new Game(new CoinGameState());
        Game game = new Game(new CoinGameState());
        System.out.println(game);
        game.play();
    }

}
