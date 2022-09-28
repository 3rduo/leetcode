/**
  * 题目Id：面试题 17.09
  * 题目：第 k 个数
  * 日期：2022-09-28 07:48:11
*/
//有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，
//5，7，9，15，21。
//
// 示例 1:
//
// 输入: k = 5
//
//输出: 9
//
// Related Topics 哈希表 数学 动态规划 堆（优先队列）
// 👍 174 👎 0

package leetcode.editor.cn;


import java.util.*;

public class GetKthMagicNumberLcci {
    public static void main(String[] args) {
        Solution solution = new GetKthMagicNumberLcci().new Solution();
        System.out.println(solution.getKthMagicNumber(9));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int getKthMagicNumber(int k) {
            int[] ints = {3, 5, 7};
            PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
            Set<Long> set = new HashSet<>();
            priorityQueue.add(1l);
            /**
             * 使用优先队列
             * 将每次的最小值(队列头的值),分别乘以3,5,7,并将结果存入优先队列,
             * 每次取出的值确保是前面操作后最小的值,一次类推,直到第k次,返回结果
             * 空间占用太多,且随着队列中的数据越来越多,时间消耗也会越来越多
             */
            for (int i = 0; i < k; i++) {
                if (i == k - 1) {
                    long res = priorityQueue.peek();
                    return (int) res;
                }
                long poll = priorityQueue.poll();
                for (int j : ints) {
                    long element = poll * j;
                    if (!set.contains(element)) {
                        priorityQueue.add(element);
                        set.add(element);
                    }
                }
            }
            return -1;
        }
        public int getKthMagicNumber1(int k) {
            int n3 = 0, n5 = 0, n7 = 0;
            int[] arr = new int[k];
            arr[0] = 1;
            /**
             * 题目要求中所有的数都是由3,5,7相乘得到的,
             * 通过n3,n5,n7分别记录是否使用过得3,5,7 个数
             * 将每次得到的最小数依次放在数组中,后续的所有值,都是通过前面已经键入的值乘以3,5,7得到
             * 将每次的数据记录进arr,得到最终结果
              */
            for (int i = 1; i < k; i++) {
                int a = arr[n3] * 3, b = arr[n5] * 5, c = arr[n7] * 7;
                int min = Math.min(a, Math.min(b, c));
                if (min == a) n3++;
                if (min == b) n5++;
                if (min == c) n7++;
                arr[i] = min;
            }
            return arr[k - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
