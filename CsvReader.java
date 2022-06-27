import java.io.*;  
import java.util.Scanner;  
import java.io.BufferedReader; 

public class CsvReader
{
    public static String[][] readFile(String file, Vector2 start, Vector2 end){
        if(start.x > end.x || start.y > end.y){
            System.out.println("start > end");
            return null;
        }
        String[][] matrix = new String[(int)(end.y - start.y) + 1][(int)(end.x - start.x) + 1];
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            for(int i = 0; i < start.y; i++){
                br.readLine();
            }
            String line;
            for(int i = 0; i < end.y - start.y + 1; i++){
                line = br.readLine();
                String[] cells = line.split(";");
                for(int j = 0; j < end.x - start.x + 1; j++){
                    //System.out.println(cells[j] + ": "+ j + "/" + cells.length + " " + i + "/" + matrix.length);
                    matrix[i][j] = cells[j];
                }
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        
        return matrix;
    }
}