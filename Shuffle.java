import java.util.*;
import java.util.ArrayList;

public class Shuffle{
    public static ArrayList majanglist=new ArrayList<>();
    public static int index=0;
    public static ArrayList<Integer> pool=new ArrayList<>();
    public int poolIndex=0;
    public static int PAUSE=100;
    public static Majang majang=new Majang();
    public static int check=1;

    private void random(){
        Collections.shuffle(majanglist);
    }

    public Shuffle() {
        if(check==1){
            create();
            random();
            index=135;
        }
        poolIndex=0;
        
    }


    private void create() {//做出所有牌
        String[] a=new String[]{"萬","條","筒","東","南","西","北","中","發","白板"}; 
        int[] d=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34};
        String b="";
        int wordcheck=0;
        int numcheck=0;
        int windcheck=0;
        for(int i=0;i<d.length;i++){
            int c=d[i];
            if(c>=5&&c<=31){
                for(int k=0;k<=3;k++){
                    Majang ma=majang.Create(c);
                    int value=ma.getType();
                   
                    majanglist.add(index,value);
                    index++;
                    numcheck++;
                    
                }
            }
            else if(c>=1&&c<=4){
                    for(int k=0;k<=3;k++){
                        Majang ma=majang.Create(c);
                        int value=ma.getType();
                        majanglist.add(index,value);
                        index++;
                        windcheck++;
                    }
                
            }else if(c>=32&&c<=34){
                for(int k=0;k<=3;k++){
                    Majang ma=majang.Create(c);
                    int value=ma.getType();
                    majanglist.add(index,value);
                    index++;
                    wordcheck++;
                }
            }
        }
        // System.out.println(majanglist.size());
        // System.out.println("紅中、發、白版有"+wordcheck+"張");
        // System.out.println("條、萬、筒有"+numcheck+"張");
        // System.out.println("東、南、西、北有"+windcheck+"張");
        check=0;
    }

    public int majanglist_size(){
        return majanglist.size();
    }
    public int getMajang(int index){
        int temp=Integer.parseInt(majanglist.get(index).toString());
        return temp;
    }
    public void removeMajang(int index){
        majanglist.remove(index);
    }
    public int getpoolindex(){
        return poolIndex;
    }
    public void poolindexplus(){
        poolIndex++;
    }
    public void poolindexminus(){
        poolIndex--;
    }
    public void addpool(int poolmajang){//打出的牌加進牌池裡
        pool.add(poolIndex,poolmajang);
        // poolindexplus();
        // pool.append(poolmajang);
    }
    public ArrayList getpool(){//整個牌池中的牌
        return pool;
    }
    public int getpoolcard(int index){
        return pool.get(index);
    }
    public int getlastpoolcard(){
        return pool.get(poolIndex-1);
    }
    public int size(){
        int size=0;
        for (int number : pool){
            size++;
        }
        return size;
    }
    public void indexminus(){
        index--;
    }
}