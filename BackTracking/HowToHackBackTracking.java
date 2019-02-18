/*
 * Back Tacking是一种包含剪枝函数的枚举法。通过剪枝避免讨论无效情况或重复情况。
 * 枚举的时候常常会会采用DFS或BFS遍历。
 * 一般用于解决两种问题：求 全排列 或 全组合。
 * 这两种情况下，他们的模板是不同的。我们将分别讨论这两种情况。
 */

// 全排列：构建nums数组中所有元素的全排列，结果记录进List<List<Integer>> res中
/* 
DFS 做法, refer https://github.com/NeonAsuka/CUCUNeverAgain/blob/master/BackTracking/Permutations.java
list s是待构建的答案，nums是原始数组，pos是当前取第几个位置的数
需要进行以下三步：
  1. 递归函数的开头写好跳出条件，满足条件才将当前结果加入总结果中
  2. 决定遍历的顺序，避免重复访问节点 if(s.contains(num)){continue;}
  3. 遍历过当前节点后，为了回溯到上一步，要去掉已经加入到结果list中的当前节点。
  4*. 若题目允许全排列中存在重复元素，可以考虑标记重复元素们，强制使其排列符合某个顺序以避免重复。
      如[3, 3, 3],可考虑标记为[3*, 3**, 3***], 任何情形下，3*必须先于3**， 3**必须先于3***。
      通常实现方法是先排序nums，取数时检查该元素左边的那个相同元素是否被使用。必须所有它左侧的相同元素已被使用，它才可以被使用。
      可以在for loop开头添加如下检查：
      visited数组记录第i个元素是否被访问，访问过记为1，未访问为0。 则若先前已访问过该元素或未访问但左侧相同元素也未访问，则跳过该情形。
      if(visited[i]==1 || (i>0 && nums[i] == nums[i-1]&& visited[i-1] == 0)) continue;
      
Time:
Space:
*/
//递归函数：
public void dfs(List<Integer> s,int[] nums,int pos){
        //跳出条件, like position == nums.length
        if(...){
            //重要：在往List<List<T>>内添加List<T>的时候必须新建List<T>加入，否则只是修改List<List<T>>内的值
            res.add(new ArrayList<Integer>(item));
            return;
        }
        //遍历池子中的数
        for(int num:nums){
            //取过的数不再取    
            if(s.contains(num)) continue;
            //取出一个数
            s.add(num);
            //进行下一个位置的取数，pos+1
            dfs(s,nums,pos+1);
            //重要！！遍历过此节点后，要回溯到上一步，因此要把加入到结果中的此点去除掉！
            s.remove(s.size()-1);
        }
    }
}
//TODO: BFS做法，非递归做法


// 全组合：构建nums数组中所有元素的全组合，结果记录进List<List<Integer>> res中
/* 
DFS递归： refer https://github.com/NeonAsuka/CUCUNeverAgain/blob/master/BackTracking/subsets.java
求全组合相比于求全排列，应注意：
  1. 不是在结果长度等于数组长度时才将结果加入总结果中，而是在每次递归中都将当前组合加入结果中，因为求的是子集而不是全排列。
  2. 每次递归不是在池子中随便取一个数加入当前结果，因为对于组合而言，[1,3]和[3,1]是相同的，要求的是[1,3]。
     因此每次在取数时，都只能从position右侧取值，以防止取到重复的组合。
     (这一方法并不适用于带重复项的全排列，因为[1,2,2]与[2,1,2]是不同的排列，只取右侧值会忽略[2,1,2]情况）
     
  Time:
  Space:
*/
//递归函数： 注意，nums是sorted的数组
public void dfs(List<Integer> s, int[] nums, int position) {
  //重要：在往List<List<T>>内添加List<T>的时候必须新建List<T>加入，否则只是修改List<List<T>>内的值
  //全组合此时没有跳出条件了，所有长度的答案都得收录。如果还有其他跳出条件，也可以考虑加。
  res.add(new ArrayList<Integer>(item));
  //限制了取数时只能取position右侧的值
  for(int i=position; i<nums.length; i++) {
    int num = nums[i];
    //查重
    if(item.contains(num)) continue;
    //取出一个数
    item.add(num);
    //设定后续的position必须不小于i(在i右边)
    dfs(item, nums, i);
    item.remove(item.size()-1);
}
