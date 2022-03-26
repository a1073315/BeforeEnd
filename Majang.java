

public class Majang {
    private int type;

    public int getType(){//圖檔名
        return type;
    }

    public void setType(int type){
        this.type =type;
    }


    public int Num=0;

    public String Number(){//1~9
        String NumString="";

        NumString+=this.getType();
        NumString+="";

        return NumString;

    }

    public int getNum(){
        return Num;
    }
       
    public void setNum(int Num){
        this.Num = Num ;
    }


    public String Wind(){//東南西北牌

            String WindString="";

            WindString+=this.getType();
            WindString+="";
            
            return WindString;

    }

    public String Word()  {//其他字牌

            String WordString="";

            WordString+=this.getType();
            WordString+="";
            
            return WordString;

    }

    public Majang Create(int type){//麻將牌
       Majang ma = null;

       if(type>=5 && type<=31){
           ma = new Majang();
           ma.setType(type);
       }
       else if(type==32||type==33||type==34){
           ma = new Majang();
           ma.setType(type);
       }
       else if(type==1||type==2||type==3||type==4){
           ma = new Majang();
           ma.setType(type);
       }
       return ma;
    }

}