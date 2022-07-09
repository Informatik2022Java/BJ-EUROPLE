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
 *  static so every class can control the UI without having to reference it
 */

public class Ui
{
    public static JFrame FRAME;
    private static JLayeredPane PANEL;
    
    private static Font FONT_KOULEN_22;
    private static Font FONT_KOULEN_30;
    private static Font FONT_KOULEN_40;
    private static Font FONT_KOULEN_73;

    public static void start(){
        FRAME = new JFrame("EUROPLE");
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = new Dimension(1071, 700);
        FRAME.setPreferredSize(size);
        FRAME.setMinimumSize(size);
        FRAME.setMaximumSize(size);
        FRAME.setResizable(false);
        FRAME.setBackground(Color.WHITE);
        
        ImageIcon img = new ImageIcon("images/UI/icon.png");
        FRAME.setIconImage(img.getImage());

        PANEL = new JLayeredPane();
        PANEL.setBackground(Color.WHITE);
        PANEL.setLayout(null);
        PANEL.setOpaque(true);
        
        
        try
        {
            FONT_KOULEN_22 = getFont("Koulen-Regular.ttf", 22f);
            FONT_KOULEN_30 = getFont("Koulen-Regular.ttf", 30f);
            FONT_KOULEN_40 = getFont("Koulen-Regular.ttf", 40f);
            FONT_KOULEN_73 = getFont("Koulen-Regular.ttf", 73f);
            //System.out.println("loaded fonts");
        }
        catch (FontFormatException ffe)
        {
            ffe.printStackTrace();
        }        

        FRAME.add(PANEL);
        FRAME.pack();
        FRAME.setVisible(true);
    }
    
    //loading screen
    public static void loading(){
        //System.out.println(">loading screen");
        URL url = Ui.class.getClassLoader().getResource("images/ui/loadingAnim.gif");
        JLabel loadingGif = new JLabel(new ImageIcon(url));
        loadingGif.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadingGif.setBounds(918, 592, 123, 41);
        PANEL.add(loadingGif);

        JLabel logo = new JLabel(new ImageIcon(((new ImageIcon("images/ui/logo2.png")).getImage()).getScaledInstance(541,246, java.awt.Image.SCALE_SMOOTH)));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBounds(265, 191, 541, 246);
        PANEL.add(logo);

        PANEL.validate();
        PANEL.repaint();
        PANEL.revalidate();
    }

    public static JComponent[] playerAmountSelect(){
        Font customFont;

        JLabel logo = new JLabel(new ImageIcon(((new ImageIcon("images/ui/logo2.png")).getImage()).getScaledInstance(264,120, java.awt.Image.SCALE_SMOOTH)));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBounds(351, 30, 367, 119);

        JTextField input = new JTextField("1");
        input.setPreferredSize(new Dimension(200,40));
        input.setFont(FONT_KOULEN_22);
        input.setBounds(436,295,200,40);

        JButton btn = new JButton("start");
        btn.setBounds(436, 344,200,40);
        btn.setFont(FONT_KOULEN_30);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(Color.decode("#4E9424"));
        btn.setForeground(Color.WHITE);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        PANEL.add(btn);
        PANEL.add(input);
        PANEL.add(logo);

        JLabel country = new JLabel("please enter the number of players", SwingConstants.CENTER);
        country.setFont(FONT_KOULEN_40);
        country.setBounds(0,182,1071,50);
        PANEL.add(country);

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
        PANEL.add(music);

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
                } catch (FontFormatException ioException) {
                    ioException.printStackTrace();
                }
                title.setBounds(30, 30, 440, 70);
                title.setOpaque(false);
                popupPanel.add(title);

                JLabel coded = new JLabel("coded by Lars, Philipp & Felix", SwingConstants.LEFT);
        
                coded.setFont(FONT_KOULEN_22);
                
                coded.setBounds(30, 100, 440, 70);
                coded.setOpaque(false);
                popupPanel.add(coded);

                JLabel music = new JLabel("Music by soundimage.org", SwingConstants.LEFT);
                
                    music.setFont(FONT_KOULEN_22);
                
                music.setBounds(30, 170, 440, 70);
                music.setOpaque(false);
                popupPanel.add(music);

                JLabel images = new JLabel("country images by worldle.teuteuf.fr", SwingConstants.LEFT);
                
                    images.setFont(FONT_KOULEN_22);
                images.setBounds(30, 240, 440, 70);
                images.setOpaque(false);
                popupPanel.add(images);

                JLabel info = new JLabel("country info by wikipedia.org", SwingConstants.LEFT);
                
                    info.setFont(FONT_KOULEN_22);
                info.setBounds(30, 310, 440, 70);
                info.setOpaque(false);
                popupPanel.add(info);

                JLabel github = new JLabel("github.com/Informatik2022Java", SwingConstants.CENTER);
                
                    github.setFont(FONT_KOULEN_22);
                github.setBounds(30, 410, 440, 70);
                github.setOpaque(false);
                popupPanel.add(github);

                JLabel thanks = new JLabel("thanks for playing :)", SwingConstants.CENTER);
                
                    thanks.setFont(FONT_KOULEN_22);
                thanks.setBounds(30, 480, 440, 70);
                thanks.setOpaque(false);
                popupPanel.add(thanks);

                popup.add(popupPanel);
                popup.pack();
                popup.setVisible(true);
            }
        });
        PANEL.add(credits);

        PANEL.validate();
        PANEL.repaint();
        PANEL.revalidate();

        JComponent[] list = new JComponent[2];
        list[0] = btn;
        list[1] = input;
        return list;
    }

    public static void clear(){
        PANEL.removeAll();
        PANEL.repaint();
    }

    public static JComponent[] game(LinkedList guesses, Country solution, Vector2 player, int round){
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
        PANEL.add(logo);

        JLabel outline = new JLabel(new ImageIcon(((new ImageIcon("images/maps/png/" + solution.getId() + ".png")).getImage()).getScaledInstance(195,195, java.awt.Image.SCALE_SMOOTH)));
        outline.setAlignmentX(Component.CENTER_ALIGNMENT);
        outline.setBounds(438,119,195,195);
        PANEL.add(outline);

        JLabel country = new JLabel("country", SwingConstants.CENTER);
        country.setFont(FONT_KOULEN_22);
        country.setBounds(300,322,157,30);
        PANEL.add(country);

        JLabel distance = new JLabel("distance", SwingConstants.CENTER);
        distance.setFont(FONT_KOULEN_22);
        distance.setBounds(457,322,157,30);
        PANEL.add(distance);

        JLabel steps = new JLabel("steps", SwingConstants.CENTER);
        steps.setFont(FONT_KOULEN_22);
        steps.setBounds(613,322,157,30);
        PANEL.add(steps);

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
            PANEL.add(bar);

            JLabel name = new JLabel(((Guess)(guesses.get(i))).getName(), SwingConstants.CENTER);
            name.setFont(FONT_KOULEN_22);
            name.setBounds(0,0,157,30);
            name.setOpaque(false);
            bar.add(name);

            JLabel dist = new JLabel(((Guess)(guesses.get(i))).getDistance() + "km", SwingConstants.CENTER);
            dist.setFont(FONT_KOULEN_22);
            dist.setBounds(157,0,157,30);
            dist.setOpaque(false);
            bar.add(dist);

            JLabel step = new JLabel(((Guess)(guesses.get(i))).getSteps() + "", SwingConstants.CENTER);
            step.setFont(FONT_KOULEN_22);
            step.setBounds(314,0 ,157,30);
            step.setOpaque(false);
            bar.add(step);
        }

        int maxGuesses = Math.max((6 - round), 1);
        for(int i = 0; i < maxGuesses - guesses.size(); i++){
            JPanel bar = new JPanel();
            bar.setBackground(Color.decode("#D9D9D9"));
            bar.setBounds(300,360 + 35*(i + guesses.size()), 470, 30);
            PANEL.add(bar);
        }

        JTextField guess = new JTextField();
        guess.setBounds(300,588,235,37);
        guess.setFont(FONT_KOULEN_22);
        PANEL.add(guess);

        JButton btn = new JButton("guess");
        btn.setBounds(535,588,235,37);
        btn.setFont(FONT_KOULEN_22);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        //btn.setContentAreaFilled(false);
        btn.setBackground(Color.decode("#4E9424"));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setForeground(Color.WHITE);
        PANEL.add(btn);

        JLabel playerCount = new JLabel((int)player.x + " player", SwingConstants.CENTER);
        playerCount.setFont(FONT_KOULEN_22);
        playerCount.setBounds(30,30,110,40);
        PANEL.add(playerCount);

        JLabel playerTurn = new JLabel((int)player.y + "\'s turn", SwingConstants.CENTER);
        playerTurn.setFont(FONT_KOULEN_22);
        playerTurn.setBounds(30,134,110,40);
        PANEL.add(playerTurn);

        JButton giveUp = new JButton("give up");
        if(guessed){
            giveUp = new JButton("start");
        }
        giveUp.setBounds(30,82,110,37);
        giveUp.setFont(FONT_KOULEN_22);
        giveUp.setBorderPainted(false);
        giveUp.setFocusPainted(false);
        //btn.setContentAreaFilled(false);
        giveUp.setBackground(Color.decode("#4E9424"));
        giveUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        giveUp.setForeground(Color.WHITE);
        PANEL.add(giveUp);

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
        PANEL.add(help);

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
                        } catch (Exception f) {
                            f.printStackTrace();
                        }
                        title.setBounds(30,30,440, 70);
                        popupPanel.add(title);

                        JLabel wikipedia = new JLabel("wikipedia:", SwingConstants.LEFT);
                        
                            wikipedia.setFont(FONT_KOULEN_22);
                        wikipedia.setBounds(30, 100, 440, 70);
                        popupPanel.add(wikipedia);

                        JButton maps = new JButton("maps");
                        maps.setBounds(660, 47, 110, 37);
                            maps.setFont(FONT_KOULEN_22);
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
            PANEL.add(info);
        }

        PANEL.validate();
        PANEL.repaint();
        PANEL.revalidate();
        //System.out.println(logo);

        JComponent[] list = new JComponent[3];
        list[0] = btn;
        list[1] = giveUp;
        list[2] = guess;
        return list;
    }

    public static void countryNotFound(String notFoundCountry){
        JLabel notFound = new JLabel("<html><p>no country: " + notFoundCountry + "</p></html>", SwingConstants.CENTER);
        notFound.setFont(FONT_KOULEN_22);
        notFound.setBounds(30,532,110,100);
        PANEL.add(notFound);

        PANEL.validate();
        PANEL.repaint();
        PANEL.revalidate();
    }

    public static void playerLost(int number){
        JPanel out = new JPanel();
        out.setBounds(169,119,733,543);
        out.setLayout(null);
        out.setBackground(new Color(0,0,0,0));
        
        JLabel outText = new JLabel("<html><p>Player " + (number+1) + " is out</p></html>", SwingConstants.CENTER);
        outText.setFont(FONT_KOULEN_73);
        outText.setBounds(0,0,733,469);
        outText.setOpaque(false);
        out.add(outText);
        
        PANEL.add(out, 1, 0);

        PANEL.validate();
        PANEL.repaint();
        PANEL.revalidate();
    }
    
    public static JComponent[] playerWon(int number){
        JPanel won = new JPanel();
        won.setBounds(169,119,733,543);
        won.setLayout(null);
        won.setBackground(new Color(0,0,0,0));
        
        JLabel player = new JLabel("<html><p>Player " + (number+1) + " won</p></html>", SwingConstants.CENTER);
        player.setFont(FONT_KOULEN_73);
        player.setBounds(0,0,733,469);
        player.setOpaque(false);
        won.add(player);
        
        JButton newGame = new JButton("new game");
        newGame.setBounds(244, 469, 235, 37);
        newGame.setFont(FONT_KOULEN_22);
        newGame.setBorderPainted(false);
        newGame.setFocusPainted(false);
        //btn.setContentAreaFilled(false);
        newGame.setBackground(Color.decode("#4E9424"));
        newGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        newGame.setForeground(Color.WHITE);
        won.add(newGame);
        
        PANEL.add(won, 1, 0);

        PANEL.validate();
        PANEL.repaint();
        PANEL.revalidate();
        
        JComponent[] list = new JComponent[1];
        list[0] = newGame;
        return list;
    }

    public static Font getFont(String name, float size) throws FontFormatException{
        try
            {
                // System.out.println("get font: " + name + " " + size);
        
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/" + name)).deriveFont(size);
            
             GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            return customFont;
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
           
        return null;
    }

    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
