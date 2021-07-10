package ch.gibb.bomberman.application.server.game.player;

import java.util.ArrayList;

public class PlayerHandler {
    private final ArrayList<Player> players;

    public PlayerHandler() {
        this.players = new ArrayList<>();
    }

    public void createPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    public int getPlayerCount() {
        return players.size();
    }

    public Player getPlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public boolean hasPlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return true;
            }
        }
        return false;
    }
}