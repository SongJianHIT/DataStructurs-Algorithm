# 数据结构：链表

## 1 基本结构介绍

### 1.1 单链表

单链表（Single Linked List）在算法中非常常见了。Java 形式的单链表类可实现如下：

```java
public static class ListNode {
		public int value;
		public ListNode next;
		public ListNode(int data) {
				value = data;
    }
}
```

### 1.2 双向链表

Java 形式的双向链表类可实现如下：

```java
public static class DoubleListNode {
		public int value;
  	    public ListNode last;
		public ListNode next;
		public ListNode(int data) {
				value = data;
    }
}
```

## 2 常见题型

### 2.1 单链表的反转

> LeetCode：https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/

给定单链表的头节点 `head` ，请反转链表，并返回反转后的链表的头节点。

![img](https://tva1.sinaimg.cn/large/008vxvgGgy1h84qhffgkfj30f2066q33.jpg)

```
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
```

**思路**

在遍历链表时，将当前节点的 `next` 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。在更改引用之前，还需要存储后一个节点。最后返回新的头引用。

**复杂度分析**

- 时间复杂度：$O(n)$  ，其中 $n$ 是链表的长度，需要遍历链表一次
- 空间复杂度：$O(1)$ 

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
```

### 2.2 删除链表的节点

> LeetCode：https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。

**示例 1:**

```
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```

**示例 2:**

```
输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

**算法流程：**

- 特例处理： 当应删除头节点 `head` 时，直接返回 `head.next` 即可。
- 初始化： `pre = head , cur = head.next` 。
- 定位节点： 当 `cur` 为空 或 `cur` 节点值等于 `val` 时跳出。
- 保存当前节点索引，即 `pre = cur` 。
- 遍历下一节点，即 `cur = cur.next` 。
- 删除节点： 若 `cur` 指向某节点，则执行 `pre.next = cur.next` ；若 `cur` 指向 `null` ，代表链表中不包含值为 `val` 的节点。
- 返回值： 返回链表头部节点 `head` 即可。

复杂度分析：

- 时间复杂度 $O(N)$ ： $N$ 为链表长度，删除操作平均需循环 $N/2$ 次，最差 $N$ 次。
- 空间复杂度 $O(1)$： `cur, pre` 占用常数大小额外空间。

```java
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        while (head != null) {
            if (head.val != val) break;
            head = head.next;
        }
        ListNode pre = head, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
```
