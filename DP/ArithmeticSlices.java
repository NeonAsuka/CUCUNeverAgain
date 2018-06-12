/**
A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
*/

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int sum = 0;
        int curr = 0;
        for(int i = 2; i < A.length; i++){
            if(A[i-2] - A[i-1] == A[i-1] - A[i]){
                curr += 1;
                sum += curr;
            }
            else {
                curr = 0;
            }
        }
        return sum;
    }
}

/** Tip: 考虑 A= 【1，2，3，4】则有符合条件的【1，2，3】，【2，3，4】，【1，2，3，4】。
这意味着每增加一位（means [1,2,3]到[1,2,3,4], 你不止是增加一个 1 进sum，你需要用一个curr记录现阶段增加数量），
需要用curr记录以 A[i] 为最后一位的最长sub-array中满足条件的增加量

另外，一个很蠢的错误，i的最大值为A.length-1而不是A.legth因为i是从0开始的
*/
