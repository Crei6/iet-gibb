package ch.gibb.bomberman.protocol.parser;

import com.google.gson.Gson;
import org.json.JSONObject;

/**
 * Helper Class providing you with tools to convert JSONs to Java objects and vice versa
 *
 * @date 10.06.2021
 * @author justin, timo
 */
public class MessageParser {

    // classpath for java message class
    private final String CLASSPATH =  "ch.gibb.bomberman.protocol.client2server.";

    /**
     * Parses Java Object to Json string
     * @param object message object to convert.
     * @return converted object.
     * @author justin
     */
    public String javaToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * Parses Json to Java object
     * @param json string to be converted.
     * @throws ClassNotFoundException when key is invalid.
     * @return converted json.
     * @author justin
     */
    public Object jsonToJava(JSONObject json) throws ClassNotFoundException {
        Gson g = new Gson();

        // build the java object using gson
        Object obj = g.fromJson(
                json.getString("value"),
                Class.forName(CLASSPATH + json.getString("key"))
        );

        return obj;
    }

    /**
     * Initializes a JSONObject, with the required key and value attributes, afrom a given Java object
     * @param object to be converted.
     * @return initialized JSONObject.
     * @author timo
     */
    public JSONObject createJsonObj(Object object) {
        // convert object to string
        String jsonString = javaToJson(object);
        // new json object
        JSONObject jsonObject = new JSONObject();
        // add essential values to json
        jsonObject.put("key", object.getClass().getSimpleName());
        jsonObject.put("value", jsonString);

        return jsonObject;
    }

    public String byteArrayToString(byte[] bytes) {
        return new String(bytes);
    }
}
