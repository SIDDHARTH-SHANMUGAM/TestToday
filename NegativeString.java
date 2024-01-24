import java.util.*;

public class NegativeString {
    public static void main(String arg[])
    {
        // inputs 
        // str1 = "ABC", str2 ="ZYX"--> YES
        // str1 = "ABC", str2 ="XYZ"--> YES
        // str1 = "ABC", str2 ="XYY"--> NO
        
        Scanner sc = new Scanner(System.in);
        char s1[] = sc.next().toCharArray();
        char s2[] = sc.next().toCharArray();
        if(s1.length!=s2.length)
        {
            System.out.print("NO");
            return;
        }
        Arrays.sort(s1);
        Arrays.sort(s2);
        for(int i=0; i<s1.length; i++)
        {
            if(isNotValid(s1[i], s2[s1.length-1-i]))
            {
                System.out.print("NO");
                return;
            }
        }
        System.out.print("YES");
    }
    public static boolean isNotValid(char c1, char c2)
    {
        return Math.abs(c1-'A')!=Math.abs(c2-'A'-25);
    }
}
