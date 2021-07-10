using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class MainMenu : MonoBehaviour
{

    public Text inputText;
    public Text errorLabel;

    public void PlayGame()
    {
        if (validateInput(inputText.text))
        {
            SceneHandler.ShowMainGameScene(inputText.text);
        }
        else
        {
            errorLabel.text = "Playername invalid";
        }

    }

    private bool validateInput(string inputText)
    {
        if (inputText == "")
        {
            return false;
        }
        else
        {
            return true;
        }

    }
}
