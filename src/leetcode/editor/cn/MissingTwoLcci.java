/**
  * 题目Id：面试题 17.19
  * 题目：消失的两个数字
  * 日期：2022-09-26 05:33:14
*/
//给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
//
// 以任意顺序返回这两个数字均可。
//
// 示例 1:
//
// 输入: [1]
//输出: [2,3]
//
// 示例 2:
//
// 输入: [2,3]
//输出: [1,4]
//
// 提示：
//
//
// nums.length <= 30000
//
// Related Topics 位运算 数组 哈希表
// 👍 144 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

public class MissingTwoLcci {
    public static void main(String[] args) {
        Solution solution = new MissingTwoLcci().new Solution();
        final int[] ints = solution.missingTwo2(new int[]{2,3});
        System.out.println(ints[0] + "|" + ints[1]);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] missingTwo(int[] nums) {
        // 数学解法
        // x + y = 预计总和 - 实际总和
        // x² + y² = 预计平方总和 - 实际平方总和
        // 易得 解为 (a ± sqrt(2b - a²)) / 2
        int n = nums.length + 2 , a = 0 , b = 0;
        for(int i = 1 ; i <= n ; i ++){
            a += i ;
            b += i * i;
        }
        for(int i : nums){
            a -= i ;
            b -= i * i;
        }
        int temp = (int) Math.sqrt(2 * b - a * a);
        return new int[]{(a + temp) / 2, (a - temp) / 2};
    }
    public int[] missingTwo2(int[] nums) {
        // 位运算
        int n = nums.length + 2;
        int a = 0;
        for(int i : nums){
            a ^= i;
        }
        for(int i = 1 ; i <= n ;i++ ){
            a ^= i;
        }
        // 相同的两个数异或后等于0 , 0 异或 任何数都等于它本身
        // 则此时的 a = x ^ y,  x,y 为最终结果
        // 计算 a 的 最低位1
        int low = a & -a;
        int[] res = new int[2];
        // 由于 a = x ^ y,则此时 a 中所有为 1 的位 确保 x,y 二者在这一位上是不同的
        // 通过这一位来将nums分组,可以确保一组总只有一个数未用到
        for(int i : nums){
            if((i & low) == 0){
                res[0] ^= i;
            }else{
                res[1] ^= i;
            }
        }
        // 在1 ~ n 范围内在分组,同时异或,使使用过得数,异或其本身得到0,那么最后剩下的,就是未使用的
        for(int i = 1 ; i <= n ; i++){
            if((i & low) == 0){
                res[0] ^= i;
            }else{
                res[1] ^= i;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
