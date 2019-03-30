import java.util.Scanner; //import scanner

public class Average {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //create scanner class
        
        System.out.println("Enter 4 Numbers"); //print line for user

            float number = input.nextFloat(); //insert 4 numbers 
            float number2 = input.nextFloat(); //nextFloat is what type of data user inputs
            float number3 = input.nextFloat();
            float number4 = input.nextFloat();
            double Average = ((number + number2 + number3 + number4) / 4); //equation to get average
        
        System.out.println("The Average is: " + Average); //output
        input.close(); //close the input
    }
}

