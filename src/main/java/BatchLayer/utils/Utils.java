package BatchLayer.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Utils {

    public static Date convertDate(String dateValue) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("M/d/yy HH:mm");
        return df.parse(dateValue);
    }

    public static String getLocation(String longitude, String latitude) {
        String res = "";
        HttpURLConnection con = null;
        try {
            String urlString = "https://api.opencagedata.com/geocode/v1/json?q=" + longitude + "," + latitude + "&key=a19ff12f9c5f488fa97bb5d76087977f";
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Integer responsecode = con.getResponseCode();
            if(responsecode != 200){
                res = "Invalid long lat";
            }
            else
            {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                JsonObject objet = new JsonParser().parse(content.toString()).getAsJsonObject();
                if (!objet.get("total_results").toString().equals("0")){
                    JsonArray jsonArray = objet.get("results").getAsJsonArray();
                    res = jsonArray.get(0).getAsJsonObject().get("formatted").toString();
                }
                else {
                    res = "No result";
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            con.disconnect();
        }
        return res;
    }
}
