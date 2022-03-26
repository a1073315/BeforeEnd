import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.IOException;

public class Game extends JFrame{
    // public Game game;
    private int WIDTH = 880;
    private int HEIGHT = 600;
    private PlayerPanel newpl;
    private PlayerPanel pl=new PlayerPanel();
    private Label piclabel;
    private Result resultpanel=new Result(1);
    
    private ImageIcon imageIcon1 = new ImageIcon("picture/buttonop.png");//透明牌
    private Image image1 = imageIcon1.getImage();
    private Image newimg1 = image1.getScaledInstance(36,26, java.awt.Image.SCALE_SMOOTH);

    private ImageIcon imageIcon2 = new ImageIcon("picture/buttonback1.png");//暗牌
    private Image image2 = imageIcon2.getImage();
    private Image newimg2 = image2.getScaledInstance(36,26, java.awt.Image.SCALE_SMOOTH);

    private JLabel cardlabel[] = new JLabel[16];
    private Icon cardicon[] = new Icon[16];
    private Image cardimage[] = new Image[16];

    public JPanel first;
    public JPanel cp;

    public int s=0;
    CardLayout card;
    public JLabel start,pic1,pic2;
    public JButton nextbutton,prevbutton,returnbutton2;

    public static void main(String[] args) {//主程式

        Game ga=new Game();
        ga.setVisible(true);

    }

    private class CkeckOnExit implements WindowListener{
        @Override
        public void windowOpened(WindowEvent e){}

        @Override
        public void windowClosing(WindowEvent e) {
            ConfirmWindow ch=new ConfirmWindow();
            ch.setVisible(true);
        }

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
        
    }
    private class ConfirmWindow extends JFrame implements ActionListener{
        public ConfirmWindow(){
            setSize(300,180);
            setResizable(false);
            getContentPane().setBackground(Color.BLACK);
            setLayout(null);
            java.awt.Dimension scr_size =java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            setLocation((scr_size.width - 300) / 2,(scr_size.height - 180) / 2);//視窗置中

            ImageIcon imageIcon2 = new ImageIcon("picture/yes.png"); //確定離開視窗
            Image image2 = imageIcon2.getImage(); 
            Icon icon2=new ImageIcon(image2.getScaledInstance(70,50, java.awt.Image.SCALE_SMOOTH));

            ImageIcon imageIcon3 = new ImageIcon("picture/no.png"); //確定離開視窗
            Image image3 = imageIcon3.getImage(); 
            Icon icon3=new ImageIcon(image3.getScaledInstance(70,50, java.awt.Image.SCALE_SMOOTH));

            JButton exit=new JButton(icon2);
            exit.setOpaque(false);
            exit.setBorder(null);
            exit.setFocusPainted(false);
            exit.setContentAreaFilled(false);
            exit.setBounds(60,80,70,50);
            exit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            add(exit);

            JButton cancel=new JButton(icon3);
            cancel.setOpaque(false);
            cancel.setBorder(null);
            cancel.setFocusPainted(false);
            cancel.setContentAreaFilled(false);
            cancel.setBounds(160,80,70,50);
            cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            add(cancel);

            ImageIcon imageIcon = new ImageIcon("picture/exit.png"); //確定離開視窗
            Image image = imageIcon.getImage(); 
            Icon icon=new ImageIcon(image.getScaledInstance(286,145, java.awt.Image.SCALE_SMOOTH));

            JLabel comfirmlabel=new JLabel(icon);
            comfirmlabel.setBounds(0,0,286,145);
            add(comfirmlabel);

        }
        public void actionPerformed(ActionEvent e){

        }
    }

    public Game() {//主視窗

        setTitle("Majang");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CkeckOnExit());

        setResizable(false);
        setLayout(new GridLayout(1,1));

        java.awt.Dimension scr_size =java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scr_size.width - WIDTH) / 2,(scr_size.height - HEIGHT) / 2);//視窗置中

        first= new JPanel();//最底層
        first.setBackground(Color.BLACK);
        card=new CardLayout();
        first.setLayout(card);

        ImageIcon imageIcon = new ImageIcon("picture/start.png"); //開始畫面
        Image image = imageIcon.getImage(); 
        Icon icon=new ImageIcon(image.getScaledInstance(880,530, java.awt.Image.SCALE_SMOOTH));

        start=new JLabel();
        start.setIcon(icon);
        start.setLayout(null);


        ImageIcon imageIcon2 = new ImageIcon("picture/startbutton.png"); //開始按鈕
        Icon icon2=new ImageIcon(imageIcon2.getImage());

        JButton startbutton=new JButton(icon2);
        startbutton.setOpaque(false);
        startbutton.setBorder(null);
        startbutton.setFocusPainted(false);
        startbutton.setContentAreaFilled(false);
        startbutton.setBounds(460,240,270,110);
        startbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.next(first);
            }
        });
        start.add(startbutton);

        ImageIcon imageIcon3 = new ImageIcon("picture/look.png");//圖鑑按鈕
        Icon icon3=new ImageIcon(imageIcon3.getImage());

        JButton picturebutton=new JButton(icon3);
        picturebutton.setOpaque(false);
        picturebutton.setBorder(null);
        picturebutton.setFocusPainted(false);
        picturebutton.setContentAreaFilled(false);
        picturebutton.setBounds(460,350,270,110);
        picturebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.next(first);
                card.next(first);
            }
        });
        start.add(picturebutton);

//////////////////////////////////////////////////////////////////////圖鑑1,2頁

        ImageIcon imageIcon4 = new ImageIcon("picture/book.png"); 
        Image image4 = imageIcon4.getImage(); 
        Icon icon4=new ImageIcon(image4.getScaledInstance(880,530, java.awt.Image.SCALE_SMOOTH));

        pic1=new JLabel();//圖鑑畫面1
        pic1.setBackground(Color.BLACK);
        pic1.setIcon(icon4);
        pic1.setLayout(null);
        repaintpic(1);

        pic2=new JLabel();//圖鑑畫面2
        pic2.setBackground(Color.BLACK);
        pic2.setIcon(icon4);
        pic2.setLayout(null);
        repaintpic(2);
//////////////////////////////////////////////////////////////////////////////胡牌結束畫面確定按鈕
        pl.resultpanel1.okbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{//加入圖鑑
                    piclabel.addCard(pl.resultpanel1.num);
                }catch(IOException f){}
                
                start.removeAll();

                ImageIcon imageIcon2 = new ImageIcon("picture/exitbutton.png"); //開始按鈕
                Icon icon2=new ImageIcon(imageIcon2.getImage());
        
                JButton startbutton=new JButton(icon2);
                startbutton.setOpaque(false);
                startbutton.setBorder(null);
                startbutton.setFocusPainted(false);
                startbutton.setContentAreaFilled(false);
                startbutton.setBounds(460,350,270,110);
                startbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                start.add(startbutton);
        
                ImageIcon imageIcon3 = new ImageIcon("picture/look.png");//圖鑑按鈕
                Icon icon3=new ImageIcon(imageIcon3.getImage());
        
                JButton picturebutton=new JButton(icon3);
                picturebutton.setOpaque(false);
                picturebutton.setBorder(null);
                picturebutton.setFocusPainted(false);
                picturebutton.setContentAreaFilled(false);
                picturebutton.setBounds(460,240,270,110);
                picturebutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        card.next(first);
                        card.next(first);
                    }
                });
                start.add(picturebutton);

                start.repaint();

                card.first(first);
                
                pic1.removeAll();
                repaintpic(1);
                pic2.removeAll();
                repaintpic(2);
            
            }
        });
///////////////////////////////////////////////////////////////////////////////流局結束畫面返回主頁面按鈕
        pl.resultpanel2.returnbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{//加入圖鑑
                    piclabel.addCard(pl.resultpanel1.num);
                }catch(IOException f){}

                start.removeAll();

                ImageIcon imageIcon2 = new ImageIcon("picture/exitbutton.png"); //開始按鈕
                Icon icon2=new ImageIcon(imageIcon2.getImage());
        
                JButton startbutton=new JButton(icon2);
                startbutton.setOpaque(false);
                startbutton.setBorder(null);
                startbutton.setFocusPainted(false);
                startbutton.setContentAreaFilled(false);
                startbutton.setBounds(460,350,270,110);
                startbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);;
                    }
                });
                start.add(startbutton);
        
                ImageIcon imageIcon3 = new ImageIcon("picture/look.png");//圖鑑按鈕
                Icon icon3=new ImageIcon(imageIcon3.getImage());
        
                JButton picturebutton=new JButton(icon3);
                picturebutton.setOpaque(false);
                picturebutton.setBorder(null);
                picturebutton.setFocusPainted(false);
                picturebutton.setContentAreaFilled(false);
                picturebutton.setBounds(460,240,270,110);
                picturebutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        card.next(first);
                        card.next(first);
                    }
                });
                start.add(picturebutton);

                start.repaint();
                
                card.first(first);
                
                pic1.removeAll();
                repaintpic(1);
                pic2.removeAll();
                repaintpic(2);
            
            }
        });


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        first.add(start);
        first.add(this.table(pl));
        first.add(pic1);
        first.add(pic2);
        

        add(first);


    }

    public JLabel table(JPanel jpanel){//牌桌
		JLabel table=new JLabel();

        ImageIcon imageIcon = new ImageIcon("picture/table.png"); 
        Image image = imageIcon.getImage(); 
        Image newimg = image.getScaledInstance(890,610, java.awt.Image.SCALE_SMOOTH); 
        imageIcon = new ImageIcon(newimg); 
        Icon icon=imageIcon;

        table.setIcon(icon);
        table.setLayout(new BorderLayout());
        table.add(this.computer1(), BorderLayout.WEST);
        table.add(this.computer2(), BorderLayout.NORTH);
        table.add(this.computer3(), BorderLayout.EAST);
        table.add(jpanel, BorderLayout.CENTER);

        return table;
    }


    public JPanel computer1() {//電腦玩家1

        JPanel computer1 = new JPanel();
        computer1.setOpaque(false);
        computer1.setLayout(new GridBagLayout());

        GridBagConstraints gc1 = new GridBagConstraints();

        gc1.gridx        = 0;
        gc1.gridy        = 0;
        gc1.gridwidth    = 1;
        gc1.gridheight   = 1;
        gc1.anchor       = gc1.CENTER;
        gc1.fill         = gc1.BOTH;

        imageIcon1 = new ImageIcon(newimg1); 
        Icon icon1=imageIcon1;

        imageIcon2 = new ImageIcon(newimg2); 
        Icon icon2=imageIcon2;

        for(int i=1;i<=16;i++){

            JButton op = new JButton(icon1);
            op.setOpaque(false);
            op.setBorder(null);
            op.setContentAreaFilled(false);
            gc1.gridy=i+1;
            computer1.add(op,gc1);

            JButton op2 = new JButton(icon1);
            op2.setOpaque(false);
            op2.setBorder(null);
            op2.setContentAreaFilled(false);
            gc1.gridx=1;
            gc1.gridy=i+1;
            computer1.add(op2,gc1);

            JButton back = new JButton(icon2);
            back.setOpaque(false);
            back.setBorder(null);
            back.setContentAreaFilled(false);
            gc1.gridx=2;
            gc1.gridy=i-1;
            computer1.add(back,gc1);


        }

        ImageIcon imageIcon = new ImageIcon("picture/computer1.png");//電腦玩家1頭像
        Image image = imageIcon.getImage(); 
        Icon icon=new ImageIcon(image.getScaledInstance(68,68, java.awt.Image.SCALE_SMOOTH));

        JLabel com=new JLabel();
        com.setIcon(icon);
        gc1.gridx=0;
        gc1.gridy=5;
        gc1.gridwidth    = 2;
        gc1.gridheight   = 3;
        computer1.add(com,gc1);

        return computer1;
    }

    public JPanel computer2() {//電腦玩家2
        JPanel computer2 = new JPanel();
        computer2.setOpaque(false);
        computer2.setLayout(new GridBagLayout());

        GridBagConstraints gc2 = new GridBagConstraints();

        gc2.gridx        = 0;
        gc2.gridy        = 0;
        gc2.gridwidth    = 1;
        gc2.gridheight   = 1;
        gc2.anchor       = gc2.CENTER;
        gc2.fill         = gc2.BOTH;

        Image newimg1 = image1.getScaledInstance(30,40, java.awt.Image.SCALE_SMOOTH);
        imageIcon1 = new ImageIcon(newimg1); 
        Icon icon1=imageIcon1;

        Image newimg2 = image2.getScaledInstance(30,40, java.awt.Image.SCALE_SMOOTH);
        imageIcon2 = new ImageIcon(newimg2); 
        Icon icon2=imageIcon2;

        for(int i=0;i<=15;i++){

            JButton back = new JButton(icon2);
            back.setOpaque(false);
            back.setBorder(null);
            back.setContentAreaFilled(false);
            gc2.gridx=i;
            gc2.gridy=2;
            computer2.add(back,gc2);

        }

        ImageIcon imageIcon = new ImageIcon("picture/computer2.png"); //電腦玩家2頭像
        Image image = imageIcon.getImage(); 
        Icon icon=new ImageIcon(image.getScaledInstance(50,50, java.awt.Image.SCALE_SMOOTH));

        JLabel com=new JLabel();
        com.setIcon(icon);
        gc2.gridx=7;
        gc2.gridy=0;
        gc2.gridwidth    = 2;
        computer2.add(com,gc2);

        return computer2;
    }

    public JPanel computer3() {//電腦玩家3
       JPanel computer3 = new JPanel();
        computer3.setOpaque(false);
        computer3.setLayout(new GridBagLayout());

        GridBagConstraints gc3 = new GridBagConstraints();

        gc3.gridx        = 0;
        gc3.gridy        = 0;
        gc3.gridwidth    = 1;
        gc3.gridheight   = 1;
        gc3.anchor       = gc3.CENTER;
        gc3.fill         = gc3.BOTH;

        imageIcon1 = new ImageIcon(newimg1); 
        Icon icon1=imageIcon1;

        imageIcon2 = new ImageIcon(newimg2); 
        Icon icon2=imageIcon2;


        for(int i=1;i<=16;i++){

            JButton back = new JButton(icon2);
            back.setOpaque(false);
            back.setBorder(null);
            back.setContentAreaFilled(false);
            gc3.gridx=0;
            gc3.gridy=i-1;
            computer3.add(back,gc3);

            JButton op = new JButton(icon1);
            op.setOpaque(false);
            op.setBorder(null);
            op.setContentAreaFilled(false);
            gc3.gridx=1;
            gc3.gridy=i+1;
            computer3.add(op,gc3);

            JButton op2= new JButton(icon1);
            op2.setOpaque(false);
            op2.setBorder(null);
            op2.setContentAreaFilled(false);
            gc3.gridx=2;
            gc3.gridy=i+1;
            computer3.add(op2,gc3);
        }
        
        ImageIcon imageIcon = new ImageIcon("picture/computer3.png"); //電腦玩家3頭像
        Image image = imageIcon.getImage(); 
        Icon icon=new ImageIcon(image.getScaledInstance(68,68, java.awt.Image.SCALE_SMOOTH));

        JLabel com=new JLabel();
        com.setIcon(icon);
        gc3.gridx=1;
        gc3.gridy=5;
        gc3.gridwidth    = 2;
        gc3.gridheight   = 3;
        computer3.add(com,gc3);

        return computer3;
    }
    public void repaintpic(int p){

        ImageIcon imageIcon5 = new ImageIcon("picture/returnbutton.png");//返回主頁按鈕
        Image image5 = imageIcon5.getImage(); 
        Icon icon5=new ImageIcon(image5.getScaledInstance(100,45, java.awt.Image.SCALE_SMOOTH));

        ImageIcon imageIcon6 = new ImageIcon("picture/next.png");//返回主頁按鈕
        Image image6 = imageIcon6.getImage(); 
        Icon icon6=new ImageIcon(image6.getScaledInstance(80,45, java.awt.Image.SCALE_SMOOTH));

        ImageIcon imageIcon7 = new ImageIcon("picture/prev.png");//返回主頁按鈕
        Image image7 = imageIcon7.getImage(); 
        Icon icon7=new ImageIcon(image7.getScaledInstance(80,45, java.awt.Image.SCALE_SMOOTH));

        ImageIcon qimage = new ImageIcon("card/0.png"); 
        Image qimage1 = qimage.getImage(); 
        Icon qimage2=new ImageIcon(qimage1);

        try{
            this.piclabel=new Label();
            piclabel.getCard(Label.card);
        }catch (IOException e1) {
            System.out.println("file does not exists");
        }


        if(p==1){
    
            for(int i=0;i<8;i++){
                if(Label.card.get(i)==null){
                    cardimage[i]=qimage1;
                    cardicon[i]=new ImageIcon(cardimage[i].getScaledInstance(130,180, java.awt.Image.SCALE_SMOOTH));
                }else{
                    cardimage[i]=Label.card.get(i);
                    cardicon[i]=new ImageIcon(cardimage[i].getScaledInstance(130,180, java.awt.Image.SCALE_SMOOTH));
                }
    
                if(i<2){
                    cardlabel[i]=new JLabel();
                    cardlabel[i].setIcon(cardicon[i]);
                    cardlabel[i].setBounds(110+i*150,90,130,180);
                    pic1.add(cardlabel[i]);
                }else if(i>1 && i<4){
                    cardlabel[i]=new JLabel();
                    cardlabel[i].setIcon(cardicon[i]);
                    cardlabel[i].setBounds(110+i*150-300,290,130,180);
                    pic1.add(cardlabel[i]);
                }else if(i>3 && i<6){
                    cardlabel[i]=new JLabel();
                    cardlabel[i].setIcon(cardicon[i]);
                    cardlabel[i].setBounds(i*150-110,90,130,180);
                    pic1.add(cardlabel[i]);
                }else if(i>5 && i<8){
                    cardlabel[i]=new JLabel();
                    cardlabel[i].setIcon(cardicon[i]);
                    cardlabel[i].setBounds(i*150-300-110,290,130,180);
                    pic1.add(cardlabel[i]);
                }
                
            }
    
    
            nextbutton=new JButton(icon6);
            nextbutton.setOpaque(false);
            nextbutton.setBorder(null);
            nextbutton.setFocusPainted(false);
            nextbutton.setContentAreaFilled(false);
            nextbutton.setBounds(560,480,80,45);
            nextbutton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    card.next(first);
                }
            });
            pic1.add(nextbutton);
    
            JButton returnbutton=new JButton(icon5);
            returnbutton.setOpaque(false);
            returnbutton.setBorder(null);
            returnbutton.setFocusPainted(false);
            returnbutton.setContentAreaFilled(false);
            returnbutton.setBounds(670,480,100,45);
            returnbutton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    card.first(first);
                }
            });
            pic1.add(returnbutton);
        }else if(p==2){
    
            for(int i=8;i<16;i++){
                if(Label.card.get(i)==null){
                    cardimage[i]=qimage1;
                    cardicon[i]=new ImageIcon(cardimage[i].getScaledInstance(130,180, java.awt.Image.SCALE_SMOOTH));
                }else{
                    cardimage[i]=Label.card.get(i);//手牌圖片
                    cardicon[i]=new ImageIcon(cardimage[i].getScaledInstance(130,180, java.awt.Image.SCALE_SMOOTH));
                }
    
                if(i<10){
                    cardlabel[i]=new JLabel();
                    cardlabel[i].setIcon(cardicon[i]);
                    cardlabel[i].setBounds(110+(i-8)*150,90,130,180);
                    pic2.add(cardlabel[i]);
                }else if(i>9 && i<12){
                    cardlabel[i]=new JLabel();
                    cardlabel[i].setIcon(cardicon[i]);
                    cardlabel[i].setBounds(110+(i-8)*150-300,290,130,180);
                    pic2.add(cardlabel[i]);
                }else if(i>11 && i<14){
                    cardlabel[i]=new JLabel();
                    cardlabel[i].setIcon(cardicon[i]);
                    cardlabel[i].setBounds((i-8)*150-110,90,130,180);
                    pic2.add(cardlabel[i]);
                }else if(i>13 && i<16){
                    cardlabel[i]=new JLabel();
                    cardlabel[i].setIcon(cardicon[i]);
                    cardlabel[i].setBounds((i-8)*150-300-110,290,130,180);
                    pic2.add(cardlabel[i]);
                }
                
            }
    
            prevbutton=new JButton(icon7);
            prevbutton.setOpaque(false);
            prevbutton.setBorder(null);
            prevbutton.setFocusPainted(false);
            prevbutton.setContentAreaFilled(false);
            prevbutton.setBounds(560,480,80,45);
            prevbutton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    card.previous(first);
                }
            });
            pic2.add(prevbutton);
    
            returnbutton2=new JButton(icon5);
            returnbutton2.setOpaque(false);
            returnbutton2.setBorder(null);
            returnbutton2.setFocusPainted(false);
            returnbutton2.setContentAreaFilled(false);
            returnbutton2.setBounds(670,480,100,45);
            returnbutton2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    card.first(first);
                }
            });
            pic2.add(returnbutton2);
        }
        repaint();
    }

}
