package ch.gibb.bomberman.application.client.control;

import ch.gibb.bomberman.application.client.model.Game;
import ch.gibb.bomberman.application.client.view.BombermanFrame;
import ch.gibb.bomberman.application.client.view.BombermanPanel;
import ch.gibb.bomberman.network.client.ServerProxy;
import ch.gibb.bomberman.network.client.ServerProxyStub;

public class BombermanClient {

    public static void main(String[] args) {
        new BombermanClient();
    }

    private BombermanClient(){
        Game game = new Game();
        Dispatcher dispatcher = new Dispatcher();
        ServerProxy serverProxy = new ServerProxyStub(dispatcher);
        BombermanPanel view = new BombermanPanel();
        ControlFactory.instantiate(serverProxy, game, view);
        new BombermanFrame(view);
    }
}
