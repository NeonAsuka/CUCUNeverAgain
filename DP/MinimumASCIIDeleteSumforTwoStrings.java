/**
Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
Note:

0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].
*/

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int[][] count = new int[s1.length()+1][s2.length()+1];
        for(int i = 1; i < count.length; i++){
            count[i][0] = count[i-1][0] + s1.charAt(i-1); //初始化边界
        }
        for(int j = 1; j < count[0].length; j++){
            count[0][j] = count[0][j-1] + s2.charAt(j-1); //初始化边界
        }
        for(int i = 1; i < count.length; i++){
            for(int j = 1; j < count[0].length; j++){
                int cost = (s1.charAt(i-1) == s2.charAt(j-1))? 0: (s1.charAt(i-1)+s2.charAt(j-1));
                count[i][j] = Math.min((count[i-1][j]+s1.charAt(i-1)), (count[i][j-1]+s2.charAt(j-1)));
                count[i][j] = Math.min(count[i][j], (count[i-1][j-1]+cost));
            }
        }
        return count[s1.length()][s2.length()];
    }
}

/**
貌似就是最经典的背包问题解法
构建一个二维数组count，初始化边界（因为第一行和第一列都被用于初始化，所以count的总行数和总列数应为s1.lenght+1 和 s2.length+1）
背包正常套路：
比较三种情况的cost：
1. s1删除charAt(i-1)，s2不删；
2. s1不动，s2删除charAt(j-1);
3. s1与s2分别删除i-1和j-1后相同，但若i-1和j-1是同一字符，则不删（cost=0）；若不同，则计算cost
*/
