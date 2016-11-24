package ru.stqa.pft.sandbox;

public class MyFirst {

    public static void main(String[] args) {
       hello("World");
        hello("User");
        hello("Alexei");

        double l = 5;
        System.out.println("Площадь Квадрата со сторойной  " + l + " = " + area(l));

        double a = 4;
        double b = 6;
        System.out.println("Площадь Прямоугольника со сторойнами  " + a + " и " + b + " = " + area(a, b));
    }

        public static void hello(String somebody) {
            System.out.println("Hello, " + somebody + "!");

        }
        public static double area(double l ){
            return  l * l;
        }
    public static double area(double a, double b ){
        return  a * b;
    }
}



