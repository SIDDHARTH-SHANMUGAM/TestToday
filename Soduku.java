import java.util.*;
public class P{
    static class Object{
        int i;
        int j;
        
        public Object(int i, int j)
        {
            this.i=i;
            this.j=j;
        }
        public String toString()
        {
            return "{ "+this.i+" "+this.j+"},\n";
        }
    }
    static ArrayList<Object> needFill = new ArrayList<>();
    public static void main(String rg[]){
        Scanner sc= new Scanner(System.in);
        int n=9;
        int a[][] = new int [9][9];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                a[i][j]=sc.nextInt();
                if(a[i][j]==0)
                {
                    needFill.add(new Object(i, j));
                }
            }
        }
        if(setVal(a))
        print(a);
        else
        System.out.print("No solution");
    }
    public static boolean setVal(int a[][])
    {
        
        if(needFill.isEmpty())
        {
            return true;
        }
        Object x = needFill.get(0);
        needFill.remove(0);
        ArrayList<Integer> li = getPossibles(x, a);
        if(a[1][0]==1)
            return false;
        if(li.isEmpty())
        {
            needFill.add(0, x);
            return false;
        }
        for(Integer val : li)
        {
            a[x.i][x.j]=val;
            if(setVal(a))
            {
                return true;
            }
            a[x.i][x.j] = 0;
        }
        
        needFill.add(0, x);
        return false;
        
    }
    public static ArrayList<Integer> getPossibles(Object x, int a[][])
    {
        int in= x.i;
        int jn= x.j;
        boolean ar[]= new boolean[10];
        Arrays.fill(ar, true);
        for(int i=0; i<9; i++)
        {
            ar[a[in][i]] = false;
        }
        for(int i=0; i<9; i++)
        {
            ar[a[i][jn]] = false;
        }
        int subi=(in/3)*3;
        int subj=(jn/3)*3;
        for(int i=subi; i<subi+3; i++)
        {
            for(int j=subj; j<subj+3; j++)
            {
                ar[a[i][j]]=false;
            }
        }
        ArrayList<Integer> pos = new ArrayList<>();
        for(int i=1; i<10; i++)
        {
            if(ar[i])
                pos.add(i);
        }
        return pos;
        
    }
    public static void print(int a[][])
    {
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                System.out.print(a[i][j]+" ");
            }
                System.out.println();
        }
    }
}