package chapter3;

public class CrazyStuff {
    public static void main(String[] args) {
        printWeather(2);
        switchStuff();
    }   

    static void printWeather(int rain) {
        // this is a switch "statement" apparently
        switch (rain) {
            case 0 -> System.out.print("Dry"); // breaks not necessary apparently 
            case 1 -> System.out.print("Wet");
            case 2 -> System.out.print("Storm");
        }
    }    

    static void switchStuff() {
        int day = 3;
        String name = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            default -> "Other";
        };
        System.out.println(name);
    }
}
