import java.util.*;
import java.util.List;
import java.util.Timer;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.awt.Image;
import java.awt.*;

public class PlayerPanel extends JPanel implements ActionListener{


    public Label piclabel;
    private int id;
    private Boolean onclick=false;
    private Boolean re=false;

    
    public Shuffle shuffle=new Shuffle();
    public Player p1=new Player(shuffle);
    public Player computer1 = new Player(shuffle);
    public Player computer2 = new Player(shuffle);
    public Player computer3 = new Player(shuffle);

    public ArrayList<Integer> pool=shuffle.getpool();//抓取shuffle.pool()
    public ArrayList<Integer> enpool=new ArrayList<>(30);//pool吃或碰過的牌


    public JButton b[];//玩家手牌的button
    public Image image[];//玩家手牌的圖片
    public Icon ic[];//玩家手牌的圖片

    public JButton bn[]=new JButton[80];//牌池中牌的button
    public Image poolimage[]=new Image[80];//玩家手牌的圖片
    public Icon poolic[]=new Icon[80];//玩家手牌的圖片

    public JButton bd[];//玩家吃或碰所有的button
    public Image imaged[];//玩家吃或碰所有的圖片
    public Icon icd[];//玩家吃或碰所有的圖片

    public JButton eat,touch;
    public JLabel c1,c2,c3,p;
///////////////////////////////////////////////
    public PumpCard pumpcard;
    public int pumpnum;
//////////////////////////////////////////////

    public static int check=0;
    public static int poolindex=0;
    public int test=0;

    public Result resultpanel1=new Result(1);
    public Result resultpanel2=new Result(2);
    public Result resultpanel3=new Result(3);


    public PlayerPanel(){

        this.piclabel=new Label();
        piclabel.getPic(Label.allpic);

        setOpaque(false);
        setLayout(null);

        for (int i = 1; i <=16; i++) {
            p1.Hit(shuffle.index);
            computer1.Hit(shuffle.index);
            computer2.Hit(shuffle.index);
            computer3.Hit(shuffle.index);
        }


        // handcard=p1.arrangehandcard(handcard);      //將玩家的牌按照順序排好


        /*摸一張牌 */
        p1.Hit(shuffle.index);
        
        check=1;
        // System.out.println(shuffle.majanglist.size());

        image=new Image[p1.handcard.size()];
        ic=new Icon[p1.handcard.size()];
        b=new JButton[p1.handcard.size()];
        
        for(int i=0;i<p1.handcard.size();i++){
            image[i]=Label.allpic.get(p1.handcard.get(i));//手牌圖片
            ic[i]=new ImageIcon(image[i].getScaledInstance(33,45, java.awt.Image.SCALE_SMOOTH));
        }

        for(int i=0;i<p1.handcard.size()-1;i++){//玩家手牌
            b[i]=new JButton(ic[i]);
            b[i].setOpaque(false);
            b[i].setBorder(null);
            b[i].setContentAreaFilled(false);
            b[i].addActionListener(this);
            b[i].setActionCommand(Integer.toString(i));
            b[i].setBounds(20+i*34,405,33,45);
            add(b[i]);
        }

        b[p1.handcard.size()-1] = new JButton(ic[p1.handcard.size()-1]);//隨機抽的牌顯示在這個button
        b[p1.handcard.size()-1].setOpaque(false);
        b[p1.handcard.size()-1].setBorder(null);
        b[p1.handcard.size()-1].setContentAreaFilled(false);
        b[p1.handcard.size()-1].addActionListener(this);
        b[p1.handcard.size()-1].setActionCommand(Integer.toString(p1.handcard.size()-1));
        b[p1.handcard.size()-1].setBounds(20+17*34,405,33,45);
        add(b[p1.handcard.size()-1]);
        ///////////////////////////////////////////////////////////////////////////////////////////////
        ImageIcon imagep = new ImageIcon("picture/p.png"); 
        Image pimage = imagep.getImage(); 
        Icon iconp=new ImageIcon(pimage.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH));

        p=new JLabel(iconp);
        p.setBounds(310,355,40,40);
        add(p);

        ImageIcon imagec1 = new ImageIcon("picture/c1.png"); 
        Image c1image = imagec1.getImage(); 
        Icon iconc1=new ImageIcon(c1image.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH));

        c1=new JLabel(iconc1);
        c1.setBounds(10,185,40,40);
        c1.setVisible(false);
        add(c1);

        ImageIcon imagec2 = new ImageIcon("picture/c2.png"); 
        Image c2image = imagec2.getImage(); 
        Icon iconc2=new ImageIcon(c2image.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH));

        c2=new JLabel(iconc2);
        c2.setBounds(310,10,40,40);
        c2.setVisible(false);
        add(c2);

        ImageIcon imagec3 = new ImageIcon("picture/c3.png"); 
        Image c3image = imagec3.getImage(); 
        Icon iconc3=new ImageIcon(c3image.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH));

        c3=new JLabel(iconc3);
        c3.setBounds(600,185,40,40);
        c3.setVisible(false);
        add(c3);

    }

    public void actionPerformed(ActionEvent e){
        String buttonString=e.getActionCommand();

        for(int i=0;i<p1.handcard.size();i++){
            if(buttonString.equals(Integer.toString(i)) && (!getOnclick())){
                b[i].setVisible(false);

                p1.Abandon(i);
                
                setOnclick(true);//讓玩家不可出下一張牌

                Work w1=new Work(2,this,p1,computer1,computer2,computer3,shuffle);

                Thread t1 = new Thread(w1);
                t1.start();

            }
        }

    }

    public void repaintpanel(int pnum){
        removeAll();
        
        int getcard=0;
        
        if(pnum==1){

            image=new Image[p1.handcard.size()];
            ic=new Icon[p1.handcard.size()];
            b=new JButton[p1.handcard.size()];

            for(int i=0;i<p1.handcard.size();i++){
                image[i]=Label.allpic.get(p1.handcard.get(i));//手牌圖片
                ic[i]=new ImageIcon(image[i].getScaledInstance(33,45, java.awt.Image.SCALE_SMOOTH));
                // System.out.print(Integer.parseInt(p1.handcard.get(i).toString())+"/");
            }

            for(int k=0;k<p1.handcard.size()-1;k++){//玩家手牌
                b[k]=new JButton(ic[k]);
                b[k].setOpaque(false);
                b[k].setBorder(null);
                b[k].setContentAreaFilled(false);
                b[k].addActionListener(this);
                b[k].setActionCommand(Integer.toString(k));
                b[k].setBounds(20+k*34,405,33,45);
                add(b[k]);
            }
    
            b[p1.handcard.size()-1] = new JButton(ic[p1.handcard.size()-1]);//隨機抽的牌顯示在這個button
            b[p1.handcard.size()-1].setOpaque(false);
            b[p1.handcard.size()-1].setBorder(null);
            b[p1.handcard.size()-1].setContentAreaFilled(false);
            b[p1.handcard.size()-1].addActionListener(this);
            b[p1.handcard.size()-1].setActionCommand(Integer.toString(p1.handcard.size()-1));
            b[p1.handcard.size()-1].setBounds(20+(p1.handcard.size())*34,405,33,45);
            add(b[p1.handcard.size()-1]);

        }else{
            image=new Image[p1.handcard.size()];
            ic=new Icon[p1.handcard.size()];
            b=new JButton[p1.handcard.size()];

            for(int i=0;i<p1.handcard.size();i++){
                image[i]=Label.allpic.get(p1.handcard.get(i));//手牌圖片
                ic[i]=new ImageIcon(image[i].getScaledInstance(33,45, java.awt.Image.SCALE_SMOOTH));
                // System.out.print(Integer.parseInt(p1.handcard.get(i).toString())+"/");
            }

            for(int k=0;k<p1.handcard.size();k++){//玩家手牌
                b[k]=new JButton(ic[k]);
                b[k].setOpaque(false);
                b[k].setBorder(null);
                b[k].setContentAreaFilled(false);
                b[k].addActionListener(this);
                b[k].setActionCommand(Integer.toString(k));
                b[k].setBounds(20+k*34,405,33,45);
                add(b[k]);
            }
        }
//////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////結束頁面
        resultpanel1.setBounds(20,0,600,400);
        resultpanel1.setVisible(false);
        add(resultpanel1);

        resultpanel2.setBounds(20,0,600,400);
        resultpanel2.setVisible(false);
        add(resultpanel2);

        resultpanel3.setBounds(20,0,600,400);
        resultpanel3.setVisible(false);
        add(resultpanel3);
///////////////////////////////////////////////////////////////////////////////////////////////////////////  

////////////////////////////////////////////////////////////////////////////////////////////吃、碰按鈕
        ImageIcon imageeat = new ImageIcon("picture/eat.png"); 
        Image eatimage = imageeat.getImage(); 
        Icon iconeat=new ImageIcon(eatimage.getScaledInstance(80,80, java.awt.Image.SCALE_SMOOTH));

        eat=new JButton(iconeat);
        eat.setVisible(false);
        eat.setOpaque(false);
        eat.setBorder(null);
        eat.setContentAreaFilled(false);
        eat.setBounds(250,330,80,80);
        add(eat);

        ImageIcon imagetouch = new ImageIcon("picture/touch.png"); 
        Image touchimage = imagetouch.getImage(); 
        Icon icontouch=new ImageIcon(touchimage.getScaledInstance(80,80, java.awt.Image.SCALE_SMOOTH));

        touch=new JButton(icontouch);
        touch.setVisible(false);
        touch.setOpaque(false);
        touch.setBorder(null);
        touch.setContentAreaFilled(false);
        touch.setBounds(320,330,80,80);
        add(touch);
///////////////////////////////////////////////////////////////////////////////////////////
        
//////////////////////////////////////////////////////////////////////////////////////////顯示吃、碰後SDGS圖卡
        pumpcard=new PumpCard();
        pumpcard.setBounds(180,15,275,380);
        pumpcard.setVisible(false);
        add(pumpcard);
//////////////////////////////////////////////////////////////////////////////////////////////////////// 

//////////////////////////////////////////////////////////////////////////////////////////////////玩家箭頭
        ImageIcon imagep = new ImageIcon("picture/p.png"); 
        Image pimage = imagep.getImage(); 
        Icon iconp=new ImageIcon(pimage.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH));
        
        p=new JLabel(iconp);
        p.setBounds(310,355,40,40);
        p.setVisible(false);
        add(p);
        
        ImageIcon imagec1 = new ImageIcon("picture/c1.png"); 
        Image c1image = imagec1.getImage(); 
        Icon iconc1=new ImageIcon(c1image.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH));
        
        c1=new JLabel(iconc1);
        c1.setBounds(10,185,40,40);
        c1.setVisible(false);
        add(c1);
        
        ImageIcon imagec2 = new ImageIcon("picture/c2.png"); 
        Image c2image = imagec2.getImage(); 
        Icon iconc2=new ImageIcon(c2image.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH));
        
        c2=new JLabel(iconc2);
        c2.setBounds(310,10,40,40);
        c2.setVisible(false);
        add(c2);
        
        ImageIcon imagec3 = new ImageIcon("picture/c3.png"); 
        Image c3image = imagec3.getImage(); 
        Icon iconc3=new ImageIcon(c3image.getScaledInstance(40,40, java.awt.Image.SCALE_SMOOTH));
        
        c3=new JLabel(iconc3);
        c3.setBounds(600,185,40,40);
        c3.setVisible(false);
        add(c3);
///////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////將pool中的牌印在中間
        for(int m=0;m<pool.size();m++){
            poolimage[m]=Label.allpic.get(shuffle.getpoolcard(m));//牌池中牌的圖片
            // System.out.println(shuffle.getpoolcard(m));
            poolic[m]=new ImageIcon(poolimage[m].getScaledInstance(33,45, java.awt.Image.SCALE_SMOOTH));
        }

        for(int j=0;j<pool.size();j++){//牌池中的牌
            if(j<=15){
                bn[j] = new JButton(poolic[j]);
                bn[j].setOpaque(false);
                bn[j].setBorder(null);
                bn[j].setContentAreaFilled(false);
                bn[j].setBounds(54+j*34,60,33,45);
                add(bn[j]);

            }else if(j>15 && j<=31){
                bn[j] = new JButton(poolic[j]);
                bn[j].setOpaque(false);
                bn[j].setBorder(null);
                bn[j].setContentAreaFilled(false);
                bn[j].setBounds(54+(j-16)*34,120,33,45);
                add(bn[j]);

            }else if(j>31 && j<=47){
                bn[j] = new JButton(poolic[j]);
                bn[j].setOpaque(false);
                bn[j].setBorder(null);
                bn[j].setContentAreaFilled(false);
                bn[j].setBounds(54+(j-32)*34,180,33,45);
                add(bn[j]);

            }else if(j>47 && j<=63){
                bn[j] = new JButton(poolic[j]);
                bn[j].setOpaque(false);
                bn[j].setBorder(null);
                bn[j].setContentAreaFilled(false);
                bn[j].setBounds(54+(j-48)*34,240,33,45);
                add(bn[j]);

            }else if(j>63 && j<=79){
                bn[j] = new JButton(poolic[j]);
                bn[j].setOpaque(false);
                bn[j].setBorder(null);
                bn[j].setContentAreaFilled(false);
                bn[j].setBounds(54+(j-64)*34,300,33,45);
                add(bn[j]);

            }
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////牌池中被吃或碰的牌
        if(enpool.size()!=0){
            for(int en=0;en<enpool.size();en++){
                bn[enpool.get(en)].setEnabled(false);
            }
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////玩家吃或碰過的牌
        if(p1.donecard.size()!=0){
            for(int d=0;d<p1.donecard.size();d++){
                imaged=new Image[p1.donecard.size()];
                icd=new Icon[p1.donecard.size()];
                bd=new JButton[p1.donecard.size()];
    
                for(int i=0;i<p1.donecard.size();i++){
                    imaged[i]=Label.allpic.get(p1.donecard.get(i));//手牌圖片
                    icd[i]=new ImageIcon(imaged[i].getScaledInstance(33,45, java.awt.Image.SCALE_SMOOTH));
                }
    
                for(int k=0;k<p1.donecard.size();k++){//玩家手牌
                    bd[k]=new JButton(icd[k]);
                    bd[k].setOpaque(false);
                    bd[k].setBorder(null);
                    bd[k].setEnabled(false);
                    bd[k].setContentAreaFilled(false);
                    bd[k].setBounds(20+k*34,355,33,45);
                    add(bd[k]);
                }
            }
        }
//////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////
        repaint();

        setRe(true);
    }


    public int getCardid(){
        return this.id;
    }

    public void setCardid(int id){
        this.id=id;
    }

    public boolean getOnclick(){
        return this.onclick;
    }

    public void setOnclick(boolean onclick){
        this.onclick=onclick;
    }

    public boolean getRe(){
        return this.re;
    }

    public void setRe(boolean re){
        this.re=re;
    }

}
    