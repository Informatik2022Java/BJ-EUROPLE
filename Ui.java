import java.util.Scanner;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.io.IOException;
import java.awt.FontFormatException;
import java.io.File;
import java.io.InputStream;
import java.awt.Cursor;

/*
 * 
 *  general idea:
 *  Ui methods are called when they should diaplay something
 *  Methods return all important components like buttons, inputfields etc
 *  Game-class adds eventlistener -> all data could be easyly stored in game-class
 * 
 */

public class Ui
{
    //private static JPanel panel;
    public static JFrame frame;
    private static JLayeredPane panel;

    public static void start(){
        frame = new JFrame("EUROPLE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = new Dimension(1071, 700);
        frame.setPreferredSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setResizable(false);
        frame.setBackground(Color.WHITE);
        
        ImageIcon img = new ImageIcon("images/UI/icon.png");
        frame.setIconImage(img.getImage());

        panel = new JLayeredPane();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setOpaque(true);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static JComponent[] loading(){
        System.out.println("loading...");
        URL url = Ui.class.getClassLoader().getResource("images/ui/loadingAnim.gif");
        JLabel loadingGif = new JLabel(new ImageIcon(url));
        loadingGif.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadingGif.setBounds(918, 592, 123, 41);
        panel.add(loadingGif);

        JLabel logo = new JLabel(new ImageIcon(((new ImageIcon("images/ui/logo2.png")).getImage()).getScaledInstance(541,246, java.awt.Image.SCALE_SMOOTH)));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBounds(265, 191, 541, 246);
        panel.add(logo);

        panel.validate();
        panel.repaint();
        panel.revalidate();

        return null;
    }

    public static JComponent[] playerAmountSelect() throws IOException {
        Font customFont;

        JLabel logo = new JLabel(new ImageIcon(((new ImageIcon("images/ui/logo2.png")).getImage()).getScaledInstance(264,120, java.awt.Image.SCALE_SMOOTH)));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBounds(351, 0, 367, 119);

        JTextField input = new JTextField("1");
        input.setPreferredSize(new Dimension(200,40));
        input.setFont(getFont("Koulen-Regular.ttf", 22f));
        input.setBounds(436,295,200,40);

        JButton btn = new JButton("start");
        btn.setBounds(436, 344,200,40);
        btn.setFont(getFont("Koulen-Regular.ttf", 30f));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        //btn.setContentAreaFilled(false);
        btn.setBackground(Color.decode("#4E9424"));
        btn.setForeground(Color.WHITE);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panel.add(btn);
        panel.add(input);
        panel.add(logo);

        JLabel country = new JLabel("please enter the number of players", SwingConstants.CENTER);
        country.setFont(getFont("Koulen-Regular.ttf", 40f));
        country.setBounds(0,182,1071,50);
        panel.add(country);

        JLabel music = new JLabel(new ImageIcon(((new ImageIcon("images/UI/music.png")).getImage()).getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH)));
        music.setAlignmentX(Component.CENTER_ALIGNMENT);
        music.setBounds(1001, 592, 40, 40);
        music.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        music.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean musicPlaying = Music.toogleMusic("music/gamemusic.wav");
                if(musicPlaying){
                    music.setIcon(new ImageIcon(((new ImageIcon("images/UI/music.png")).getImage()).getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH)));
                }
                else{
                    music.setIcon(new ImageIcon(((new ImageIcon("images/UI/musicOff.png")).getImage()).getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH)));
                }
            }
        });
        panel.add(music);

        JLabel credits = new JLabel(new ImageIcon(((new ImageIcon("images/UI/credit.png")).getImage()).getScaledInstance(123,41, java.awt.Image.SCALE_SMOOTH)));
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        credits.setBounds(30, 591, 123, 41);
        credits.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        credits.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame popup = new JFrame();
                //popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Dimension size = new Dimension(500, 600);
                popup.setPreferredSize(size);
                popup.setMinimumSize(size);
                popup.setMaximumSize(size);
                popup.setResizable(false);
                popup.setBackground(Color.WHITE);
                //frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/UI/icon.png")));
                ImageIcon img = new ImageIcon("images/UI/icon.png");
                popup.setIconImage(img.getImage());

                JPanel popupPanel = new JPanel();
                popupPanel.setBackground(Color.WHITE);
                popupPanel.setLayout(null);

                JLabel title = new JLabel("credits", SwingConstants.LEFT);
                try {
                    title.setFont(getFont("Koulen-Regular.ttf", 50f));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                title.setBounds(30, 30, 440, 70);
                title.setOpaque(false);
                popupPanel.add(title);

                JLabel coded = new JLabel("coded by Lars, Philipp & Felix", SwingConstants.LEFT);
                try {
                    coded.setFont(getFont("Koulen-Regular.ttf", 22f));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                coded.setBounds(30, 100, 440, 70);
                coded.setOpaque(false);
                popupPanel.add(coded);

                JLabel music = new JLabel("Music by soundimage.org", SwingConstants.LEFT);
                try {
                    music.setFont(getFont("Koulen-Regular.ttf", 22f));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                music.setBounds(30, 170, 440, 70);
                music.setOpaque(false);
                popupPanel.add(music);

                JLabel images = new JLabel("country images by worldle.teuteuf.fr", SwingConstants.LEFT);
                try {
                    images.setFont(getFont("Koulen-Regular.ttf", 22f));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                images.setBounds(30, 240, 440, 70);
                images.setOpaque(false);
                popupPanel.add(images);

                JLabel info = new JLabel("country info by wikipedia.org", SwingConstants.LEFT);
                try {
                    info.setFont(getFont("Koulen-Regular.ttf", 22f));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                info.setBounds(30, 310, 440, 70);
                info.setOpaque(false);
                popupPanel.add(info);

                JLabel github = new JLabel("github.com/Informatik2022Java", SwingConstants.CENTER);
                try {
                    github.setFont(getFont("Koulen-Regular.ttf", 22f));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                github.setBounds(30, 410, 440, 70);
                github.setOpaque(false);
                popupPanel.add(github);

                JLabel thanks = new JLabel("thanks for playing :)", SwingConstants.CENTER);
                try {
                    thanks.setFont(getFont("Koulen-Regular.ttf", 22f));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                thanks.setBounds(30, 480, 440, 70);
                thanks.setOpaque(false);
                popupPanel.add(thanks);

                popup.add(popupPanel);
                popup.pack();
                popup.setVisible(true);
            }
        });
        panel.add(credits);

        panel.validate();
        panel.repaint();
        panel.revalidate();

        JComponent[] list = new JComponent[2];
        list[0] = btn;
        list[1] = input;
        return list;
    }

    public static void clear(){
        panel.removeAll();

        panel.repaint();
    }

    public static JComponent[] game(LinkedList guesses, Country solution, Vector2 player, int round) throws IOException {
        //player: x = number of all players, y = current player
        System.out.println("game: " + solution.getName());
        boolean guessed = false;
        for(int i = 0; i < guesses.size(); i++){
            if(((Guess)(guesses.get(i))).getName().equals(solution.getName())){
                guessed = true;
            }
        }

        JLabel logo = new JLabel(new ImageIcon(((new ImageIcon("images/ui/logo.png")).getImage()).getScaledInstance(368,120, java.awt.Image.SCALE_SMOOTH)));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBounds(351, 0, 367, 119);
        panel.add(logo);

        JLabel outline = new JLabel(new ImageIcon(((new ImageIcon("images/maps/png/" + solution.getId() + ".png")).getImage()).getScaledInstance(195,195, java.awt.Image.SCALE_SMOOTH)));
        outline.setAlignmentX(Component.CENTER_ALIGNMENT);
        outline.setBounds(438,119,195,195);
        panel.add(outline);

        JLabel country = new JLabel("country", SwingConstants.CENTER);
        country.setFont(getFont("Koulen-Regular.ttf", 22f));
        country.setBounds(300,322,157,30);
        panel.add(country);

        JLabel distance = new JLabel("distance", SwingConstants.CENTER);
        distance.setFont(getFont("Koulen-Regular.ttf", 22f));
        distance.setBounds(457,322,157,30);
        panel.add(distance);

        JLabel steps = new JLabel("steps", SwingConstants.CENTER);
        steps.setFont(getFont("Koulen-Regular.ttf", 22f));
        steps.setBounds(613,322,157,30);
        panel.add(steps);

        for(int i = 0; i < guesses.size(); i++){
            JPanel bar = new JPanel();
            bar.setLayout(null);
            if(((Guess)(guesses.get(i))).getName().equals(solution.getName())){
                bar.setBackground(Color.decode("#4E9424"));
            }
            else{
                bar.setBackground(Color.decode("#A5A5A5"));
            }
            bar.setBounds(300,360 + 35*i, 470, 30);
            panel.add(bar);

            JLabel name = new JLabel(((Guess)(guesses.get(i))).getName(), SwingConstants.CENTER);
            name.setFont(getFont("Koulen-Regular.ttf", 22f));
            name.setBounds(0,0,157,30);
            name.setOpaque(false);
            bar.add(name);

            JLabel dist = new JLabel(((Guess)(guesses.get(i))).getDistance() + "km", SwingConstants.CENTER);
            dist.setFont(getFont("Koulen-Regular.ttf", 22f));
            dist.setBounds(157,0,157,30);
            dist.setOpaque(false);
            bar.add(dist);

            JLabel step = new JLabel(((Guess)(guesses.get(i))).getSteps() + "", SwingConstants.CENTER);
            step.setFont(getFont("Koulen-Regular.ttf", 22f));
            step.setBounds(314,0 ,157,30);
            step.setOpaque(false);
            bar.add(step);
        }

        int maxGuesses = Math.max((6 - round), 1);
        for(int i = 0; i < maxGuesses - guesses.size(); i++){
            JPanel bar = new JPanel();
            bar.setBackground(Color.decode("#D9D9D9"));
            bar.setBounds(300,360 + 35*(i + guesses.size()), 470, 30);
            panel.add(bar);
        }

        JTextField guess = new JTextField();
        guess.setBounds(300,588,235,37);
        guess.setFont(getFont("Koulen-Regular.ttf", 22f));
        panel.add(guess);

        JButton btn = new JButton("guess");
        btn.setBounds(535,588,235,37);
        btn.setFont(getFont("Koulen-Regular.ttf", 22f));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        //btn.setContentAreaFilled(false);
        btn.setBackground(Color.decode("#4E9424"));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setForeground(Color.WHITE);
        panel.add(btn);

        JLabel playerCount = new JLabel((int)player.x + " player", SwingConstants.CENTER);
        playerCount.setFont(getFont("Koulen-Regular.ttf", 22f));
        playerCount.setBounds(30,30,110,40);
        panel.add(playerCount);

        JLabel playerTurn = new JLabel((int)player.y + "\'s turn", SwingConstants.CENTER);
        playerTurn.setFont(getFont("Koulen-Regular.ttf", 22f));
        playerTurn.setBounds(30,134,110,40);
        panel.add(playerTurn);

        JButton giveUp = new JButton("give up");
        if(guessed){
            giveUp = new JButton("start");
        }
        giveUp.setBounds(30,82,110,37);
        giveUp.setFont(getFont("Koulen-Regular.ttf", 22f));
        giveUp.setBorderPainted(false);
        giveUp.setFocusPainted(false);
        //btn.setContentAreaFilled(false);
        giveUp.setBackground(Color.decode("#4E9424"));
        giveUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        giveUp.setForeground(Color.WHITE);
        panel.add(giveUp);

        JLabel help = new JLabel(new ImageIcon(((new ImageIcon("images/UI/help.png")).getImage()).getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH)));
        help.setAlignmentX(Component.CENTER_ALIGNMENT);
        help.setBounds(1001, 30, 40, 40);
        help.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        help.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openWebpage("https://docs.google.com/document/d/1gDcPWK0FKAoBOFZ3fq5qvlNocPAcOAJp08E77_EMw_8/edit?usp=sharing");
                }
            });
        panel.add(help);

        if(guessed || guesses.size() >= 6){
            JLabel info = new JLabel(new ImageIcon(((new ImageIcon("images/UI/info.png")).getImage()).getScaledInstance(120,40, java.awt.Image.SCALE_SMOOTH)));
            info.setAlignmentX(Component.CENTER_ALIGNMENT);
            info.setBounds(921, 80, 120, 40);
            info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            info.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //openWebpage("https://docs.google.com/document/d/1gDcPWK0FKAoBOFZ3fq5qvlNocPAcOAJp08E77_EMw_8/edit?usp=sharing");
                        //JOptionPane.showInputDialog(frame, wikiSearch.search(solution.getName()));

                        JFrame popup = new JFrame();
                        //popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        Dimension size = new Dimension(800, 600);
                        popup.setPreferredSize(size);
                        popup.setMinimumSize(size);
                        popup.setMaximumSize(size);
                        popup.setResizable(false);
                        popup.setBackground(Color.WHITE);
                        //frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/UI/icon.png")));
                        ImageIcon img = new ImageIcon("images/UI/icon.png");
                        popup.setIconImage(img.getImage());

                        JPanel popupPanel = new JPanel();
                        popupPanel.setBackground(Color.WHITE);
                        popupPanel.setLayout(null);

                        JTextArea wiki = new JTextArea(API.wikiSearch(solution.getName()));
                        wiki.setLineWrap(true);
                        try{
                            wiki.setFont(getFont("Koulen-Regular.ttf", 15f));
                        }
                        catch (Exception f)
                        {
                        }
                        JScrollPane scroll = new JScrollPane(wiki);
                        scroll.setBounds(30, 170, 740, 360);
                        scroll.setHorizontalScrollBar(null);
                        scroll.setBorder(BorderFactory.createEmptyBorder());
                        popupPanel.add(scroll);

                        JLabel title = new JLabel(solution.getName(), SwingConstants.LEFT);
                        try {
                            title.setFont(getFont("Koulen-Regular.ttf", 50f));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        title.setBounds(30,30,440, 70);
                        popupPanel.add(title);

                        JLabel wikipedia = new JLabel("wikipedia:", SwingConstants.LEFT);
                        try {
                            wikipedia.setFont(getFont("Koulen-Regular.ttf", 22f));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        wikipedia.setBounds(30, 100, 440, 70);
                        popupPanel.add(wikipedia);

                        JButton maps = new JButton("maps");
                        maps.setBounds(660, 47, 110, 37);
                        try {
                            maps.setFont(getFont("Koulen-Regular.ttf", 22f));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        maps.setBorderPainted(false);
                        maps.setFocusPainted(false);
                        //btn.setContentAreaFilled(false);
                        maps.setBackground(Color.decode("#4E9424"));
                        maps.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        maps.setForeground(Color.WHITE);
                        maps.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                openWebpage("https://www.google.com/maps/place/" + solution.getName().replace(" ", "%20"));
                            }
                        });
                        popupPanel.add(maps);

                        popup.add(popupPanel);
                        popup.pack();
                        popup.setVisible(true);
                    }
                });
            panel.add(info);
        }

        panel.validate();
        panel.repaint();
        panel.revalidate();
        //System.out.println(logo);

        JComponent[] list = new JComponent[3];
        list[0] = btn;
        list[1] = giveUp;
        list[2] = guess;
        return list;
    }

    public static Font getFont(String name, float size) throws IOException {
        // System.out.println("get font: " + name + " " + size);
        try
        {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/" + name)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            return customFont;
        }
        catch (FontFormatException ffe)
        {
            return null;
        }
    }

    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void countryNotFound(String notFoundCountry) throws IOException {
        JLabel notFound = new JLabel("<html><p>no country: " + notFoundCountry + "</p></html>", SwingConstants.CENTER);
        notFound.setFont(getFont("Koulen-Regular.ttf", 22f));
        notFound.setBounds(30,532,110,100);
        panel.add(notFound);

        panel.validate();
        panel.repaint();
        panel.revalidate();
    }

    public static void playerLost(int number) throws IOException{
        JPanel out = new JPanel();
        out.setBounds(169,119,733,543);
        out.setLayout(null);
        out.setBackground(new Color(0,0,0,0));
        
        JLabel notFound = new JLabel("<html><p>Player " + (number+1) + " is out</p></html>", SwingConstants.CENTER);
        notFound.setFont(getFont("Koulen-Regular.ttf", 73f));
        notFound.setBounds(0,0,733,469);
        notFound.setOpaque(false);
        out.add(notFound);
        
        panel.add(out, 1, 0);

        panel.validate();
        panel.repaint();
        panel.revalidate();
    }
    
    public static JComponent[] playerWon(int number) throws IOException{
        JPanel won = new JPanel();
        won.setBounds(169,119,733,543);
        won.setLayout(null);
        won.setBackground(new Color(0,0,0,0));
        
        JLabel player = new JLabel("<html><p>Player " + (number+1) + " won</p></html>", SwingConstants.CENTER);
        player.setFont(getFont("Koulen-Regular.ttf", 73f));
        player.setBounds(0,0,733,469);
        player.setOpaque(false);
        won.add(player);
        
        
        JButton newGame = new JButton("new game");
        newGame.setBounds(244, 469, 235, 37);
        newGame.setFont(getFont("Koulen-Regular.ttf", 22f));
        newGame.setBorderPainted(false);
        newGame.setFocusPainted(false);
        //btn.setContentAreaFilled(false);
        newGame.setBackground(Color.decode("#4E9424"));
        newGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newGame.setForeground(Color.WHITE);
        won.add(newGame);
        
        panel.add(won, 1, 0);

        panel.validate();
        panel.repaint();
        panel.revalidate();
        
        JComponent[] list = new JComponent[1];
        list[0] = newGame;
        return list;
    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
