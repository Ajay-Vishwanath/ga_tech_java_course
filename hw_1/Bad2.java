public class Bad2 {
    public static void main(String[] args) {
        int a = 1331;
        int b = 0;
        System.out.println("Welcome to \nCS 1331!");
        if (b !=0){
          int c = a / b;
          System.out.println("c is equal to: " + c);
        } else {
          System.out.println("Error: Division by zero is not allowed.");
        }
    }
}