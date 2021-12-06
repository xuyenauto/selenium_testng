package helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static java.util.Map.*;

public class ReadDataFile {

    public String readJsonFile(String filePath, Object type, Object key) {
        String rootPath = System.getProperty("user.dir");
        String value = null;
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {

            Object obj = parser.parse(new FileReader(rootPath + filePath));
            jsonObject = (JSONObject) obj;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        Map abc = ((Map) jsonObject.get(type));
        String temp;

        Iterator<Entry> itr1 = abc.entrySet().iterator();
        if (itr1.hasNext()) {
            do {
                Entry pair = itr1.next();
                temp = pair.getKey().toString();
                if (temp.equals(key)) {
                    value = pair.getValue().toString();
                }
            } while (itr1.hasNext());
        }
        return value;
    }
}
