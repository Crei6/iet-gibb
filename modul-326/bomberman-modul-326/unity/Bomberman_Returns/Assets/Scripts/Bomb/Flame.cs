using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Flame : MonoBehaviour
{

    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(WaitForFlame());
    }

    IEnumerator WaitForFlame()
    {
        yield return new WaitForSeconds(1);
        
        Destroy(gameObject);
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Enemy" || collision.gameObject.tag == "Player")
        {
            Destroy(collision.gameObject);
            GameObject.FindGameObjectWithTag("GameController").GetComponent<GameHandler>().PlayerDied();
        }
        else if (collision.gameObject.tag == "ExplodableTile")
        {
            GameObject newTile = (GameObject)Instantiate(Resources.Load("StandardTile"));
            newTile.transform.position = collision.transform.position;
            Destroy(collision.gameObject);
        }
    }
}
