import java.util.ArrayList;

public class Bank {
    private int np, nr;
    private int[][] max, alloc;
    private int[] available;
    int[][] need;
    private ArrayList<Integer> safeSequence;
    public Bank(int np, int nr, int[][] max, int[][] alloc, int[] available) {
        this.np = np;
        this.nr = nr;
        this.max = max;
        this.alloc = alloc;
        this.available = available;
        need = new int[np][nr];
        for (int i = 0; i < np; ++i)
        {
            for (int j = 0; j < nr; ++j)
                need[i][j] = max[i][j] - alloc[i][j];
        }
        safeSequence = null;
    }

    public Bank(Bank other)
    {
        this.np = other.getNp();
        this.nr = other.getNr();
        max = new int[np][nr];
        alloc = new int[np][nr];
        available = new int[nr];
        for (int i = 0; i < np; ++i)
        {
            for (int j = 0; j < nr; ++j)
            {
                this.max[i][j] = other.getMax()[i][j];
                this.alloc[i][j] = other.getAlloc()[i][j];
            }
        }
        for (int j = 0; j < nr; ++j)
            this.available[j] = other.getAvailable()[j];
        need = new int[np][nr];
        for (int i = 0; i < np; ++i)
        {
            for (int j = 0; j < nr; ++j)
                need[i][j] = max[i][j] - alloc[i][j];
        }
        safeSequence = null;
    }

    public int[][] getMax() {
        return max;
    }

    public int[][] getAlloc() {
        return alloc;
    }

    public int[] getAvailable() {
        return available;
    }

    public ArrayList<Integer> getSafe() {
        return safeSequence;
    }

    public int getNp() {
        return np;
    }

    public int getNr() {
        return nr;
    }

    public boolean isSafe()
    {
        if (safeSequence != null)
            return true;

        // initialization
        int[] work = new int[nr];
        for (int i = 0; i < nr; ++i)
            work[i] = available[i];
        boolean[] finished = new boolean[np]; // inited to false
        safeSequence = new ArrayList<Integer>();

        for (int it = 0; it < np; ++it)
        {
            int picked = -1;
            for (int i = 0; i < np; ++i) {
                if (!finished[i] && canSatisfy(need[i], work))
                {
                    picked = i;
                    break;
                }
            }
            if (picked == -1)
            {
                safeSequence = null;
                break;
            }
            for (int j = 0; j < nr; ++j)
                work[j] += alloc[picked][j];
            finished[picked] = true;
            safeSequence.add(picked);
        }
        if (safeSequence != null)
            return true;
        return false;
    }

    boolean canSatisfy(int[] need, int[] work)
    {
        for (int i = 0; i < nr; ++i)
        {
            if (need[i] > work[i])
                return false;
        }
        return true;
    }

    public boolean allocate(int pid, int rid, int cnt)
    {
        if (alloc[pid][rid] + cnt <= max[pid][rid] && cnt <= available[rid])
        {
            alloc[pid][rid] += cnt;
            available[rid] -= cnt;
            need[pid][rid] -= cnt;
            return true;
        }
        return false;
    }

    private void showSafeSequence()
    {
        System.out.println("Safe Sequnce: ");
        for (int i = 0; i < np ;++i)
        {
            System.out.print("P" + safeSequence.get(i) + "  ");
        }
        System.out.println();
    }

    public void print()
    {
        System.out.println("Allocation Matrix: ");
        printMatrix(alloc);
        System.out.println("Need Matrix: ");
        printMatrix(need);
        System.out.println("Max matrix: ");
        printMatrix(max);
        System.out.print("Available: ");
        for (int i = 0; i < available.length; ++i)
            System.out.print(available[i] + " ");
        System.out.println();
        showSafeSequence();
    }

    private void printMatrix(int[][] mat)
    {
        for (int i = 0; i < mat.length; ++i)
        {
            System.out.print("P" + i + ": ");
            for (int j = 0; j < mat[i].length; ++j)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }
}
