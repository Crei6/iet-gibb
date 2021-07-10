using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class Highscore : MonoBehaviour
{
    void Start()
    {
        GameObject image = GameObject.FindGameObjectWithTag("ButtonGroup");

        GameObject refButton = (GameObject)Instantiate(Resources.Load("UI/Button"));


        string[] mockHighscores = new[] { "Daniel    50p", "Karsten Stahl    40p", "Hubert Langschwanz    20p", "Mister T    10p", "Mike Oxlong    5p" };


        foreach (var highscoreItem in mockHighscores)
        {
            GameObject firstButton = Instantiate(refButton, transform);

            firstButton.GetComponentInChildren<Text>().text = highscoreItem;

            firstButton.transform.SetParent(image.transform);
        }


    }

    public void ChangeToMainMenuScene()
    {
        SceneHandler.ShowMainMenuScene();
    }

    public void QuitGame()
    {
        Application.Quit();
    }
}