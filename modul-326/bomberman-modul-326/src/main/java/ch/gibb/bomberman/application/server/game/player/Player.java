package ch.gibb.bomberman.application.server.game.player;

public class Player {
    private String name;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}