public class Bank {
    private int np, nr;
    private int[][] max, alloc;
    private int[] available;
    Boolean safe;
    public Bank(int np, int nr, int[][] max, int[][] alloc, int[] available) {
        this.np = np;
        this.nr = nr;
        this.max = max;
        this.alloc = alloc;
        this.available = available;
        safe = null;
    }

    public boolean isSafe()
    {
        if (safe != null)
            return safe;

        // initialization
        int[] work = available;
        boolean[] finished = new boolean[np]; // inited to false
        int[][] need = new int[np][nr];
        for (int i = 0; i < np; ++i)
        {
            for (int j = 0; j < nr; ++j)
                need[i][j] = max[i][j] - alloc[i][j];
        }
        safe = true;

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
                safe = false;
                break;
            }
            for (int j = 0; j < nr; ++j)
                work[j] += alloc[picked][j];
            finished[picked] = true;
        }
        return safe;
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
    
    private void allocate(int pid, int rid, int cnt)
    {

    }

}
