package ghicestenumarul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameSetUp extends JFrame{
    public int maxValue=20;
    public String username=null;


    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JPanel p5 = new JPanel();

    JFrame alertaUser = new JFrame();

    JButton startButton = new JButton("START");

    JLabel instructiuneUsername = new JLabel("Username:", SwingConstants.CENTER);
    JTextField inputUsername = new JTextField();

    JLabel labelDificultate = new JLabel("Alege dificultatea:", SwingConstants.CENTER);

    JRadioButton easyRadioBtn;
    JRadioButton normalRadioBtn = new JRadioButton("Normal (numar intre 1 si 50)");
    JRadioButton hardRadioBtn = new JRadioButton("Greu (numar intre 1 si 100)");
    ButtonGroup difficultyRadioGroup = new ButtonGroup();

    public GameSetUp(){
        easyRadioBtn = new JRadioButton("Usor (numar intre 1 si 20)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setLayout(new BorderLayout());
        setSize(700,500);
        setTitle("Guessing Game");
        //p1.setBackground(Color.RED);
        p1.setPreferredSize(new Dimension(100,100));

        //p2.setBackground(Color.GREEN);
        p2.setPreferredSize(new Dimension(200,100));

        //p3.setBackground(Color.YELLOW);
        p3.setPreferredSize(new Dimension(200,100));

        //p4.setBackground(Color.MAGENTA);
        p4.setPreferredSize(new Dimension(200,100));

        //p5.setBackground(Color.PINK);
        p5.setPreferredSize(new Dimension(200,100));

        add(p1,BorderLayout.NORTH);
        //add(p2,BorderLayout.WEST);
        //add(p3,BorderLayout.EAST);
        add(p4,BorderLayout.SOUTH);
        add(p5,BorderLayout.CENTER);

        inputUsername.setPreferredSize(new Dimension(50,30));

        //p5.setLayout(new GridLayout(6,1,0,5));
        p5.setLayout(new BorderLayout());
        JPanel i1 = new JPanel();
        JPanel i2 = new JPanel();
        JPanel i3 = new JPanel();
        p5.add(i1,BorderLayout.EAST);
        //i1.setBackground(Color.GREEN);
        i1.setPreferredSize(new Dimension(200,50));
        p5.add(i2,BorderLayout.WEST);
        //i2.setBackground(Color.ORANGE);
        i2.setPreferredSize(new Dimension(200,50));
        p5.add(i3,BorderLayout.CENTER);
        i3.setLayout(new GridLayout(6,1));

        difficultyRadioGroup.add(easyRadioBtn);
        difficultyRadioGroup.add(normalRadioBtn);
        difficultyRadioGroup.add(hardRadioBtn);

        i3.add(instructiuneUsername);
        i3.add(inputUsername);

        i3.add(labelDificultate);
        labelDificultate.setVerticalAlignment(SwingConstants.BOTTOM);
        i3.add(easyRadioBtn);
        easyRadioBtn.setHorizontalAlignment(SwingConstants.CENTER);
        easyRadioBtn.setVerticalAlignment(SwingConstants.BOTTOM);
        i3.add(normalRadioBtn);
        normalRadioBtn.setHorizontalAlignment(SwingConstants.CENTER);
        i3.add(hardRadioBtn);
        hardRadioBtn.setHorizontalAlignment(SwingConstants.CENTER);
        hardRadioBtn.setVerticalAlignment(SwingConstants.TOP);

        //p5.add(instructiuneUsername);
        inputUsername.setHorizontalAlignment(JTextField.CENTER);
        //inputUsername.setColumns(5);
        //p5.add(inputUsername);
        p4.add(startButton);
        easyRadioBtn.setSelected(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(normalRadioBtn.isSelected()){
                    maxValue=50;
                }else if(hardRadioBtn.isSelected()){
                    maxValue=100;
                }
                username=inputUsername.getText();
                if(username.length()<1){
                    JOptionPane.showMessageDialog(alertaUser,"Introduceti username-ul!","Alerta",JOptionPane.WARNING_MESSAGE);
                }else {
                    setVisible(false);
                    new GhicesteNumarul(maxValue,username).setVisible(true);
                }
            }
        });
    }


}
