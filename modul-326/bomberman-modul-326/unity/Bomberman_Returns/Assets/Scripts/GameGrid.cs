using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameGrid : MonoBehaviour
{
    public char[,] gameFieldData;
    public float tileSize;
    public GameObject[,] tiles;
    public IDictionary<string, GameObject> players = new Dictionary<string, GameObject>();

    public void GenerateGameGrid()
    {
        GameObject referenceStandardTile = (GameObject) Instantiate(Resources.Load("StandardTile"));
        GameObject referenceSolidTile = (GameObject)Instantiate(Resources.Load("SolidTile"));
        GameObject referenceExplodableTile = (GameObject)Instantiate(Resources.Load("ExplodableTile"));

        for (int x = 0; x < gameFieldData.GetLength(1); x++)
        {
            for (int y = 0; y < gameFieldData.GetLength(0); y++)
            {
                GameObject tile = null;
                switch (gameFieldData[y,x])
                {
                    case 'f': 
                        tile = Instantiate(referenceStandardTile, transform);
                        break;
                    case 's':
                        tile = Instantiate(referenceSolidTile, transform);
                        break;
                    case 'e':
                        tile = Instantiate(referenceExplodableTile, transform);
                        break;

                    default:
                        break;
                }
                float posX = x * tileSize;
                float posY = y * -tileSize;
                tile.transform.position = new Vector2(posX, posY);
                tiles[x, y] = tile;
            }
        }
        Destroy(referenceStandardTile);
        Destroy(referenceSolidTile);
        Destroy(referenceExplodableTile);

        float gridWidth = gameFieldData.GetLength(0) * tileSize;
        float gridHeight = gameFieldData.GetLength(1) * tileSize;
        GameObject mainCameraGameObject = GameObject.FindGameObjectWithTag("MainCamera");
        // center the grid in the camera. need to add half the size of the tilesize
        transform.position = new Vector2(mainCameraGameObject.transform.position.x / 2 - gridWidth / 2 + tileSize / 2, 
                                         -(mainCameraGameObject.transform.position.y / 2 - gridHeight / 2 + tileSize / 2));
    }

    // This method places the user at the begining of the game
    public void PlacePlayer(string name, Vector2 pos, bool isEnemy)
    {
        GameObject player = (GameObject)Instantiate(Resources.Load(isEnemy ? "Enemy" : "Player"));
        player.transform.position = tiles[(int)pos.x,(int)pos.y].transform.position + new Vector3(0, tileSize / 2);
        players.Add(name, player);
    }
}
