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

但是，这种方法与backtracking无关。
*/

class Solution1 {
    public List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();
        for(int h=0; h<12; h++) {
            for(int m=0; m<60; m++) {
                if((Integer.bitCount(h) + Integer.bitCount(m)) == num) {
                    times.add(String.format("%d:%02d",h,m));
                }
            }
        }
        return times;
    }
}

/*
2.

*/
