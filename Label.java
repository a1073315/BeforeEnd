import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Label {

    public static HashMap<Integer,Image> allpic = new HashMap<Integer,Image>();
    public static HashMap<Integer,Image> allcard = new HashMap<Integer,Image>();
    public static HashMap<Integer,Image> card = new HashMap<Integer,Image>();

    public ArrayList<Integer> cardtext = new ArrayList<Integer>();

    public void getPic(HashMap<Integer, Image> allpic){//////////////////////////////////存取所有牌的圖片

        for(int i=1;i<=34;i++){
            Image img = new ImageIcon("picture/"+i+".png").getImage();
            allpic.put(i,img);

        }
    }
    public void getAllcard(HashMap<Integer, Image> allcard){/////////////////////////存取所有圖卡的圖片

        for(int i=1;i<=27;i++){
            Image img = new ImageIcon("card/"+i+".png").getImage();
            allcard.put(i,img);

        }
    }
    public void getCard(HashMap<Integer, Image> card) throws IOException{////////////////取得圖鑑中圖卡的圖片
        BufferedReader br = new BufferedReader(new FileReader("card.txt"));

        String line;

        while((line = br.readLine()) != null) {

            int a=Integer.parseInt(line);
            if(a!=0 && a<28){
                cardtext.add(a);
            }
            
        }

        br.close();	

        for(int i=0;i<cardtext.size();i++){
            Image img = new ImageIcon("card/"+cardtext.get(i)+".png").getImage();
            card.put(i,img);
        }
    }
    public void addCard(int cardnum) throws IOException{///////////////////////////////////////儲存抽到的圖卡
        BufferedReader br = new BufferedReader(new FileReader("card.txt"));
        FileWriter fr=new FileWriter("card.txt",true);

        String line;
        int re=0;

        while((line = br.readLine()) != null) {

            int lin=Integer.parseInt(line);
            if(cardnum==lin){
                re=1;
            }
        }
        if(re==0){
            fr.write(Integer.toString(cardnum));
            fr.write("\n");
        }
        fr.close();
        br.close();
    }


    public HashMap getallpic(){
    	return this.allpic;
    }
    public HashMap getcard(){
    	return this.card;
    }


}