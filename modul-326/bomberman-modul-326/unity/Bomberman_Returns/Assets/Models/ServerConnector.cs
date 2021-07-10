using System.IO;
using System.Net.Sockets;
using UnityEngine;

public class ServerConnector
{
    public static GameHandler gameHandler;
    static TcpClient tcpClient;
    static NetworkStream stream;
    static BinaryWriter binaryWriter;

    public static void Connect(string server, Message message)
    {
        JsonWrapper<Message> jsonWrapper = new JsonWrapper<Message>();
        jsonWrapper.value = message;
        jsonWrapper.key = "JoinGame";
        string jsonMessage = JsonUtility.ToJson(jsonWrapper);

        try
        {
            int port = 7777;
            tcpClient = new TcpClient(server, port);

            stream = tcpClient.GetStream();
            binaryWriter = new BinaryWriter(stream);

            //byte[] data = System.Text.Encoding.UTF8.GetBytes(jsonMessage);

            //stream.Write(data, 0, data.Length);
            //stream.Flush();
            //ReceiveMessages();
        }
        catch (System.Exception)
        {
            throw;
        }
    }

    public static void SendMessage(Message message)
    {
        try
        {
            JsonWrapper<MovePlayer> jsonWrapper = new JsonWrapper<MovePlayer>();

            if (message.GetType() == typeof(MovePlayer))
            {
                jsonWrapper.key = "MovePlayer";
                jsonWrapper.value = (MovePlayer)message;
            }
            else if (message.GetType() == typeof(DropBomb))
            {
                jsonWrapper.key = "DropBomb";
            }

            string jsonMessage = JsonUtility.ToJson(jsonWrapper);
            byte[] data = System.Text.Encoding.UTF8.GetBytes(jsonMessage);
            Debug.Log(jsonMessage);
            binaryWriter.Write(jsonMessage);
            //binaryWriter.Flush();
        }
        catch (System.Exception e)
        {
            Debug.Log(e.StackTrace);
        }
    }

    private static void ReceiveMessages()
    {
        new System.Threading.Thread(() =>
        {
            try
            {
                byte[] data = new byte[256];
                string responseData;
                Message message = new Message();

                while (tcpClient.Connected)
                {
                    int bytes = stream.Read(data, 0, data.Length);
                    responseData = System.Text.Encoding.UTF8.GetString(data, 0, bytes);

                    JsonWrapper<string> firstJsonWrapper = JsonUtility.FromJson<JsonWrapper<string>>(responseData);

                    // Convert value to the associated message type
                    switch (firstJsonWrapper.key)
                    {
                        case "BombDropped":
                            Debug.Log("BombDropped");
                            message = JsonUtility.FromJson<BombDropped>(firstJsonWrapper.value);
                            break;
                        case "BombExploded":
                            Debug.Log("BombExploded");
                            message = JsonUtility.FromJson<BombExploded>(firstJsonWrapper.value);
                            break;
                        case "ErrorMessage":
                            Debug.Log("ErrorMessage");
                            message = JsonUtility.FromJson<ErrorMessage>(firstJsonWrapper.value);
                            break;
                        case "GameOver":
                            Debug.Log("GameOver");
                            message = JsonUtility.FromJson<GameOver>(firstJsonWrapper.value);
                            break;
                        case "PlayerHit":
                            Debug.Log("PlayerHit");
                            message = JsonUtility.FromJson<PlayerHit>(firstJsonWrapper.value);
                            break;
                        case "PlayerJoined":
                            Debug.Log("PlayerJoined");
                            message = JsonUtility.FromJson<PlayerJoined>(firstJsonWrapper.value);
                            break;
                        case "PlayerMoved":
                            Debug.Log("PlayerMoved");
                            message = JsonUtility.FromJson<PlayerMoved>(firstJsonWrapper.value);
                            break;
                        case "StartGame":
                            Debug.Log("StartGame");
                            message = JsonUtility.FromJson<StartGame>(firstJsonWrapper.value);
                            break;
                        case "Update":
                            Debug.Log("Update");
                            message = JsonUtility.FromJson<Update>(firstJsonWrapper.value);
                            break;
                    }

                    gameHandler.HandleMessage(message);
                }
                stream.Close();
                tcpClient.Close();
            }
            catch (System.Exception e)
            {
                Debug.Log(e.StackTrace);
            }
        }).Start();
    }

}
