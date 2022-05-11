/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ghicestenumarul;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ComponentSampleModel;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author adamaltar & dumitrudenisa
 */
public class GhicesteNumarul extends JFrame {

    public static int MAX_VALUE;
    private final Timer timer;
    public String _username;
    private int lives = 5;
    private int generatedNumber;
    JPanel components = new JPanel();
    JPanel drawingArea;
    JLabel first;
    JLabel second;
    JLabel third = new JLabel("Mai aveti "+lives+" incercari", SwingConstants.CENTER);
    JTextField guess = new JTextField();
    JFrame alertaExpirareTimp = new JFrame();
    JButton btnReset = new JButton();
    
    
    public GhicesteNumarul(int maxVal, String username, int interval){
        MAX_VALUE=maxVal;
        generatedNumber= new Random().nextInt(MAX_VALUE)+1;
        _username=username;
        btnReset.setText("Reset");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.restart();
                generatedNumber= new Random().nextInt(MAX_VALUE)+1;
                guess.selectAll();
                lives = 5;
                drawingArea.repaint();
                third.setText("Mai aveti "+lives+" incercari");
                first.setText("Introduceti un numar intre 1 si "+MAX_VALUE);
                guess.setEnabled(true);
            }
        });
        second = new JLabel("Bine ai venit, "+username+"!", SwingConstants.CENTER);
        ActionListener gameStopper = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guess.setEnabled(false);
                JOptionPane.showMessageDialog(alertaExpirareTimp,"Timpul a expirat!","Time's up",JOptionPane.WARNING_MESSAGE);
                first.setText("GAME OVER, TIMPUL A EXPIRAT!");
                third.setText("Numarul era: "+generatedNumber);
            }
        };
        timer  = new Timer(interval, gameStopper);
        timer.setRepeats(false);
        timer.start();
        drawingArea= new JPanel(){
            {
                setBackground(Color.GRAY);
            }
            @Override
            public void paintComponent(Graphics gr){
                super.paintComponent(gr);
                Graphics2D g = (Graphics2D)gr;
                g.setStroke(new BasicStroke(3));
                int w = getWidth();
                int h = getHeight();
                //g.drawRect(20, 20, 150, 150);
                switch (lives){
                    case -1: g.drawArc(w/2-15, 20, 30, 30, 225, 90);
                    case 0: g.drawLine(0,0,w,h); g.drawLine(w,0,0,h);//taietura
                    case 1: g.drawLine(w/2, h-40, w/2-50, h-10); g.drawLine(w/2, h-40, w/2+50, h-10);//picioare
                    case 2: g.drawLine(w/2, 70, w/2-50, 85); g.drawLine(w/2, 70, w/2+50, 85);//maini
                    case 3: g.drawLine(w/2, 60, w/2, h-40);//corp
                    case 4: g.drawOval(w/2-25, 10, 50, 50);//cap

                }

            }

        };
        first= new JLabel("Introduceti un numar intre 1 si "+MAX_VALUE, SwingConstants.CENTER);
        components.setLayout(new GridLayout(5,1));
        //components.add(first);
        components.add(guess);
        //components.add(second);
        components.add(third);
        components.add(btnReset);
        btnReset.setVerticalAlignment(SwingConstants.BOTTOM);



        JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(100,100));
        p1.setBackground(Color.LIGHT_GRAY);
        //p1.setBackground(Color.RED);
        JPanel p2 = new JPanel();
        //p2.setBackground(Color.YELLOW);
        p2.setPreferredSize(new Dimension(350,100));
        JPanel p3 = new JPanel();
        //p3.setBackground(Color.BLUE);
        p3.setPreferredSize(new Dimension(350,100));
        JPanel compTitlu = new JPanel(new GridLayout(2,1));
        compTitlu.add(second);
        compTitlu.add(first);
        JPanel p4 = new JPanel();


        setLayout(new BorderLayout());
        setSize(700,500);
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.WEST);
        add(p3,BorderLayout.EAST);
        p3.setLayout(new GridLayout(1,1));
        p1.add(compTitlu);
        p2.add(components);

        //p2.add(btnReset);
        p3.add(drawingArea);
//        add(components);
//        add(drawingArea);
//        add(btnReset);
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font f = new Font("Verdana", Font.BOLD, 14);
        first.setFont(f);
        second.setFont(f);
        third.setFont(f);
        guess.setFont(f);
        first.setOpaque(true);
        //first.setBackground(Color.YELLOW);
        //first.setForeground(Color.RED);

//        drawingArea.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                timer.restart();
//                generatedNumber= new Random().nextInt(MAX_VALUE)+1;
//                guess.selectAll();
//                third.setText("Mai aveti "+lives+" incercari");
//                first.setText("Introduceti un numar intre 1 si "+MAX_VALUE);
//                guess.setEnabled(true);
//            }
//        });


        
        guess.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int userGuess = Integer.parseInt(guess.getText());
                if (generatedNumber==userGuess){//cazul castigator
                    guess.setEnabled(false);
                    timer.stop();
                    second.setText("Felicitari, ai ghicit!!");
                    first.setText("GAME OVER!"); 
                    lives=-1;
                } else {
                    lives--;
                    if (lives==0){//cazul pierzator
                        guess.setEnabled(false);
                        second.setText("Oops, ai pierdut!!");
                        first.setText("GAME OVER!");
                        third.setText("Numarul era: "+generatedNumber);
                    
                    } else {//cazul in care jocul continua
                        guess.selectAll();
                        third.setText("Mai aveti "+lives+" incercari");
                        if (userGuess>generatedNumber)
                            second.setText("Introduceti un numar mai mic");
                        else
                            second.setText("Introduceti un numar mai mare");
                    
                    }
                    
                
                
                }
                drawingArea.repaint();
            
            }
        
        });

    }

}
