package app;

public abstract class B {
    private static int IDCount = 0;
    private int ID;

    public B(String name) {
        this.ID = ++IDCount;
        System.out.println(name + " " + this.ID);
    }

    public int getID(){
        return ID;
    }

    public abstract int say(String s);
}
