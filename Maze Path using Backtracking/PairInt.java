public class PairInt {
    //private data fields @SmitaRath
    private int x;
    private int y;

    //constructor for initialization @SmitaRath
    public PairInt(int x, int y){
        this.x=x;
        this.y=y;
    }

    //getter methods @SmitaRath
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    //setter methods @SmitaRath
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }

    //overrding equals methods @SmitaRath
    @Override
    public boolean equals(Object p){
        PairInt pairInt = (PairInt) p;
        return this.x == pairInt.x && this.y == pairInt.y;
    }
    //overrding toString method @SmitaRath
    @Override
    public String toString()
    {
        return "(" + x + "," + y + ")";
    }

    //copy method to copy and create a new object @SmitaRath
    public PairInt copy(){
        return new PairInt(this.x,this.y);
    }
}
