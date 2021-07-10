using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{

    public float speed;

    public Animator animator;

    public bool isPlayerRunning;

    void Start()
    {
        // Letting animator know, to look down
        animator.SetFloat("Vertical", -0.01f);
    }

    public void MovePlayer(Direction direction)
    {
        Vector2 newPos = new Vector2(0, 0);
        switch (direction)
        {
            case Direction.UP:
                newPos = transform.position + new Vector3(0, 1);
                break;
            case Direction.DOWN:
                newPos = transform.position + new Vector3(0, -1);
                break;
            case Direction.LEFT:
                newPos = transform.position + new Vector3(-1, 0);
                break;
            case Direction.RIGHT:
                newPos = transform.position + new Vector3(1, -0);
                break;
        }
        if (!isPlayerRunning)
        {
            StartCoroutine(Move(newPos));
        }
    }

    IEnumerator Move(Vector3 movement)
    {
        isPlayerRunning = true;
        while (transform.position != movement)
        {
            var newPoint = Vector2.MoveTowards(transform.position, movement, speed * Time.deltaTime);
            animator.SetFloat("Horizontal", movement.x - transform.position.x);
            animator.SetFloat("Vertical", movement.y - transform.position.y);
            animator.SetFloat("Speed", new Vector2(movement.x - transform.position.x, movement.y - transform.position.y).sqrMagnitude);
            transform.position = newPoint;
            yield return new WaitForFixedUpdate();
        }
        isPlayerRunning = false;
    }

    public void PlaceBomb()
    {
        var bombs = GameObject.FindGameObjectsWithTag("Bomb");
        bool isBombOnPosition = false;
        foreach (var bomb in bombs)
        {
            if (bomb.transform.position == transform.position - new Vector3(0, 0.5f))
            {
                isBombOnPosition = true;
                break;
            }
        }
        if (!isPlayerRunning && !isBombOnPosition)
        {
            var bomb = (GameObject)Instantiate(Resources.Load("Bomb/Bomb"));
            bomb.transform.position = transform.position - new Vector3(0, 0.5f);
        }
    }
}
