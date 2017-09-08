import java.util.Scanner;

public class helloWorld {

	public static void main(String[] args) {
		// Declare the object and initialize with
        // predefined standard input object
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
 
        // String input
        System.out.println("What is your name?");
        String name = sc.nextLine();
 
        // Character input
        System.out.println("What is your gender?");
        String gender = sc.nextLine();
 
        // Numerical data input
        // byte, short and float can be read
        // using similar-named functions.
        System.out.println("What is your age?");
        int age = sc.nextInt();
 
        // Print the values to check if input was correctly obtained.
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
        System.out.println("Hello " + name + ", you are a " + gender + " with the age of " + age + ".");
	}

}
