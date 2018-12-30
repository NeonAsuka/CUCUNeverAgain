Q:

/*
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.
*/

A:

#我的解答
class Solution {
    
    List<List<Integer>> res = new ArrayList<>();
    
    public List<String> letterCasePermutation(String S) {
        char[] s = S.toCharArray();
        List<Integer> item = new ArrayList<>();
        List<String> result = new ArrayList<>();
        dfs(s, 0, item);
        for(List<Integer> re : res) {
            result.add(trans(re,s));
        }
        return result;
    }
    
    private void dfs(char[] s, int position, List<Integer> item) {
    //重要：在往List<List<T>>内添加List<T>的时候必须新建List<T>加入，否则只是修改List<List<T>>内的值
        res.add(new ArrayList<Integer>(item));
        if(position == s.length) return;
        for(int i=position; i<s.length; i++) {
            char ch = s[i];
            if(!Character.isLetter(ch)) continue;
            if(item.contains(i)) continue;
            item.add(i);
            //重要：此处应是i而不是position+1,全排列和全组合的这里是不同的
            dfs(s, i, item);
            item.remove(item.size()-1);
        }
    }
    
    private String trans(List<Integer> item, char[] s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length; i++) {
            if(item.contains(i)) {
                if (Character.isLowerCase(s[i])) sb.append(Character.toUpperCase(s[i]));
                else sb.append(Character.toLowerCase(s[i]));              
            }
            else sb.append(s[i]);
        }
        return sb.toString();
    }
}
