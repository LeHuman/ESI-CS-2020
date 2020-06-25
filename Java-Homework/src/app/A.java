package app;

public class A extends B {

    public A(String name) {
        super(name);
    }

    public void eat() {
        System.out.println(this.getID());
    }

    @Override
    public int say(String s) {
        s += ", AHHHHH!";
        System.out.println("yes");
        return s.length();
    }

    public static void main(String[] args) {
        try {
            int i = 1 / 0;
            System.out.println(i);
        } catch (ArithmeticException e) {
            System.out.println("ohno");
        } catch (Exception e) {
            System.out.print("what happened?");
        }
    }

}