import java.util.Scanner;

public class Main {

    private static void input(int np, int nr, int[][] max, int[][] alloc, int[] available)
    {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < np; ++i)
        {
            for (int j = 0; j < nr; ++j)
            {
                System.out.print("Maximum need of process " + i + "of resource " + j + ": ");
                max[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < np; ++i)
        {
            for (int j = 0; j < nr; ++j)
            {
                System.out.print("Currently allocated to process " + i + "of resource " + j + ": ");
                max[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < nr; ++i)
        {
            System.out.print("Available instance of resource " + i + ": ");
            available[i] = sc.nextInt();
        }

    }

    private static void printMenu()
    {
        System.out.print("What would you like to do?\n" +
                "1- Enter new processes state\n" +
                "2- Add request to current state\n" +
                "0- Quit");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int np, nr;
        System.out.print("Number of processes: ");
        np = sc.nextInt();
        System.out.print("Number of resources: ");
        nr = sc.nextInt();

        int[][] max = new int[np][nr], alloc = new int[np][nr];
        int[] available = new int[nr];
        input(np, nr, max, alloc, available);

        while(true)
        {
            printMenu();
            int choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.print("Number of processes: ");
                    np = sc.nextInt();
                    System.out.print("Number of resources: ");
                    nr = sc.nextInt();
                    max = new int[np][nr];
                    alloc = new int[np][nr];
                    available = new int[nr];
                    input(np, nr, max, alloc, available);
                    break;
                case 2:

            }
        }

    }
}
