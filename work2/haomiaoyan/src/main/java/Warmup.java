public class Warmup {

    static boolean isPalindrome(String s)
    {
        int left = 0;   // 左指针
        int right = s.length() - 1;  // 右指针

        while (left < right)
        {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if (!Character.isLetterOrDigit(l)){left++; continue;}
            if (!Character.isLetterOrDigit(r)){right--; continue;}
            if (Character.toLowerCase(l) != Character.toLowerCase(r)) return false;
            left++;
            right--;
        }
        return true;
    }

    static int[] reverse(int[] a)
    {
        int[] out = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            out[a.length - 1 - i] = a[i];
        }
        return out;
    }

}
