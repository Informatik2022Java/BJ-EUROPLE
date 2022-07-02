import org.json.JSONArray;
import org.json.JSONObject;

public class Json
{
    public static Vector2 getLatLngCapital(String json){
        JSONArray latLng = ((JSONObject)new JSONArray(json).getJSONObject(0).get("capitalInfo")).getJSONArray("latlng");
        return new Vector2(Float.parseFloat(latLng.get(0).toString()), Float.parseFloat(latLng.get(1).toString()));
    }

    public static String[] getBorders(String json){
        try {
            JSONArray borders = ((JSONObject) new JSONArray(json).getJSONObject(0)).getJSONArray("borders");
            String[] res = new String[borders.length()];
            for (int i = 0; i < borders.length(); i++) {
                res[i] = borders.get(i).toString();
            }
            return res;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new String[0];
    }

    public static String getCca3(String json){
        return new JSONArray(json).getJSONObject(0).getString("cca3");
    }

    public static String getWikiSearch(String json){
        int extract = json.indexOf("extract");
        String result = json.substring(extract);
        return result;
    }
}