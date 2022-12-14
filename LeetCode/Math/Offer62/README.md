# 剑指 Offer 62. 圆圈中最后剩下的数字

## 题目描述

0,1,···,n-1 这 n 个数字排成一个圆圈，从数字 0 开始，每次从这个圆圈里删除第 m 个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4 这 5 个数字组成一个圆圈，从数字0开始每次删除第 3 个数字，则删除的前 4 个数字依次是 2、0、4、1，因此最后剩下的数字是 3。

**示例 1：**

```
输入: n = 5, m = 3
输出: 3
```

**示例 2：**

```
输入: n = 10, m = 17
输出: 2
```

**限制：**

- `1 <= n <= 10^5`
- `1 <= m <= 10^6`

## 解题思路

重点：只关心最后活着的人的序号变化。

> 这个问题实际上是约瑟夫问题，这个问题描述是：
>
> N个人围成一圈，第一个人从1开始报数，报M的将被杀掉，下一个人接着从1开始报。如此反复，最后剩下一个，求最后的胜利者。

下面这个例子是`N=8 m=3`的例子

我们定义`F(n,m)`表示最后剩下那个人的`索引号`，因此我们只关系最后剩下来这个人的索引号的变化情况即可。

![image-20221220093412676](https://tva1.sinaimg.cn/large/008vxvgGgy1h9a0w88eipj313w0matax.jpg)

从8个人开始，每次杀掉一个人，去掉被杀的人，然后把杀掉那个人之后的第一个人作为开头重新编号

- 第一次C被杀掉，人数变成7，D作为开头，（最终活下来的G的编号从6变成3）
- 第二次F被杀掉，人数变成6，G作为开头，（最终活下来的G的编号从3变成0）
- 第三次A被杀掉，人数变成5，B作为开头，（最终活下来的G的编号从0变成3）
- 以此类推，当只剩一个人时，他的编号必定为0！（重点！）

在我们知道了 G 的索引号的变化过程，那么我们反推一下 从 N = 7 到 N = 8 的过程。如何才能将 N = 7 的排列变回到 N = 8 呢？

我们先把被杀掉的 C 补充回来，然后右移 m 个人，发现溢出了，再把溢出的补充在最前面，经过这个操作就恢复了 N = 8 的排列了！

![image-20221220093534571](https://tva1.sinaimg.cn/large/008vxvgGgy1h9a0xmw8pwj313w0k8jut.jpg)

再把`n=1`这个最初的情况加上，就得到递推公式：
$$
f(n, m)= \begin{cases}0 & n=1 \\ {[f(n-1, m)+m] \% n} & n>1\end{cases}
$$

## 代码

```java
public int lastRemaining(int n, int m) {
    int x = 0;
    for (int i = 2; i <= n; i++) {
        x = (x + m) % i;
    }
    return x;
}
```

## 参考

https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solutions/178427/huan-ge-jiao-du-ju-li-jie-jue-yue-se-fu-huan-by-as/