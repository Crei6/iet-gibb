package ch.gibb.bomberman.application.server.controller;

import ch.gibb.bomberman.protocol.client2server.ClientMessage;

public class ControllerDispatcher {

    private final ControllerFactory factory;

    public ControllerDispatcher(ControllerFactory factory) {
        this.factory = factory;
    }

    public Controller getController(ClientMessage message) throws ControllerNotExistsException {
        return switch (message.getClass().getSimpleName()) {
            case "JoinGame" -> factory.createJoinGameController(message.getPlayerName());
            case "MovePlayer" -> factory.createMovePlayerController(message.getPlayerName());
            case "DropBomb" -> factory.createDropBombController(message.getPlayerName());
            default -> throw new ControllerNotExistsException("Controller " + message.getClass().getSimpleName() + " does not exist!");
        };
    }
}