/* 
BFS遍历树模板
基本思路：将上一层节点放入一个stack中，遍历这个stack并：
1. 将每个节点的左右子节点放入栈中
2. 之后将该节点出栈（用完就扔工具人）

以二叉树为例： 111. Minimum Depth of Binary Tree
从root遍历到 Minimum Dapth of Binary Tree
*/

public int BFS(TreeNode root) {
    if(root == null) return 0;
    int depth = 1;     // 初始化一个depth，本题用depth记录深度
   
 >>>>>>>>>>>> 模板开始>>>>>>>>>>>>
 
    // 初始化一个FIFO queue并将root入栈
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.offer(root);
    
    // 终止条件：栈空
    while(!q.isEmpty()){
        //用size限制每次loop只访问某一层的所有节点，就算下一层的节点也在栈中也不访问
        int size = q.size();
        // 遍历某一层所有节点
        for(int i=0;i<size;i++){
            // 出栈
            TreeNode node = q.poll();
            
            // 对该层节点进行处理
            if(node.left == null && node.right == null){
                return depth;
            }
            
            // 将左右子节点入栈
            if(node.left != null){
                q.offer(node.left);
            }
            if(node.right != null){
                q.offer(node.right);
            }
        }
        depth++;
    }
>>>>>>>>>>>> 模板结束 >>>>>>>>>>>>>>

    return depth;
}
