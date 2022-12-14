# 283. 移动零

## 题目描述

给定一个数组 `nums`，编写一个函数将所有 `0` 移动到数组的末尾，同时保持非零元素的相对顺序。

**请注意** ，必须在不复制数组的情况下原地对数组进行操作。

**示例 1:**

```
输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]
```

**示例 2:**

```
输入: nums = [0]
输出: [0]
```

**提示**:

- `1 <= nums.length <= 104`
- `-231 <= nums[i] <= 231 - 1`

## 分析与思路

本题的表述很直观了，就是把数组划分为两个不同性质的区间：

- 一个区间要求元素不等于 `0`
- 另一个区间要求元素等于 `0`

很显然，这就是快排的简单版本，只需要修改一下 `if` 条件即可。

具体而言，使用两个指针（也算是双指针法），左指针 `point` 指向 **当前已经处理好的序列的尾部（也就是不等于0的区间尾部）**，右指针 `i`  **指向待处理序列的头部**，对数组进行遍历：

- 当 `arr[i]=0` 时，不进行任何处理，直接 `continue` 下次循环
- 当 `arr[i]!=0` 时，把当前元素 `arr[i]` 与区间下一个元素 `arr[point+1]` 进行交换，并扩大区间范围 `ponit++`

![IMG_5ADCFED97E7B-1](https://tva1.sinaimg.cn/large/008vxvgGgy1h89647qngfj31120u0te6.jpg)

因为没有出现任何指针的回退，因此时间复杂度： $O(N)$ ，空间复杂度为 $O(1)$ 。

```java
class Solution {
    public void moveZeroes(int[] nums) {
        if(nums==null) {
            return;
        }
        // p2 维护了非0边界
        int p2 = -1;
        for(int p1 = 0; p1 < nums.length; p1++) {
            //当前元素!=0，就与边界尾部的下一个元素进行交换，并且扩大边界
            if(nums[p1]!=0) {
                int tmp = nums[p1];
                nums[p1] = nums[p2+1];
                nums[p2+1] = tmp;
                p2++;
            }
        }
    }
}
```

