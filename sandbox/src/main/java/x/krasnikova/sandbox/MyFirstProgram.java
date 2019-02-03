package x.krasnikova.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("user");
    hello("world");
    hello("test");


    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(5,7);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

  }

  public static void hello(String somebody){
    System.out.println("HELLO, " + somebody + "!");
  }

}
