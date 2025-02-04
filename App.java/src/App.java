import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Hello and welcome to the java Metric/Imperial unit Conveter Program!");
            System.out.println("Please select unit conversion type:");
            System.out.println("1. Kilometers to Miles");
            System.out.println("2. Miles to Kilometers");
            System.out.println("3. Meters to Feet");
            System.out.println("4. Feet to Meters");
            System.out.println("5. Centimeters to Inches");
            System.out.println("6. Inches to Centimeters");
            System.out.println("7. Celsius to Fahrenheit");
            System.out.println("8. Fahrenheit to Celsius");
            System.out.println("9. Kilograms to Punds");
            System.out.println("10. Pounds to Kilograms");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            System.out.print("Enter value to convert: ");
            double value = scanner.nextDouble();

            switch (choice) {
                case 1:
                            System.out.println(value + " Km = "+ KilometersToMiles(value) + " mi");
                    break;
                case 2:
                            System.out.println(value + " mi = "+ MilesToKilometers(value) + " Km");
                    break;
                case 3:
                            System.out.println(value + " m = " + MetersToFeet(value) + " ft");
                    break;
                case 4:
                            System.out.println(value + " ft = " + FeetToMeters(value) + " m");
                    break;
                case 5:
                            System.out.println(value + " cm = " + CentimetersToInches(value) + " in");
                    break;
                case 6:
                            System.out.println(value + " in = " + InchesToCentimeters(value) + " cm");
                    break;
                case 7:
                            System.out.println(value + " °C = " + CelsiusToFahrenheit(value) + " °F");
                    break;
                case 8:
                            System.out.println(value + " °F = " + FahrenheitToCelsius(value) + " °C");
                    break;
                case 9:
                            System.out.println(value + " Kg = " + KilogramsToPounds(value) + " Lbs");
                    break;
                case 10:
                            System.out.println(value + " Lbs = " + PoundsToKilograms(value) + " Kg");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            System.out.println();
        }

        scanner.close();
        
    }

    // All of the conversion methods.
    static double KilometersToMiles (double Km) {
        return Km * 0.621371;
    }

    static double MilesToKilometers (double mi) {
        return mi * 1.60934;
    }

    static double MetersToFeet (double m) {
        return m * 3.28084;
    }

    static double FeetToMeters (double ft) {
        return ft * 0.3048;
    }

    static double CentimetersToInches (double cm) {
        return cm * 0.393701;
    }

    static double InchesToCentimeters (double in) {
        return in * 2.54;
    }

    static double CelsiusToFahrenheit (double C) {
        return C * 9 / 5 + 32;
    }

    static double FahrenheitToCelsius (double F) {
        return (F - 32) * 5 / 9;
    }

    static double KilogramsToPounds (double Kg) {
        return Kg * 2.20462;
    }

    static double PoundsToKilograms (double Lbs) {
        return Lbs * 0.453592;
    }

}
