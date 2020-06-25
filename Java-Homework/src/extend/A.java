package extend;

public class A extends B {
    protected String me = "";

    public A(String a) {
        this.me = a;
        // A.say("yes");
        // super(a);
    }

    @Override
    public void say(String a) {
        me.concat(a);
    }
}