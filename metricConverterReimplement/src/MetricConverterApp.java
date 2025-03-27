import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MetricConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric/Imperial Unit Converter");

        // Creates the UI elements 
        Label choiceLabel = new Label("Select Conversion Type:");
        ComboBox<String> conversionChoiceBox = new ComboBox<>();
        conversionChoiceBox.getItems().addAll(
                "Kilometers to Miles",
                "Miles to Kilometers",
                "Meters to Feet",
                "Feet to Meters",
                "Centimeters to Inches",
                "Inches to Centimeters",
                "Celsius to Fahrenheit",
                "Fahrenheit to Celsius",
                "Kilograms to Pounds",
                "Pounds to Kilograms"
        );
        conversionChoiceBox.setValue("Kilometers to Miles");

        Label inputLabel = new Label("Enter value to convert:");
        TextField inputTextField = new TextField();

        Button convertButton = new Button("Convert");

        Label resultLabel = new Label("Result: ");
        
        // Layout (GridPane)
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        grid.add(choiceLabel, 0, 0);
        grid.add(conversionChoiceBox, 1, 0);
        grid.add(inputLabel, 0, 1);
        grid.add(inputTextField, 1, 1);
        grid.add(convertButton, 0, 2, 2, 1);
        grid.add(resultLabel, 0, 3, 2, 1);

        // Convert Button Action
        convertButton.setOnAction(e -> { // 
            try {
                double value = Double.parseDouble(inputTextField.getText());
                String selectedConversion = conversionChoiceBox.getValue();
                double result = 0;
                String resultText = "";

                switch (selectedConversion) {
                    case "Kilometers to Miles":
                        result = KilometersToMiles(value);
                        resultText = value + " Km = " + result + " mi";
                        break;
                    case "Miles to Kilometers":
                        result = MilesToKilometers(value);
                        resultText = value + " mi = " + result + " Km";
                        break;
                    case "Meters to Feet":
                        result = MetersToFeet(value);
                        resultText = value + " m = " + result + " ft";
                        break;
                    case "Feet to Meters":
                        result = FeetToMeters(value);
                        resultText = value + " ft = " + result + " m";
                        break;
                    case "Centimeters to Inches":
                        result = CentimetersToInches(value);
                        resultText = value + " cm = " + result + " in";
                        break;
                    case "Inches to Centimeters":
                        result = InchesToCentimeters(value);
                        resultText = value + " in = " + result + " cm";
                        break;
                    case "Celsius to Fahrenheit":
                        result = CelsiusToFahrenheit(value);
                        resultText = value + " °C = " + result + " °F";
                        break;
                    case "Fahrenheit to Celsius":
                        result = FahrenheitToCelsius(value);
                        resultText = value + " °F = " + result + " °C";
                        break;
                    case "Kilograms to Pounds":
                        result = KilogramsToPounds(value);
                        resultText = value + " Kg = " + result + " Lbs";
                        break;
                    case "Pounds to Kilograms":
                        result = PoundsToKilograms(value);
                        resultText = value + " Lbs = " + result + " Kg";
                        break;
                    default:
                        resultText = "Invalid choice.";
                }

                resultLabel.setText("Result: " + resultText);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Result: Invalid input value.");
            }
        });

        // Scene and Stage setup
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // All of the conversion methods
    static double KilometersToMiles(double Km) {
        return Km * 0.621371;
    }

    static double MilesToKilometers(double mi) {
        return mi * 1.60934;
    }

    static double MetersToFeet(double m) {
        return m * 3.28084;
    }

    static double FeetToMeters(double ft) {
        return ft * 0.3048;
    }

    static double CentimetersToInches(double cm) {
        return cm * 0.393701;
    }

    static double InchesToCentimeters(double in) {
        return in * 2.54;
    }

    static double CelsiusToFahrenheit(double C) {
        return C * 9 / 5 + 32;
    }

    static double FahrenheitToCelsius(double F) {
        return (F - 32) * 5 / 9;
    }

    static double KilogramsToPounds(double Kg) {
        return Kg * 2.20462;
    }

    static double PoundsToKilograms(double Lbs) {
        return Lbs * 0.453592;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
