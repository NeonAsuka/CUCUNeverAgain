//Palindromic Substrings

/**
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.
*/

/**
    Hint:
    1. 单个char肯定是回文String，在此基础上，观察以每个char为中心点的extend String是否回文， 并且递归；
    2. 若一个String回文，则其左右均等延伸的extend string可能是回文String（具体观察扩展的两边char是否相同）；
    3. 若一个String不回文，则其左右均等延伸的extend string必定不回文；

    Pro: 用递归写easy as fuck
    Con: 但是复杂度很操蛋，效率差

    Todo：用循环重写，并参考超过100%的那个答案
*/

class Solution {

    int count  = 0;

    public int countSubstrings(String s) {
        if(s == null && s.length() == 0) return count;

        for(int i = 0; i < s.length(); i++) {
            extendPalindString(s, i, i);     //odd one
            extendPalindString(s, i, i+1);   //even one
        }

        return count;
    }

    private void extendPalindString(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            count++;
            left--;
            right++;
        }
    }
}
