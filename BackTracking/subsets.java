Q:

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

A:
/*
本题是求全组合。相比于求全排列，应注意：
  1. 不是在结果长度等于数组长度时才将结果加入总结果中，而是在每次递归中都将当前组合加入结果中，因为求的是子集而不是全排列。
  2. 每次递归不是在池子中随便取一个数加入当前结果，因为此题要求的是子集，[1,3]和[3,1]是相同的，要求的是[1,3]，因此每次在取数时，都要从其位置开始取后面的数，防止取到[3,1]这样的结果。

参考：https://blog.csdn.net/crystal6918/article/details/51924665 

*/

public class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> item = new ArrayList<>();
        Arrays.sort(nums);
        helper(item, nums, 0, 0);
        return res;  
    }
    
    private void helper(List<Integer> s, int[] nums, int nowPosition, int indexPosition) {
        res.add(new ArrayList<Integer>(s));
        if(nowPosition == nums.length) return;
        
        for(int i=indexPosition; i<nums.length; i++) {
            if(s.contains(nums[i])) continue;
            s.add(nums[i]);
            helper(s, nums, nowPosition+1, i);
            s.remove(s.size()-1);
        }
    }
}

