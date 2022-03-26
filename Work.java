import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.IOException;

public class Work implements Runnable{
    private PlayerPanel playerpanel;
    private int a;
    private Player p1;
    private Player computer1;
    private Player computer2;
    private Player computer3;
    private Shuffle shuffle;
    public int i=1;
    public int nohit=0;

    public Boolean win=false;

    
    public Work(int a,PlayerPanel playerpanel,Player p1,Player computer1,Player computer2,Player computer3,Shuffle shuffle){
        this.playerpanel=playerpanel;
        this.a=a;
        this.p1=p1;
        this.computer1=computer1;
        this.computer2=computer2;
        this.computer3=computer3;
        this.shuffle=shuffle;
    }

    @Override
    public void run() {

        int b=0;
        int getcard=0;
        playerpanel.setRe(false);
        ImageIcon imageIcon = new ImageIcon("picture/op.png"); //開始畫面
        Image image = imageIcon.getImage(); 
        Icon icon=new ImageIcon(image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH));
        
        while(i<=10 && !getwin()){
            if(i==2){
                playerpanel.repaintpanel(2); 
                playerpanel.p.setVisible(true);
                doNothing(200);
            }else if(i==3){
                playerpanel.repaintpanel(2); 
                playerpanel.c3.setVisible(true);
                doNothing(200);
            }else if(i==4){
                playerpanel.computer1.Hit(playerpanel.shuffle.index);
                getcard=Integer.parseInt(playerpanel.computer1.gethandcard(16).toString());
                playerpanel.computer1.handcard.add(16,getcard);
                playerpanel.computer1.Abandon(16);
                playerpanel.repaintpanel(3);
                playerpanel.c3.setVisible(true);
                
                if(p1.Touch(shuffle.getlastpoolcard())==1){
                    playerpanel.touch.setVisible(true);
                    playerpanel.touch.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            playerpanel.touch.setVisible(false);
                            playerpanel.bn[playerpanel.pool.size()-1].setEnabled(false);
                            playerpanel.enpool.add(playerpanel.pool.size()-1);
                            p1.addtouchedcard();
                            i=9;
                            nohit=1;
                            playerpanel.c3.setVisible(false);
                            if(p1.checkwin()==1){
                                playerpanel.resultpanel1.setVisible(true);
                                setwin(true);
                            }else{
                                playerpanel.pumpcard.setVisible(true);
                            }
                        }
                    });
                    try{
                        Thread.sleep(3000);
                    }catch(InterruptedException e){}
                    
                }
                if(playerpanel.pool.size()==72){
                    playerpanel.resultpanel2.setVisible(true);
                    setwin(true);
                }
                doNothing(1000);
                playerpanel.pumpcard.setVisible(false);

            }else if(i==5){
                playerpanel.repaintpanel(2); 
                playerpanel.c2.setVisible(true);
                doNothing(200);
            }else if(i==6){

                playerpanel.computer2.Hit(playerpanel.shuffle.index);
                getcard=Integer.parseInt(playerpanel.computer2.gethandcard(16).toString());
                playerpanel.computer2.handcard.add(16,getcard);
                playerpanel.computer2.Abandon(16);
                playerpanel.repaintpanel(4);
                playerpanel.c2.setVisible(true);
                
                if(p1.Touch(shuffle.getlastpoolcard())==1){
                    playerpanel.touch.setVisible(true);
                    playerpanel.touch.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            playerpanel.touch.setVisible(false);
                            playerpanel.bn[playerpanel.pool.size()-1].setEnabled(false);
                            playerpanel.enpool.add(playerpanel.pool.size()-1);
                            playerpanel.c2.setVisible(false);
                            p1.addtouchedcard();
                            i=9;
                            nohit=1;
                            if(p1.checkwin()==1){
                                playerpanel.resultpanel1.setVisible(true);
                                setwin(true);
                            }else{
                                playerpanel.pumpcard.setVisible(true);
                            }
                        }
                    });
                    try{
                        Thread.sleep(3000);
                    }catch(InterruptedException e){}
                    
                }
                if(playerpanel.pool.size()==72){
                    playerpanel.resultpanel2.setVisible(true);
                    setwin(true);
                }
                doNothing(1000);
                playerpanel.pumpcard.setVisible(false);


            }else if(i==7){
                playerpanel.repaintpanel(2); 
                playerpanel.c1.setVisible(true);
                doNothing(200);
            }else if(i==8){

                playerpanel.computer3.Hit(playerpanel.shuffle.index);
                getcard=Integer.parseInt(playerpanel.computer3.gethandcard(16).toString());
                playerpanel.computer3.handcard.add(16,getcard);
                playerpanel.computer3.Abandon(16);
                playerpanel.repaintpanel(2);
                playerpanel.c1.setVisible(true);

                if(p1.Touch(shuffle.getlastpoolcard())==1){
                    playerpanel.touch.setVisible(true);
                    playerpanel.touch.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            playerpanel.touch.setVisible(false);
                            playerpanel.bn[playerpanel.pool.size()-1].setEnabled(false);
                            playerpanel.enpool.add(playerpanel.pool.size()-1);
                            p1.addtouchedcard();
                            nohit=1;
                            if(p1.checkwin()==1){
                                playerpanel.resultpanel1.setVisible(true);
                                setwin(true);
                            }else{
                                playerpanel.pumpcard.setVisible(true);
                            }
                        }
                    });
                    try{
                        Thread.sleep(3000);
                    }catch(InterruptedException e){}
                }else if(p1.Eat(shuffle.getlastpoolcard())==1){
                    playerpanel.eat.setVisible(true);
                    playerpanel.eat.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            playerpanel.eat.setVisible(false);
                            playerpanel.bn[playerpanel.pool.size()-1].setEnabled(false);
                            playerpanel.enpool.add(playerpanel.pool.size()-1);
                            p1.addeatencard();
                            nohit=1;
                            if(p1.checkwin()==1){
                                playerpanel.resultpanel1.setVisible(true);
                                setwin(true);
                            }else{
                                playerpanel.pumpcard.setVisible(true);
                            }
                        }
                    });
                    try{
                        Thread.sleep(3000);
                    }catch(InterruptedException e){}
                }
                if(playerpanel.pool.size()==72){
                    playerpanel.resultpanel2.setVisible(true);
                    setwin(true);
                }
                doNothing(1000);
                playerpanel.pumpcard.setVisible(false);
            }else if(i==9){
                playerpanel.repaintpanel(2);
                doNothing(200);
            }else if(i==10){
                if(nohit==0){
                    p1.Hit(shuffle.index);
                    playerpanel.repaintpanel(1);
                }else{
                    playerpanel.repaintpanel(2);
                }
                
                nohit=0;
                doNothing(500);
            }
            i++;
        }
        playerpanel.setOnclick(false);
        playerpanel.p.setVisible(true);
        

    }
    private static void doNothing(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Unexpected interruption");
            System.exit(0);
        }
    }
    public boolean getwin(){
        return this.win;
    }

    public void setwin(boolean win){
        this.win=win;
    }
}
