import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.util.*;
import java.io.IOException;


public class Result extends JLabel implements ActionListener{
    private Label piclabel;

    public JButton okbutton,againbutton,returnbutton;
    public int num;
    
    public Result(int card){

        ImageIcon imageIcon1 = new ImageIcon("picture/icon1.png"); //胡牌背景圖
        Image image1 = imageIcon1.getImage();  
        Icon icon1=new ImageIcon(image1.getScaledInstance(600,400, java.awt.Image.SCALE_SMOOTH)); 

        ImageIcon imageIcon2 = new ImageIcon("picture/icon2.png"); //流局背景圖
        Image image2 = imageIcon2.getImage();  
        Icon icon2=new ImageIcon(image2.getScaledInstance(600,400, java.awt.Image.SCALE_SMOOTH));

        ImageIcon imageIcon3 = new ImageIcon("picture/icon3.png"); //失敗背景圖
        Image image3 = imageIcon3.getImage();  
        Icon icon3=new ImageIcon(image3.getScaledInstance(600,400, java.awt.Image.SCALE_SMOOTH));

        ImageIcon imageIcon4 = new ImageIcon("picture/ok.png"); //確定按鈕
        Image image4 = imageIcon4.getImage();  
        Icon icon4=new ImageIcon(image4.getScaledInstance(100,50, java.awt.Image.SCALE_SMOOTH));

        ImageIcon imageIcon5 = new ImageIcon("picture/again.png"); //遊戲按鈕
        Image image5 = imageIcon5.getImage();  
        Icon icon5=new ImageIcon(image5.getScaledInstance(100,50, java.awt.Image.SCALE_SMOOTH));

        ImageIcon imageIcon6 = new ImageIcon("picture/returnbutton.png"); //主頁按鈕
        Image image6 = imageIcon6.getImage();  
        Icon icon6=new ImageIcon(image6.getScaledInstance(100,50, java.awt.Image.SCALE_SMOOTH));

        this.piclabel = new Label();
        piclabel.getAllcard(Label.allcard);

        setOpaque(false);
        setLayout(null);
///////////////////////////////////////////////////////////////////////////////////////////////////胡牌結束畫面
        if(card==1){
            setIcon(icon1);

            num=(int)(Math.random()*(27-18+1))+18;//隨機抽台灣小吃牌
    
            Image im=Label.allcard.get(num);
            Icon ic=new ImageIcon(im.getScaledInstance(140,200, java.awt.Image.SCALE_SMOOTH));
  
            JLabel cardpic=new JLabel(ic);//顯示台灣小吃牌
            cardpic.setBounds(370,90,140,200);
            add(cardpic);

            okbutton=new JButton(icon4);//回主頁面按鈕
            okbutton.setOpaque(false);
            okbutton.setBorder(null);
            okbutton.setFocusPainted(false);
            okbutton.setContentAreaFilled(false);
            okbutton.setBounds(255,295,100,50);
            add(okbutton);
////////////////////////////////////////////////////////////////////////////////////////////////流局
        }else if(card==2){
            setIcon(icon2);

            againbutton=new JButton(icon5);
            againbutton.setOpaque(false);
            againbutton.setBorder(null);
            againbutton.setFocusPainted(false);
            againbutton.setContentAreaFilled(false);
            againbutton.setBounds(255,245,100,50);
            add(againbutton);

            returnbutton=new JButton(icon6);
            returnbutton.setOpaque(false);
            returnbutton.setBorder(null);
            returnbutton.setFocusPainted(false);
            returnbutton.setContentAreaFilled(false);
            returnbutton.setBounds(255,295,100,50);
            add(returnbutton);
////////////////////////////////////////////////////////////////////////////////////////輸家  
        }else if(card==3){
            setIcon(icon3);

            JButton againbutton=new JButton(icon5);
            againbutton.setOpaque(false);
            againbutton.setBorder(null);
            againbutton.setFocusPainted(false);
            againbutton.setContentAreaFilled(false);
            againbutton.setBounds(255,245,100,50);
            againbutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Game game=new Game();
                }
            });
            add(againbutton);

            JButton returnbutton=new JButton(icon6);
            returnbutton.setOpaque(false);
            returnbutton.setBorder(null);
            returnbutton.setFocusPainted(false);
            returnbutton.setContentAreaFilled(false);
            returnbutton.setBounds(255,295,100,50);
            returnbutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Game game=new Game();
                }
            });
            add(returnbutton);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
