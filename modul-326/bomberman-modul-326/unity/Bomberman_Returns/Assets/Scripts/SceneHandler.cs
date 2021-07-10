using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public static class SceneHandler
{
    public static void ShowMainMenuScene()
    {
        SceneManager.LoadScene(0);
    }

    public static void ShowMainGameScene(string playerName)
    {
        GameHandler.playerName = playerName;
        SceneManager.LoadScene(1);
    }

    public static void ShowHighScoreScene()
    {
        SceneManager.LoadScene(2);
    }
}