/**
  * 题目Id：1636
  * 题目：按照频率将数组升序排序
  * 日期：2022-09-19 09:28:24
*/
//给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
//
// 请你返回排序后的数组。
//
//
//
// 示例 1：
//
// 输入：nums = [1,1,2,2,2,3]
//输出：[3,1,1,2,2,2]
//解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
//
//
// 示例 2：
//
// 输入：nums = [2,3,1,3,2]
//输出：[1,3,3,2,2]
//解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
//
//
// 示例 3：
//
// 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
//输出：[5,-1,4,4,-6,-6,1,1,1]
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// -100 <= nums[i] <= 100
//
// Related Topics 数组 哈希表 排序
// 👍 103 👎 0

package leetcode.editor.cn;

import java.util.*;

public class SortArrayByIncreasingFrequency {
    public static void main(String[] args) {
        Solution solution = new SortArrayByIncreasingFrequency().new Solution();
        solution.frequencySort(new int[]{1,1,2,2,2,3});
        System.out.println("Hello world");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] frequencySort(int[] nums) {
        int[] sortArr = new int[201];
        for (int i : nums) {
            sortArr[i + 100]++;
        }
        Map<Integer, List<Integer>> map = new HashMap();
        for (int i = 200; i >= 0; i--) {
            if (sortArr[i] == 0) continue;
            List list = map.getOrDefault(sortArr[i], new LinkedList());
            list.add(i - 100);
            map.put(sortArr[i],list);
        }
        int index = 0;
        for (int i = 1; i <= 100; i++) {
            if (map.containsKey(i)) {
                for (int j : map.get(i)) {
                    Arrays.fill(nums, index, index + i, j);
                    index += i;
                }
            }
        }
        return nums;
    }
    public int[] frequencySort2(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums) {
            cnt.put(i, cnt.getOrDefault(i, 0) + 1);
            list.add(i);
        }
        Collections.sort(list, (a, b) -> {
            return cnt.get(a) != cnt.get(b) ? cnt.get(a) - cnt.get(b) : (int) b - (int) a;
        });
        for(int i = 0 ; i < nums.length ; i ++){
            nums[i] = (int) list.get(i);
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
