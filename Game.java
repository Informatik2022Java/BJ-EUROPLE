import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Game
{
    private final Countries countries;
    private LinkedList guesses;
    private Country solution;
    private int turn, round, numberOfPlayers;
    private LinkedList playerIn;
    private boolean right;

    public Game() throws java.io.IOException
    {
        //read all country-names from csv file
        String[][] data = CsvReader.readFile("data.csv", new Vector2(0,0), new Vector2(0,35));
        countries = new Countries(36);

        //start Ui & Music
        Ui.start();
        Ui.loading();
        Music.playMusic("music/gamemusic.wav", true);

        //get data from API using the country-names
        for (String[] line : data) {
            String name = line[0];
            String json = null;
            try {
                json = API.get("https://restcountries.com/v3.1/name/" + name.replace(" ", "%20"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(json);

            //extract actual data from json
            String id = Json.getCca3(json);
            Vector2 pos = Json.getLatLngCapital(json);
            String[] borders = Json.getBorders(json);

            countries.add(new Country(name.toUpperCase(), id, pos, new LinkedList(borders)));
        }
        //System.out.println("loaded all Countries from API");

        for(int i = 0; i < data.length; i++){
            Country curCountry = (Country)countries.get(i);
            LinkedList borders = curCountry.getBorders();
            for(int country = 0; country < countries.size(); country++){
                String cca3 = countries.get(country).getId();
                //System.out.println("searching: "+ cca3 + " in: " + curCountry.getName());
                Object cca = cca3;
                //if country borders other country, find distance
                if (borders.contains(cca)){
                    //System.out.println("found it");
                    Vector2 pos = ((Country) countries.get(country)).getPos();
                    countries.setEdge(country, i, Countries.dist2coords(curCountry.getPos(), pos));
                    countries.setEdge(i, country, Countries.dist2coords(curCountry.getPos(), pos));
                }
            }
        }
        newGame();
    }

    public void reset(){
        right = false;
        guesses = new LinkedList();
        int rdmIdx = (int)(Math.random() * countries.size());
        solution = (Country)countries.get(rdmIdx);
    }

    //starting a new game -> getting the number of players
    public void newGame() throws java.io.IOException {
        Ui.clear();

        JComponent[] components = Ui.playerAmountSelect();
        JButton btn = (JButton)components[0];
        JTextField input = (JTextField)components[1];

        btn.addActionListener(e -> {
                String text = input.getText();
                System.out.println("number of players: " + text);
                numberOfPlayers = 1;

                try{
                    numberOfPlayers = Integer.parseInt(text);
                }
                catch(NumberFormatException ignored){
                }

                turn = 0;
                round = 0;

                playerIn = new LinkedList();
                for(int i = 0; i < numberOfPlayers; i++){
                    playerIn.add(true);
                }

                reset();
                game();
            });
    }

    //main game method, called after each event
    public void game(){
        System.out.println("game for " + numberOfPlayers + " players: updated frame");
        Ui.clear();

        JComponent[] components = Ui.game(guesses, solution, new Vector2(numberOfPlayers, turn + 1), round);
        JButton btn = (JButton)components[0];
        JTextField inputField = (JTextField)components[2];

        btn.addActionListener(e -> {
                String input = inputField.getText().toUpperCase();

                //input country was not found -> start method again
                if(!countries.contains(input)){
                    game();

                    Ui.countryNotFound(input);

                    return;
                }

                //was already rigth guessed (only important for single-player mode)
                if(right){
                    return;
                }

                right = input.equals(solution.getName());

                if(right){
                    //System.out.println("> playing congrats sound");
                    Music.playMusic("music/congrats.wav", false);
                }

                if(numberOfPlayers > 1){
                    //multiplayer
                    if(right){
                        //find the next player who is still in game
                        do{
                            turn++;
                            if(turn >= numberOfPlayers){
                                turn = 0;
                                round++;
                            }
                        }
                        while(!(boolean)playerIn.get(turn));
                        reset();
                        game();
                    }
                    else{
                        Country country = (Country)countries.getVertex(input);
                        Vector2 distAndSteps = countries.costAndSteps(country.getId(), solution.getId());
                        countries.printMatrix();
                        //System.out.println(country.getId());
                        //System.out.println(distAndSteps.x + " " + distAndSteps.y);

                        guesses.add(new Guess(input, (int)distAndSteps.x, (int)distAndSteps.y));
                        game();

                        //max guesses
                        if(guesses.size() == Math.max((6 - round), 1)){

                            Ui.playerLost(turn);
                            playerIn.set(turn, false);
                            new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        //checks if there are more than 1 player still in game
                                        int index = -1;
                                        int count = 0;
                                        for(int i = 0; i < numberOfPlayers; i++){
                                            if((boolean)playerIn.get(i)){
                                                index = i;
                                                count++;
                                            }
                                        }

                                        if(count != 0){
                                            //find the next player who is still in game
                                            do{
                                                turn++;
                                                if(turn >= numberOfPlayers){
                                                    turn = 0;
                                                    round++;
                                                }
                                            }
                                            while(!(boolean)playerIn.get(turn));
                                        }

                                        reset();
                                        right = false;
                                        guesses = new LinkedList();
                                        game();
                                        if(count == 1){
                                            //only 1 player still in -> player won

                                            JButton newGame = (JButton)Ui.playerWon(index)[0];
                                            newGame.addActionListener(e1 -> {
                                                    System.out.println("click");
                                                    Ui.clear();
                                                    try
                                                    {
                                                        newGame();
                                                    }
                                                    catch (IOException ioe)
                                                    {
                                                        ioe.printStackTrace();
                                                    }
                                                });

                                        }
                                        else{
                                            game();
                                        }
                                    }
                                },
                                5000
                            );

                        }
                    }
                }
                else{
                    //single-player

                    Country country = (Country)countries.getVertex(input);
                    Vector2 distAndSteps = countries.costAndSteps(country.getId(), solution.getId());
                    //countries.printMatrix();
                    //System.out.println(country.getId());
                    //Vector2 distAndSteps = countries.costAndSteps("de", "pl");
                    //System.out.println(distAndSteps.x + " " + distAndSteps.y);

                    int count = guesses.size();

                    if(count == 6-1){
                        Music.playMusic("music/fail.wav", false);
                        guesses.add(new Guess(input, (int)distAndSteps.x, (int)distAndSteps.y));
                    }
                    else if(count > 6-1){
                        Music.playMusic("music/fail.wav", false);
                    }
                    else{
                        guesses.add(new Guess(input, (int)distAndSteps.x, (int)distAndSteps.y));
                    }

                    game();
                }
            });

        JButton giveUpBtn = (JButton)components[1];
        giveUpBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //System.out.println("click");
                    Ui.clear();
                    try
                    {
                        newGame();
                    }
                    catch (java.io.IOException ioe)
                    {
                        ioe.printStackTrace();
                    }
                }
            });

    }
}