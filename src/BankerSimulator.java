import java.util.Scanner;

public class BankerSimulator {
    Bank state;

    BankerSimulator()
    {
        state = null;
    }

    private static void printMenu()
    {
        System.out.print("What would you like to do?\n" +
                "1- Enter new processes state\n" +
                "2- Add request to current state\n" +
                "0- Quit");
    }


    void run()
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            int choice;
            choice = sc.nextInt();
        }
    }


}
