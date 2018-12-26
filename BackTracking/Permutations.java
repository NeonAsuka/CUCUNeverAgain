Q:

/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

A:

/*
DFS+Recursive: 典型的backtracking问题
本题就是求数组内数字的全排列。所以适用于backtracking。需要进行以下三步：
  1. 递归函数的开头写好跳出条件，满足条件才将当前结果加入总结果中
  2. 按顺序position为每一个位置安排不重复的数字，已经拿过的数不再拿 if(s.contains(num)){continue;}
  3. 遍历过当前节点后，为了回溯到上一步，要去掉已经加入到结果list中的当前节点。
  
更多请参考：https://blog.csdn.net/crystal6918/article/details/51924665
*/

public class Solution {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    
    
    public List<List<Integer>> permute(int[] nums) {
        if(nums == null) return res;
        List<Integer> select = new ArrayList<Integer>();
        helper(select, nums, 0);
        return res;
    }
    
    private void helper(List<Integer> s, int[] nums, int position){
        if(position == nums.length) {
            res.add(new ArrayList<Integer>(s));
            return;
        }
        
        //nums[] 中有所有可取的数，为现在这一位(position)尝试所有可能的值
        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            if(s.contains(num)) continue;
            s.add(num);
            helper(s, nums, position+1);
            //回溯回上一步，移除新近加入的节点，以便于继续测试其他同层节点
            s.remove(s.size()-1);
        }
    }
}
