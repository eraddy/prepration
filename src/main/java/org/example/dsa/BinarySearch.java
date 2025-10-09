package org.example.dsa;

public class BinarySearch {

    private int search(int[] nums,int target,int l,int r)
    {
        int mid = (l+r)/2;

        if(nums[mid]>target)
        {
            r = target;
            return search(nums,target,l,r);
        }
        else if(nums[mid] < target){
            l = mid;
            return search(nums,target,l,r);
        }
        else
            return mid;
    }

    public int binarySearch(int[] nums,int target)
    {
        int l = 0;
        int r = nums.length-1;
        return search(nums,target,l,r);
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = {1,4,5,7,8,9,56,78,98};
        System.out.println(binarySearch.binarySearch(arr, 56));
    }
}
