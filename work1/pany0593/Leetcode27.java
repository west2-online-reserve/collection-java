public class Leetcode27 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;

        int l = 0, r = nums.length;
        while (l < r) {
            if (nums[l] == val) {
                nums[l] = nums[r - 1];
                r--;
            } else
                l++;
        }
//        return l;

        for (int i = 0; i < l; i++)
            System.out.printf("%d ", nums[i]);
    }
}
