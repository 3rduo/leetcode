/**
  * 题目Id：3
  * 题目：无重复字符的最长子串
  * 日期：2022-09-27 07:01:58
*/
//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 字符串 滑动窗口
// 👍 8209 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstring1("Hello world"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] used = new int[256];
        int res = 0 , l = 0 , r = 0 ,len = 0;
        Arrays.fill(used, -1);
        for(char ch : s.toCharArray()){
            // 之前出现过当前值,并且出现值得位置大于当前记录的l
            if(used[ch] != -1 && l < used[ch]){
                // 重新计算步长
                l = used[ch];
                len = r - l + 1;
            // 之前未出现过当前值,或者出现位置小于记录的l
            }else if(used[ch] == -1) len ++;
            //比较len 与 res 取大;
            res = Math.max(res, len);
            used[ch] = ++r;
        }
        return res;
    }
    public int lengthOfLongestSubstring1(String s) {
        int res = 0 ;
        boolean used[] = new boolean[256];
        LinkedList<Character> list = new LinkedList();
        for(char ch : s.toCharArray()){
            if(used[ch]){
                while( list.getFirst() != null && !list.getFirst().equals(ch) ) {
                    used[list.getFirst()] = false;
                    list.removeFirst();
                }
                list.removeFirst();
            }
            used[ch] = true;
            list.add(ch);
            res = Math.max(res, list.size());
        }
        return res;
    }
    public int lengthOfLongestSubstring2(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        Arrays.fill(last, -1);
        int n = s.length(), res = 0, start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
