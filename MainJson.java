import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class MainJson
{
    public static void main(String args[])
    {
        //String jsonString = "[{\"name\":{\"common\":\"Germany\",\"official\":\"Federal Republic of Germany\",\"nativeName\":{\"deu\":{\"official\":\"Bundesrepublik Deutschland\",\"common\":\"Deutschland\"}}},\"tld\":[\".de\"],\"cca2\":\"DE\",\"ccn3\":\"276\",\"cca3\":\"DEU\",\"cioc\":\"GER\",\"independent\":true,\"status\":\"officially-assigned\",\"unMember\":true,\"currencies\":{\"EUR\":{\"name\":\"Euro\",\"symbol\":\"€\"}},\"idd\":{\"root\":\"+4\",\"suffixes\":[\"9\"]},\"capital\":[\"Berlin\"],\"altSpellings\":[\"DE\",\"Federal Republic of Germany\",\"Bundesrepublik Deutschland\"],\"region\":\"Europe\",\"subregion\":\"Western Europe\",\"languages\":{\"deu\":\"German\"},\"translations\":{\"ara\":{\"official\":\"جمهورية ألمانيا الاتحادية\",\"common\":\"ألمانيا\"},\"ces\":{\"official\":\"Spolková republika Německo\",\"common\":\"Německo\"},\"cym\":{\"official\":\"Federal Republic of Germany\",\"common\":\"Germany\"},\"deu\":{\"official\":\"Bundesrepublik Deutschland\",\"common\":\"Deutschland\"},\"est\":{\"official\":\"Saksamaa Liitvabariik\",\"common\":\"Saksamaa\"},\"fin\":{\"official\":\"Saksan liittotasavalta\",\"common\":\"Saksa\"},\"fra\":{\"official\":\"République fédérale d'Allemagne\",\"common\":\"Allemagne\"},\"hrv\":{\"official\":\"Njemačka Federativna Republika\",\"common\":\"Njemačka\"},\"hun\":{\"official\":\"Német Szövetségi Köztársaság\",\"common\":\"Németország\"},\"ita\":{\"official\":\"Repubblica federale di Germania\",\"common\":\"Germania\"},\"jpn\":{\"official\":\"ドイツ連邦共和国\",\"common\":\"ドイツ\"},\"kor\":{\"official\":\"독일 연방 공화국\",\"common\":\"독일\"},\"nld\":{\"official\":\"Bondsrepubliek Duitsland\",\"common\":\"Duitsland\"},\"per\":{\"official\":\"جمهوری فدرال آلمان\",\"common\":\"آلمان\"},\"pol\":{\"official\":\"Republika Federalna Niemiec\",\"common\":\"Niemcy\"},\"por\":{\"official\":\"República Federal da Alemanha\",\"common\":\"Alemanha\"},\"rus\":{\"official\":\"Федеративная Республика Германия\",\"common\":\"Германия\"},\"slk\":{\"official\":\"Nemecká spolková republika\",\"common\":\"Nemecko\"},\"spa\":{\"official\":\"República Federal de Alemania\",\"common\":\"Alemania\"},\"swe\":{\"official\":\"Förbundsrepubliken Tyskland\",\"common\":\"Tyskland\"},\"urd\":{\"official\":\"وفاقی جمہوریہ جرمنی\",\"common\":\"جرمنی\"},\"zho\":{\"official\":\"德意志联邦共和国\",\"common\":\"德国\"}},\"latlng\":[51.0,9.0],\"landlocked\":false,\"borders\":[\"AUT\",\"BEL\",\"CZE\",\"DNK\",\"FRA\",\"LUX\",\"NLD\",\"POL\",\"CHE\"],\"area\":357114.0,\"demonyms\":{\"eng\":{\"f\":\"German\",\"m\":\"German\"},\"fra\":{\"f\":\"Allemande\",\"m\":\"Allemand\"}},\"flag\":\"\\uD83C\\uDDE9\\uD83C\\uDDEA\",\"maps\":{\"googleMaps\":\"https://goo.gl/maps/mD9FBMq1nvXUBrkv6\",\"openStreetMaps\":\"https://www.openstreetmap.org/relation/51477\"},\"population\":83240525,\"gini\":{\"2016\":31.9},\"fifa\":\"GER\",\"car\":{\"signs\":[\"DY\"],\"side\":\"right\"},\"timezones\":[\"UTC+01:00\"],\"continents\":[\"Europe\"],\"flags\":{\"png\":\"https://flagcdn.com/w320/de.png\",\"svg\":\"https://flagcdn.com/de.svg\"},\"coatOfArms\":{\"png\":\"https://mainfacts.com/media/images/coats_of_arms/de.png\",\"svg\":\"https://mainfacts.com/media/images/coats_of_arms/de.svg\"},\"startOfWeek\":\"monday\",\"capitalInfo\":{\"latlng\":[52.52,13.4]},\"postalCode\":{\"format\":\"#####\",\"regex\":\"^(\\\\d{5})$\"}}]";
        //jsonString = "[{ \"name\": \"1\", \"arr\":[ {\"val\": \"11\"},{ \"val2\": \"12\"}] }, { \"name2\": \"2\" }]";
        //System.out.println(Json.getObject(jsonString, "capitalInfo"));
        //System.out.println(Json.getArray(jsonString, "val2"));
        //System.out.println(Json.get(jsonString, "latlng"));


        String jsonString = null;
        try {
            jsonString = API.get("https://restcountries.com/v3.1/name/germany");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Vector2 res = Json.getLatLngCapital(jsonString);
        System.out.println(res.x + " " + res.y);

        String[] borders = Json.getBorders(jsonString);
        for (String border: borders) {
            System.out.println(border);
        }

        System.out.println(Json.getCca3(jsonString));
    }
}
