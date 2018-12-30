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

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        if(nums == null) return res;
        List<Integer> item = new ArrayList<>();
        dfs(item, nums, 0);
        return res;
    }
    
    private void dfs(List<Integer> item, int[] nums, int position) {
        if(position == nums.length) {
            //重要：在往List<List<T>>内添加List<T>的时候必须新建List<T>加入，否则只是修改List<List<T>>内的值
            res.add(new ArrayList<Integer>(item));
            return;
        }
        //nums[] 中有所有可取的数，为现在这一位(position)尝试所有可能的值
        for(int num:nums) {
            //这一步判断结果中是否有重复元素，也可以事先Arrays.sort(nums),但排序可能会增加额外的时间复杂度nlogn
            if(item.contains(num)) continue;
            item.add(num);
            dfs(item, nums, position+1);
            //回溯回上一步，移除新近加入的节点，以便于继续测试其他同层节点
            item.remove(item.size()-1);
        }
    }
}




/*
求全排列的backtracking模板：

//list s是已取出的数，nums是原始数组，pos是当前取第几个位置的数
public void helper(List<Integer> s,int[] nums,int pos){
        //跳出条件
        if(……){
            ……
            return;
        }
        //遍历池子中的数
        for(int num:nums){
            //取过的数不再取    
            if(s.contains(num)) continue;
            //取出一个数
            s.add(num);
            //进行下一个位置的取数，pos+1
            helper(s,nums,pos+1);
            //重要！！遍历过此节点后，要回溯到上一步，因此要把加入到结果中的此点去除掉！
            s.remove(s.size()-1);
        }
    }
}
*/
