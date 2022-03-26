import java.util.*;
import java.util.ArrayList;

public class Player {
    public String name;
    public Majang majang=new Majang();
    public List handcard=new ArrayList<>(17);
    private List eatencard=new ArrayList<>(17);
    private List touchedcard=new ArrayList<>(17);
    public List donecard=new ArrayList<>(8);
    private int handcardindex=0;
    private int donecardindex=0;
    public  Shuffle shuffle;
    public Object removedMajang;
    private int check=0;
    public int eatencard1=0;
    public int eatencard2=0;
    public Player(Shuffle shuffle){
        this.shuffle=shuffle;
    }
    public void Hit(int index){
        // System.out.println(handcardindex);
        handcard.add(handcardindex,shuffle.getMajang(index));
        this.handcardindex++;
        shuffle.removeMajang(index);
        shuffle.indexminus();
    }
    public void Abandon(int index){
        if((index>handcard.size())||index<0){
            System.out.println("出錯啦");
        }else{
            int temp=Integer.parseInt(handcard.get(index).toString());
            shuffle.addpool(temp);
            shuffle.poolindexplus();
            removedMajang=handcard.get(index);
            // for(int i=0;i<handcard.size();i++){
            //     if(handcard.get(i)==removedMajang) {
            //         handcard.remove(i);
            //         this.handcardindex--;
            //     }
            // }
            handcard.remove(index);
            handcardindex--;
            /*return handcard.get(index);*/
            // panel.repaint();
        }
        
    }
    public Object gethandcard(int index){
        // if(check==0){
        //     handcard=arrangehandcard(handcard);
        //     check=1;
        // }
        return handcard.get(index);
    }
    public int gethandcardindex(){
        return handcardindex;
    }
    public int Touch(int card){
        int check=0;
        /*從牌堆裡拿到剛打出的牌*/
        int riverLastJiang = shuffle.pool.get(shuffle.getpoolindex()-1);

        // int frequency = Collections.frequency(handcard, riverLastJiang);
        int frequency=0;
        int playercard=0;
        for(int i=0;i<handcard.size();i++){
            playercard=Integer.parseInt(handcard.get(i).toString());
            if(playercard==riverLastJiang){
                frequency++;
            }
            if (frequency==2) {
                // System.out.println(riverLastJiang);
                // System.out.println("碰牌");
                // touchedcard.add(riverLastJiang);
                // touchedcard.add(riverLastJiang);
                // touchedcard.add(riverLastJiang);
                // handcard.remove(riverLastJiang);        /*把碰掉的牌移除*/
                // shuffle.pool.remove(shuffle.poolIndex-1);
                check=1;
                return check;
            }
        }
        
        return check;
    }

    public int Eat(int card){
        int check=0;
        int eatable=0;
        int riverLastJiang = shuffle.pool.get(shuffle.getpoolindex()-1);
        if(riverLastJiang>=5&&riverLastJiang<=13){
            for(int a=0;a<handcard.size();a++){
                int y=Integer.parseInt(gethandcard(a).toString());
                if(y!=riverLastJiang){
                    if(y==riverLastJiang-1&&checkrangeword(y)==true){
                        for(int b=0;b<handcard.size();b++){
                            int x=Integer.parseInt(gethandcard(b).toString());
                            if(x!=riverLastJiang&&x!=y){
                                if(checkrangeword(x)==true){
                                    if(x==y-1||x==riverLastJiang+1){
                                        eatencard1=y;
                                        eatencard2=x;
                                            
                                        // System.out.println("吃牌");
                                        // eatencard.add(riverLastJiang);
                                        // eatencard.add(eatencard1);
                                        // eatencard.add(eatencard2);
                                        // handcard.remove(riverLastJiang);        /*把吃掉的牌移除*/
                                        // handcard.remove(eatencard1);
                                        // handcard.remove(eatencard2);
                                        // shuffle.pool.remove(shuffle.poolIndex-1);
                                        check=1;
                                        return check; 
                                    }
                                }
                            }
                        }
                    }else if(y==riverLastJiang+1&&checkrangeword(y)==true){
                        for(int b=0;b<handcard.size();b++){
                            int x=Integer.parseInt(gethandcard(b).toString());
                            if(x!=riverLastJiang&&x!=y){
                                if(checkrangeword(x)==true){
                                    if(x==y+1||x==riverLastJiang-1){
                                        eatencard1=y;
                                        eatencard2=x;
                                        // System.out.println("吃牌");
                                            // eatencard.add(riverLastJiang);
                                            // eatencard.add(eatencard1);
                                            // eatencard.add(eatencard2);
                                            // handcard.remove(riverLastJiang);        /*把吃掉的牌移除*/
                                            // handcard.remove(eatencard1);
                                            // handcard.remove(eatencard2);
                                            // shuffle.pool.remove(shuffle.poolIndex-1);
                                        check=1;
                                        return check;      
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(riverLastJiang>=14&&riverLastJiang<=22){
            for(int e=0;e<handcard.size();e++){
                int c=Integer.parseInt(gethandcard(e).toString());
                if(c!=riverLastJiang){
                    if(c==riverLastJiang-1&&checkrangecake(c)==true){
                        for(int f=0;f<handcard.size();f++){
                            int d=Integer.parseInt(gethandcard(f).toString());
                            if(d!=riverLastJiang&&d!=c){
                                if(checkrangecake(d)==true){
                                    if(d==c-1||d==riverLastJiang+1){
                                        eatencard1=c;
                                        eatencard2=d;
                                            
                                        // System.out.println("吃牌");
                                        // eatencard.add(riverLastJiang);
                                        // eatencard.add(eatencard1);
                                        // eatencard.add(eatencard2);
                                        // handcard.remove(riverLastJiang);        /*把吃掉的牌移除*/
                                        // handcard.remove(eatencard1);
                                        // handcard.remove(eatencard2);
                                        // shuffle.pool.remove(shuffle.poolIndex-1);
                                        check=1;
                                        return check; 
                                    }
                                }
                            }
                        }
                    }else if(c==riverLastJiang+1&&checkrangecake(c)==true){
                        for(int f=0;f<handcard.size();f++){
                            int d=Integer.parseInt(gethandcard(f).toString());
                            if(d!=riverLastJiang&&d!=c){
                                if(checkrangecake(d)==true){
                                    if(d==c+1||d==riverLastJiang-1){
                                        eatencard1=c;
                                        eatencard2=d;
                                        // System.out.println("吃牌");
                                            // eatencard.add(riverLastJiang);
                                            // eatencard.add(eatencard1);
                                            // eatencard.add(eatencard2);
                                            // handcard.remove(riverLastJiang);        /*把吃掉的牌移除*/
                                            // handcard.remove(eatencard1);
                                            // handcard.remove(eatencard2);
                                            // shuffle.pool.remove(shuffle.poolIndex-1);
                                        check=1;
                                        return check;      
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(riverLastJiang>=23&&riverLastJiang<=31){
            for(int a=0;a<handcard.size();a++){
                int y=Integer.parseInt(gethandcard(a).toString());
                if(y!=riverLastJiang){
                    if(y==riverLastJiang-1&&checkrangestick(y)==true){
                        for(int b=0;b<handcard.size();b++){
                            int x=Integer.parseInt(gethandcard(b).toString());
                            if(x!=riverLastJiang&&x!=y){
                                if(checkrangestick(x)==true){
                                    if(x==y-1||x==riverLastJiang+1){
                                        eatencard1=y;
                                        eatencard2=x;
                                            
                                        // System.out.println("吃牌");
                                        // eatencard.add(riverLastJiang);
                                        // eatencard.add(eatencard1);
                                        // eatencard.add(eatencard2);
                                        // handcard.remove(riverLastJiang);        /*把吃掉的牌移除*/
                                        // handcard.remove(eatencard1);
                                        // handcard.remove(eatencard2);
                                        // shuffle.pool.remove(shuffle.poolIndex-1);
                                        check=1;
                                        return check; 
                                    }
                                }
                            }
                        }
                    }else if(y==riverLastJiang+1&&checkrangestick(y)==true){
                        for(int b=0;b<handcard.size();b++){
                            int x=Integer.parseInt(gethandcard(b).toString());
                            if(x!=riverLastJiang&&x!=y){
                                if(checkrangestick(x)==true){
                                    if(x==y+1||x==riverLastJiang-1){
                                        eatencard1=y;
                                        eatencard2=x;
                                        // System.out.println("吃牌");
                                            // eatencard.add(riverLastJiang);
                                            // eatencard.add(eatencard1);
                                            // eatencard.add(eatencard2);
                                            // handcard.remove(riverLastJiang);        /*把吃掉的牌移除*/
                                            // handcard.remove(eatencard1);
                                            // handcard.remove(eatencard2);
                                            // shuffle.pool.remove(shuffle.poolIndex-1);
                                        check=1;
                                        return check;      
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return check;
    }

    public List arrangehandcard(List oldhandcard){
        Collections.sort(oldhandcard);
        return oldhandcard;
    }

    public int checkwin(){
        int check=0;
        if(eatencard.size()+touchedcard.size()==9){
            check=1;
            return check;
        }
        else if(donecard.size()>=9){
            check=1;
            return check;
        }
        return check;
    }
    public boolean checkrangeword(int word){
        if(word>=5&&word<=13){
            return true;
        }
        return true;
    }
    public boolean checkrangecake(int word){
        if(word>=14&&word<=22){
            return true;
        }
        return true;
    }
    public boolean checkrangestick(int word){
        if(word>=23&&word<=31){
            return true;
        }
        return true;
    }
    public int searchhandcard(int card){
        int searchindex=0;
        for(int i=0;i<=handcard.size();i++){
            if(Integer.parseInt(handcard.get(i).toString())==card){
                searchindex=i;
                return searchindex;
            }
        }
        return searchindex;
    }
    public void addtouchedcard(){
        int riverLastJiang = shuffle.pool.get(shuffle.getpoolindex()-1);
        donecard.add(donecardindex,riverLastJiang);
        donecardindex++;
        donecard.add(donecardindex,riverLastJiang);
        donecardindex++;
        donecard.add(donecardindex,riverLastJiang);
        donecardindex++;
        handcard.remove(searchhandcard(riverLastJiang)); 
        handcardindex--;
        handcard.remove(searchhandcard(riverLastJiang));        /*把碰掉的牌移除*/
        handcardindex--;
        // shuffle.pool.remove(shuffle.poolIndex-1);
        // shuffle.poolindexminus();
    }
    public void addeatencard(){
        int riverLastJiang = shuffle.pool.get(shuffle.getpoolindex()-1);
        donecard.add(donecardindex,riverLastJiang);
        donecardindex++;
        donecard.add(donecardindex,eatencard1);
        donecardindex++;
        donecard.add(donecardindex,eatencard2);
        donecardindex++;
        handcard.remove(searchhandcard(eatencard1));        /*把吃掉的牌移除*/
        handcardindex--;
        handcard.remove(searchhandcard(eatencard2));
        handcardindex--;
        // shuffle.pool.remove(shuffle.poolIndex-1);
        // shuffle.poolindexminus();
    }
}
