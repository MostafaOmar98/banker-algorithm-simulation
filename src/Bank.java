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

    public Bank(Bank other)
    {
        this.np = other.getNp();
        this.nr = other.getNr();
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
        safe = null;
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

    public Boolean getSafe() {
        return safe;
    }

    public int getNp() {
        return np;
    }

    public int getNr() {
        return nr;
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

    public boolean allocate(int pid, int rid, int cnt)
    {
        if (alloc[pid][rid] + cnt <= max[pid][rid] && cnt <= available[rid])
        {
            alloc[pid][rid] += cnt;
            available[rid] -= cnt;
            return true;
        }
        return false;
    }

}
