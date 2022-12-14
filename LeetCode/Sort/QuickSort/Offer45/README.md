# 剑指 Offer 45.把数组排成最小的数

输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

**示例 1:**

```
输入: [10,2]
输出: "102"
```

**示例 2:**

```
输入: [3,30,34,5,9]
输出: "3033459"
```

**提示:**

- `0 < nums.length <= 100`

**说明:**

- 输出结果可能非常大，所以你需要返回一个字符串而不是整数
- 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0

## 解法：自定义排序

主要还是复习一下排序规则。

<img src="https://tva1.sinaimg.cn/large/008vxvgGgy1h90rtr25pcj30ts0mdmz6.jpg" alt="95e81dbccc44f26292d88c509afd68204a86b37d342f83d109fa7aa0cd4a6049-Picture1" style="zoom: 50%;" />

`x` “小于” `y` 表示在排序完成后，数组中 `x` 应该在 `y` 的左边。

## 解法：快排

```java
	public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
       for (int i = 0; i < nums.length; ++i) {
           strs[i] = String.valueOf(nums[i]);
       }
       quickSort(strs, 0, strs.length - 1);
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < strs.length; i++) {
           sb.append(strs[i]);
       }
       return sb.toString();
    }

    public void quickSort(String[] strs, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        String tmp = strs[i];
        while (i < j) {
            while((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            while((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }
```







