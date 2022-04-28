/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ghicestenumarul;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ComponentSampleModel;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author adamaltar
 */
public class GhicesteNumarul extends JFrame {

    public static int MAX_VALUE;
    public String _username;
    private int lives = 5;
    private int generatedNumber;
    JPanel components = new JPanel();
    JPanel drawingArea;
    JLabel first;
    JLabel second = new JLabel("Bine ati venit!");
    JLabel third = new JLabel("Mai aveti "+lives+" incercari");
    JTextField guess = new JTextField();
    
    
    public GhicesteNumarul(int maxVal, String username){
        MAX_VALUE=maxVal;
        generatedNumber= new Random().nextInt(MAX_VALUE)+1;
        _username=username;
        drawingArea= new JPanel(){
            {
                setBackground(Color.CYAN);
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
        first= new JLabel("Introduceti un numar intre 1 si "+MAX_VALUE);
        components.setLayout(new GridLayout(4,1));
        components.add(first);
        components.add(guess);
        components.add(second);
        components.add(third);
        setLayout(new GridLayout(2,1));
        add(components);
        add(drawingArea);
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font f = new Font("Verdana", Font.BOLD, 14);
        first.setFont(f);
        second.setFont(f);
        third.setFont(f);
        guess.setFont(f);
        first.setOpaque(true);
        first.setBackground(Color.YELLOW);
        first.setForeground(Color.RED);
        
        guess.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int userGuess = Integer.parseInt(guess.getText());
                if (generatedNumber==userGuess){//cazul castigator
                    guess.setEnabled(false);
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
