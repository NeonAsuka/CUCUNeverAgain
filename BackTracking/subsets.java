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

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        if(nums==null) return res;
        List<Integer> item = new ArrayList<>();
      //此时需要进行排序，因为[1,3]和[3,1]是一样的，需要通过排序避免重复(同时后续遍历需要一直向右走不能回头,所以设置position避免回头路)
        Arrays.sort(nums);
        dfs(item, nums, 0);
        return res;
    }
    
  //position或者i记录现在遍历到的nums数组中的位置，后续值只能取此为右边的值(比此值大的值)，以避免重复
    private void dfs(List<Integer> item, int[] nums, int position) {
        res.add(new ArrayList<Integer>(item));
        for(int i=position; i<nums.length; i++) {
            int num = nums[i];
            if(item.contains(num)) continue;
            item.add(num);
          //设定后续的position必须不小于i(在i右边)
            dfs(item, nums, i);
            item.remove(item.size()-1);
        }
    }
}

