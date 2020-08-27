public class HelloWorld {



        public static void main (String[] args) {
            hello("world");
            hello("user");
            hello("Olga");

            double l = 5;
            System.out.println ("площадь квадрата со стороный "+ 5 + " = " + area (l));
        }
        public static void hello(String somebody)  {
            System.out.println("Hello, " + somebody + "!");
        }
        public static double area(double len) {
            return len * len;
        }

        public static double area (double a, double b) {
            return a * b;
        }
}