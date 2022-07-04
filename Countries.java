
public class Countries extends Graph
{
    
    public Countries()
    {
        super(10);
    }
    
    public Countries(Country[] countries, int[][] edges){
        super(countries, edges);
    }
    
    public static int dist2coords(Vector2 coordsA, Vector2 coordsB){
        double Lat1 = coordsA.x;
        double Lon1 = coordsA.y;
        double Lat2 = coordsB.x;
        double Lon2 = coordsB.y;
        //double dist =  Math.acos(Math.cos(Math.toRadians(90-Lat1)) * Math.cos(Math.toRadians(90-Lat2)) + Math.sin(Math.toRadians(90-Lat1)) *  Math.sin(Math.toRadians(90-Lat2)) * Math.cos(Math.toRadians(Lon1-Lon2))) * 3959;
        //return (int) dist;
        
        return (int)(meters(Lat1, Lon1, Lat2, Lon2) / 1000);
    }
    
    private static final double r2d = 180.0D / 3.141592653589793D;
    private static final double d2r = 3.141592653589793D / 180.0D;
    private static final double d2km = 111189.57696D * r2d;
    public static double meters(double lt1, double ln1, double lt2, double ln2) {
        double x = lt1 * d2r;
        double y = lt2 * d2r;
        return Math.acos( Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos(d2r * (ln1 - ln2))) * d2km;
    }
    
    public static Country[] getCountriesFromData(String[][] data){
        Country[] array = new Country[data.length];
        for(int i = 0; i < data.length; i++){
            array[i] = new Country(data[i][0].toUpperCase(), data[i][1]);
        }
        return array;
    }
    
    public static int[][] getEdgesFromData(String[][] data){
        int[][] array = new int[data.length][data.length];
        for(int i = 0; i < data.length; i++){
            String neightbours[] = data[i][4].split(",");
            String country = data[i][1];
            for(int j = 0; j < data.length; j++){
                for(int k = 0; k < neightbours.length; k++){
                    if(neightbours[k].equals(data[j][1])){
                        Vector2 coordsA = new Vector2(Float.parseFloat(data[i][2]), Float.parseFloat(data[i][3]));
                        Vector2 coordsB = new Vector2(Float.parseFloat(data[j][2]), Float.parseFloat(data[j][3]));                        
                        array[i][j] = dist2coords(coordsA, coordsB);
                    }
                }
            }
        }
        return array;
    }
}
