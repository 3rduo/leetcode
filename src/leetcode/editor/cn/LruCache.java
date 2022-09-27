/**
  * 题目Id：146
  * 题目：LRU 缓存
  * 日期：2022-09-27 08:33:33
*/
//请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
//
// 实现 LRUCache 类：
//
//
//
//
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 ke
//y-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
//
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
//
//
//
//
//
// 示例：
//
//
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
//
//
//
//
// 提示：
//
//
// 1 <= capacity <= 3000
// 0 <= key <= 10000
// 0 <= value <= 105
// 最多调用 2 * 105 次 get 和 put
//
// Related Topics 设计 哈希表 链表 双向链表
// 👍 2411 👎 0

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LruCache {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {
        class LRUCacheNode {
            public LRUCacheNode() {
            }

            public LRUCacheNode(int key, int value) {
                this.key = key;
                this.value = value;
            }

            int key, value;
            LRUCacheNode prev, next;
        }

        Map<Integer, LRUCacheNode> cacheMap;
        int size, capacity;
        LRUCacheNode head, tail;


        public LRUCache(int capacity) {
            cacheMap = new HashMap<>();
            this.capacity = capacity;
            head = new LRUCacheNode();
            tail = new LRUCacheNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (cacheMap.containsKey(key)) {
                LRUCacheNode node = cacheMap.get(key);
                moveToHead(node);
                return node.value;
            }
            return -1;
        }

        private void moveToHead(LRUCacheNode node) {
            brokenNode(node);
            setHead(node);
        }

        private void setHead(LRUCacheNode node) {
            head.next.prev = node;
            node.next = head.next;
            head.next = node;
            node.prev = head;
        }

        private void brokenNode(LRUCacheNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;//for GC
            node.prev = null;
        }

        public void put(int key, int value) {
            LRUCacheNode node;
            if ((node = cacheMap.get(key)) != null) {
                moveToHead(node);
                node.value = value;
            } else {
                node = new LRUCacheNode(key, value);
                setHead(node);
                if (++size > capacity) {
                    removeTail();
                }
            }
            cacheMap.put(key, node);
        }

        private void removeTail() {
            LRUCacheNode node;
            if ((node = tail.prev) != head) {
                brokenNode(node);
                cacheMap.remove(node.key);
                size--;
            }
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
