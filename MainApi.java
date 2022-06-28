public class MainApi
{
    //https://restcountries.com/v3.1/name/germany
    public static void main(String[] args) throws java.io.IOException {
        System.out.println(API.get("https://restcountries.com/v3.1/name/germany"));
    }
}
