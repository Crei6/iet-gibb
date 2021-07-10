package ch.gibb.bomberman.application.server.controller;

public class ControllerFactory {
    JoinGameController createJoinGameController(String playerName) {
        return new JoinGameController(playerName);
    }

    MovePlayerController createMovePlayerController(String playerName) {
        return new MovePlayerController(playerName);
    }

    DropBombController createDropBombController(String playerName) {
        return new DropBombController(playerName);
    }
}