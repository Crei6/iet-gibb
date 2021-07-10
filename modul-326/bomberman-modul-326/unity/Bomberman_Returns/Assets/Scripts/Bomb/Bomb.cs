using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;
using System;

public class Bomb : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(WaitForExplosion());
    }

    IEnumerator WaitForExplosion()
    {
        yield return new WaitForSeconds(2);
        Explode();
    }

    void Explode()
    {
        var solidTiles = GameObject.FindGameObjectsWithTag("SolidTile");
        // Right flame
        if (!Array.Exists(solidTiles, t => (Vector2) t.transform.position == (Vector2) (transform.position + new Vector3(1, 0))))
        {
            GameObject rightFlame = (GameObject)Instantiate(Resources.Load("Bomb/Flame"));
            rightFlame.transform.position = transform.position + new Vector3(1, 0);
        }

        // Left flame
        if (!Array.Exists(solidTiles, t => (Vector2)t.transform.position == (Vector2)(transform.position + new Vector3(-1, 0))))
        {
            GameObject leftFlame = (GameObject)Instantiate(Resources.Load("Bomb/Flame"));
            leftFlame.transform.position = transform.position + new Vector3(-1, 0);
        }

        // Bottom flame
        if (!Array.Exists(solidTiles, t => (Vector2)t.transform.position == (Vector2)(transform.position + new Vector3(0, 1))))
        {
            GameObject bottomFlame = (GameObject)Instantiate(Resources.Load("Bomb/Flame"));
            bottomFlame.transform.position = transform.position + new Vector3(0, 1);
        }

        // Top flame
        if (!Array.Exists(solidTiles, t => (Vector2)t.transform.position == (Vector2)(transform.position + new Vector3(0, -1))))
        {
            GameObject topFlame = (GameObject)Instantiate(Resources.Load("Bomb/Flame"));
            topFlame.transform.position = transform.position + new Vector3(0, -1);
        }

        // Center flame
        GameObject centerFlame = (GameObject)Instantiate(Resources.Load("Bomb/Flame"));
        centerFlame.transform.position = transform.position;

        Destroy(gameObject);
    }
}
