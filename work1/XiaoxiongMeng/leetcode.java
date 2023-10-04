class Solution {
    public int removeElement(int[] nums, int val) {
         int sum = nums.length;

        for(int i = 0; i < sum; i++){
            if(val == nums[i]){
                for(int j = i; j < sum-1; j++){
                    nums[j] = nums[j+1];
                }
                sum--;
                i--;
            }
        }
        return sum;
    }
}