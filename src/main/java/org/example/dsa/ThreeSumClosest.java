package org.example.dsa;

import java.util.Arrays;

public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target)
    {
        Arrays.sort(nums);
        int sum  = Integer.MAX_VALUE;
        for(int i = 0;i<nums.length-2;i++)
        {
            if(i>0 && nums[i] == nums[i-1]) continue;

            int left = i+1;
            int right = nums.length -1;
            while(left<right)
            {
                int temp  = nums[i]+nums[left]+nums[right];
                if(Math.abs(temp - target) < Math.abs(sum - target))
                    sum = temp;

                if(sum < target)
                    left++;
                else if(sum > -target)
                    right--;
                else
                    return sum;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] arr = {2,4,2,4,5,3,4};
        System.out.println(ThreeSumClosest.threeSumClosest(arr,5));
    }
}
