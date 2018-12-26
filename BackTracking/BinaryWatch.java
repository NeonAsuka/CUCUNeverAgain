# Easy

Q:
/**
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
*/

class Solution {
    public List<String> readBinaryWatch(int num) {
        
    }
}


A:

/*
1.
逆向思考：
正向思考本题，可以得到小时h的binary为1的位数，进而得到分钟的binary为1的位数(即 num-h)。但是在知道h和m之后确定具体的小时和分钟数字时会
遇到困难(可能的解法是用递归， 见解法2)。此时不妨逆向思考。
逆向思考的话，我们直接go through所有可能的小时和分钟的数字(而不是为1的二进制位数)，进而构成一个完整的时间。判断此时间转换为二进制时1的
位数是否等于num,如果是，即是我们想要的结果。

Time: 1
Space: 1

但是，这种方法与backtracking无关。
*/

class Solution1 {
    public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();
        for(int h=0; h<12; h++) {
            for(int m=0; m<60; m++) {
                if((Integer.bitCount(h) + Integer.bitCount(m)) == num) {
               //or h<<6 + m  or  h*64 + m (since 2^6=64, thus h*64 == h<<6)
                    times.add(String.format("%d:%02d",h,m));
                }
            }
        }
        return times;
    }
}

/*
2. 在获得hour为1的位数后，则m为1位数为num-h，然后用递归DFS遍历所有可能的情况。

Time: 至少为NlogN+N^2
Space: N?

*/

public class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }
        
        for(int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);    
        }
    }
}
