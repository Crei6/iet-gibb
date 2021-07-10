package ch.gibb.bomberman.application.server.game.labyrinth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class Labyrinth {
    protected static final Type TILE_TYPE = new TypeToken<List<Tile>>(){}.getType();
    private Tile[][] tiles;
    private int size;

    public void read(String filename) {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(filename));
            List<Tile> data = gson.fromJson(reader, TILE_TYPE);
        } catch (FileNotFoundException e) {
            System.err.println("Something went wrong while reading map");
        }
    }

    public char[][] toCharMap() {
        return new char[size][size];
    }
}