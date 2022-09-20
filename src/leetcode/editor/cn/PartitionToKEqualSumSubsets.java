/**
  * 题目Id：698
  * 题目：划分为k个相等的子集
  * 日期：2022-09-20 02:00:36
*/
//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
//
//
//
// 示例 1：
//
//
//输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
//
// 示例 2:
//
//
//输入: nums = [1,2,3,4], k = 3
//输出: false
//
//
//
// 提示：
//
//
// 1 <= k <= len(nums) <= 16
// 0 < nums[i] < 10000
// 每个元素的频率在 [1,4] 范围内
//
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩
// 👍 703 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        Solution solution = new PartitionToKEqualSumSubsets().new Solution();
        final boolean b = solution.canPartitionKSubsets(new int[]{1, 1, 1, 1, 4}, 2);
        System.out.println(b);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] nums;
        int n, t, k;
        boolean[] vis;

        public boolean canPartitionKSubsets(int[] _nums, int _k) {
            nums = _nums; k = _k;
            int tot = 0;
            for (int x : nums) tot += x;
            if (tot % k != 0) return false; // 可行性剪枝
            Arrays.sort(nums);
            n = nums.length; t = tot / k;
            vis = new boolean[n];
            return dfs(n - 1, 0, 0);
        }
        boolean dfs(int idx, int cur, int cnt) {
            if (cnt == k) return true;
            if (cur == t) return dfs(n - 1, 0, cnt + 1);
            if (idx == -1) return false;
            for (int i = idx; i >= 0; i--) {  // 顺序性剪枝
                if (vis[i] || cur + nums[i] > t) continue;
                vis[i] = true;
                if (dfs(i - 1, cur + nums[i], cnt)) return true;
                vis[i] = false;
                if (cur == 0) return false; // 可行性剪枝
            }
            return false;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
