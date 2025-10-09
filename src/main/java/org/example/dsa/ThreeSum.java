package org.example.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0;i<nums.length;i++)
        {
            int left = i+1;
            int right = nums.length-1;

            while(left < right)
            {
                while(left < nums.length && nums[left] == nums[left-1]) left++;
                while(right < nums.length -1 && nums[right] == nums[right+1]) right--;

                if(nums[i] + nums[left] + nums[right] == 0)
                {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    ans.add(list);
                }
                left ++;
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] arr = {-1,0,1,2,-1,-4};
        System.out.println(threeSum.threeSum(arr));
    }
}
