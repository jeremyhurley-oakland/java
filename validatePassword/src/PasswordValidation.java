import java.util.HashSet;
import java.util.Set;

public class PasswordValidation {
    
    public static boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 16) {
            
            return false;
        }

        Set<Character> charTypes = new HashSet<>();
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)){
                charTypes.add('a');
            } else if (Character.isUpperCase(c)) {
                charTypes.add('A');
            } else if (Character.isDigit(c)) {
                charTypes.add('0');
            } else if ("~!@#$%^&*()-=+_".contains(String.valueOf(c))) {
                charTypes.add('!');
            }
            
        }
        
        return charTypes.size() >= 3;
    }
    
    public static void main(String[] args) {
        String password = "Pa$$w0rd123";
        if (isValidPassword(password)) {
            System.out.println("Valid password");
        } else {
            System.out.println("Invalid password");
        }
    }
}
