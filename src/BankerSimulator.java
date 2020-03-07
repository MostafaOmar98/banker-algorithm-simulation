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


    public void run()
    {
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while(!done)
        {
            if (state == null)
            {
                inputState();
                continue;
            }
            printMenu();
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    inputState();
                    break;
                case 2:
                    inputRequest();
                    break;
                case 0:
                    done = true;
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }

    private void inputRequest()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Process ID");
        int pid = sc.nextInt();
        if (pid >= state.getNp())
        {
            deny();
            return;
        }
        System.out.println("Enter number of requested resources: ");
        int nRequested = sc.nextInt();
        if (nRequested > state.getNr())
        {
            deny();
            return;
        }

        Bank newState = new Bank(state);
        for (int i = 0; i < nRequested; ++i)
        {
            int rid, cnt;
            System.out.println("Enter requested resource id: ");
            rid = sc.nextInt();
            System.out.println("Enter requested count: ");
            cnt = sc.nextInt();
            newState.allocate(pid, rid, cnt);
        }
        if (newState.isSafe())
        {
            accept();
            state = newState;
        }
        else
            deny();

    }

    private void accept()
    {
        System.out.println("Request is granted\n" +
                "System state has been updated\n");
    }

    private void deny()
    {
        System.out.println("Request Denied\n");
    }

    private void inputState()
    {
        Scanner sc = new Scanner(System.in);
        int np, nr;
        System.out.print("Number of processes: ");
        np = sc.nextInt();
        System.out.print("Number of resources: ");
        nr = sc.nextInt();

        int[][] max = new int[np][nr], alloc = new int[np][nr];
        int[] available = new int[nr];
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
                alloc[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < nr; ++i)
        {
            System.out.print("Available instance of resource " + i + ": ");
            available[i] = sc.nextInt();
        }

        state = new Bank(np, nr, max, alloc, available);
        if (!state.isSafe())
        {
            System.out.println("This state is not safe!");
            state = null;
        }
    }


}
