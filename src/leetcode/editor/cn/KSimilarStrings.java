/**
  * 题目Id：854
  * 题目：相似度为 K 的字符串
  * 日期：2022-09-21 09:03:09
*/
//对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
//
// 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
//
//
//
// 示例 1：
//
//
//输入：s1 = "ab", s2 = "ba"
//输出：1
//
//
// 示例 2：
//
//
//输入：s1 = "abc", s2 = "bca"
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= s1.length <= 20
// s2.length == s1.length
// s1 和 s2 只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母
// s2 是 s1 的一个字母异位词
//
// Related Topics 广度优先搜索 字符串
// 👍 212 👎 0

package leetcode.editor.cn;

import java.util.*;

public class KSimilarStrings {
    public static void main(String[] args) {
        Solution solution = new KSimilarStrings().new Solution();
        System.out.println(solution.kSimilarity("aabbccddee", "dcacbedbae"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int res = Integer.MAX_VALUE, n;
        char[] chArr1, chArr2;

        public int kSimilarity(String s1, String s2) {
            chArr1 = s1.toCharArray();
            chArr2 = s2.toCharArray();
            n = chArr1.length;
            dfs(0, 0);
            return res;
        }

        private void dfs(int idx, int steps) {
            if (steps >= res) return; //当前交換次数已经大于统计过得最小次数,直接返回
            if (idx == n) { //已经到最后一位,此时就是最小
                res = steps;
                return;
            }
            int i = idx;
            while (i < n) {// 从当前位往后遍历,检查时候有不同的,不同时则递归继续计算步数
                if (chArr1[i] != chArr2[i]) {
                    for (int j = i + 1; j < n; j++) {
                        if (chArr1[j] == chArr2[i]) {
                            swap(i, j);
                            dfs(i + 1, steps + 1);
                            swap(i, j);
                        }
                    }
                    return;
                }
                i++;
            }
            //没有在上面return掉,说明进来后,当前节点之后全都相同,赋值结果
            res = steps;
        }

        private void swap(int i, int j) {
            char ch = chArr1[i];
            chArr1[i] = chArr1[j];
            chArr1[j] = ch;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
