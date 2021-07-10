using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameHandler : MonoBehaviour
{
    private GameGrid gameGrid;
    public char[,] gameFieldData = new char[,] { { 's', 's', 's', 's', 's', 's', 's', 's', 's', 's' },
                                                 { 's', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 's' },
                                                 { 's', 'f', 'e', 'e', 'f', 'f', 'e', 'e', 'f', 's' },
                                                 { 's', 'f', 'e', 'e', 'f', 'f', 'e', 'e', 'f', 's' },
                                                 { 's', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 's' },
                                                 { 's', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 's' },
                                                 { 's', 'f', 'e', 'e', 'f', 'f', 'e', 'e', 'f', 's' },
                                                 { 's', 'f', 'e', 'e', 'f', 'f', 'e', 'e', 'f', 's' },
                                                 { 's', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 's' },
                                                 { 's', 's', 's', 's', 's', 's', 's', 's', 's', 's' } };

    public static string playerName;

    // Start is called before the first frame update
    void Start()
    {
        gameGrid = GameObject.FindGameObjectWithTag("GameGrid").GetComponent<GameGrid>();
        gameGrid.gameFieldData = gameFieldData;
        gameGrid.tiles = new GameObject[gameFieldData.GetLength(0), gameFieldData.GetLength(1)];

        //ServerConnector.gameHandler = this;
        //ServerConnector.Connect("localhost", new JoinGame("1"));

        gameGrid.GenerateGameGrid();
        gameGrid.PlacePlayer(playerName, new Vector2(1,1), false);
        gameGrid.PlacePlayer("2", new Vector2(8,8), true);
        StartCoroutine(MoveEnemy());
    }

    void Update()
    {
        Direction directionToMove =
            Input.GetKey(KeyCode.W) ? Direction.UP :
            (Input.GetKey(KeyCode.S) ? Direction.DOWN :
            (Input.GetKey(KeyCode.A) ? Direction.LEFT :
            (Input.GetKey(KeyCode.D) ? Direction.RIGHT :
            Direction.DEFAULT)));

        if (directionToMove != Direction.DEFAULT)
        {
            SendMessage(new MovePlayer(playerName, directionToMove));
        }

        if (Input.GetKeyDown(KeyCode.Space))
        {
            SendMessage(new DropBomb(playerName));
        }
    }

    public void PlayerDied()
    {
        StartCoroutine(GameFinished());
    }

    IEnumerator GameFinished()
    {
        var canvas = GameObject.FindGameObjectWithTag("GamePanel");
        var gameOver = (GameObject)Instantiate(Resources.Load("UI/GameOver"));
        gameOver.transform.parent = canvas.transform;
        gameOver.transform.position = new Vector2(Screen.width / 2, Screen.height / 2);
        GameObject.FindGameObjectWithTag("Filter").GetComponent<Image>().color = new Color32(0,0,0,60);
        yield return new WaitForSeconds(5);
        SceneHandler.ShowHighScoreScene();
    }

    IEnumerator MoveEnemy()
    {
        yield return new WaitForSecondsRealtime(2);

        // Mock new Movement here
        gameGrid.players["2"].GetComponent<Player>().MovePlayer(Direction.UP);

        yield return new WaitForSeconds(1);

        // Mock new Movement here
        gameGrid.players["2"].GetComponent<Player>().MovePlayer(Direction.UP);

        yield return new WaitForSeconds(1);

        // Mock new Movement here
        gameGrid.players["2"].GetComponent<Player>().MovePlayer(Direction.UP);

        yield return new WaitForSeconds(1);

        // Mock new Movement here
        gameGrid.players["2"].GetComponent<Player>().MovePlayer(Direction.LEFT);

        yield return new WaitForSeconds(1);

        // Mock new Movement here
        gameGrid.players["2"].GetComponent<Player>().MovePlayer(Direction.LEFT);

        yield return new WaitForSeconds(1);

        // Mock new Movement here
        gameGrid.players["2"].GetComponent<Player>().MovePlayer(Direction.LEFT);
    }

    public void SendMessage(Message message)
    {
        // Send the message right here
        //if (!gameGrid.player.GetComponent<Player>().running)
        //{
        //    ServerConnector.SendMessage(message);
        //}

        if (message.GetType() == typeof(MovePlayer))
        {
            var direction = ((MovePlayer)message).Direction;
            gameGrid.players[playerName].GetComponent<Player>().MovePlayer(direction);
        }
        else if (message.GetType() == typeof(DropBomb))
        {
            var bombPlacer = ((DropBomb)message).PlayerName;
            gameGrid.players[bombPlacer].GetComponent<Player>().PlaceBomb();
        }

    }

    // This method would be used by the server connection
    public void HandleMessage(Message message)
    {
        Debug.Log(message.ToString());
    }
}
