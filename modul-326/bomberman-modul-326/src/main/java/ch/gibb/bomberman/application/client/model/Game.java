package ch.gibb.bomberman.application.client.model;

import ch.gibb.bomberman.protocol.server2client.PlayerJoined;

import java.util.List;
import java.util.ArrayList;

public class Game
{
    private Player myPlayer;
    private List<Player> opponents = new ArrayList<Player>();


    public void createMyPlayer(String playerName)
    {
        myPlayer = new Player(playerName);
    }

    public void playerJoined(PlayerJoined message)
    {
        String playerName = message.getPlayerName();
        int initialY = message.getInitialPositionX();
        int initialX = message.getInitialPositionX();
        if (myPlayer.isYourName(playerName)) {
            myPlayer.setPosition(initialX, initialY);
        } else {
            Player opponent = new Player(playerName, initialX, initialY);
            opponents.add(opponent);
        }
    }
}
