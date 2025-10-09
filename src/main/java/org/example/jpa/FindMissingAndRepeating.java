package org.example.jpa;

import java.util.HashSet;
import java.util.Set;

public class FindMissingAndRepeating {
    public int[] findMissingAndRepeating(int[] nums)
    {
        int[] ans = new int[2];
        Set<Integer> set = new HashSet<>();
        for(int i =0;i<nums.length;i++)
            set.add(i);
        int fast = 1;
        int slow = 0;
        while(nums[fast] != nums[slow])
        {
            set.remove(fast);
            set.remove(slow);
            fast = nums[nums[fast]];
            slow = nums[slow];
        }
        ans[0] = nums[fast];
        ans[1] = set.stream().findFirst().get();
        return ans;
    }
}
