package org.example.dsa;

public class MergeSort {
    public int[] mergeSort(int[] nums)
    {
        int l = 0;
        int r = nums.length-1;
        mergeSort(nums,l,r);
        return nums;

    }
    public void mergeSort(int[] nums,int left,int right)
    {
        if(left < right)
        {
            int mid = (left+right)/2;
            mergeSort(nums,left,mid);
            mergeSort(nums,mid,right);
        }
    }

    public void merge(int[] nums,int left,int right,int mid)
    {

    }

}
