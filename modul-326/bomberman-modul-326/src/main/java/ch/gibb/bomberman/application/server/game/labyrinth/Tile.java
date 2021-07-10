package ch.gibb.bomberman.application.server.game.labyrinth;

import ch.gibb.bomberman.application.server.game.Bomb;

public class Tile {
    private boolean destructible;
    private Bomb bomb;

    Tile(boolean destructible) {
        this.destructible = destructible;
    }

    public void createBomb() {
        bomb = new Bomb();
    }
}