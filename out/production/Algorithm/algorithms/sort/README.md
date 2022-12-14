# 排序算法

## 排序算法总结

### 时间复杂度

|          | 时间复杂度 | 额外空间复杂度 |        稳定性        |
| :------: | :--------: | :------------: | :------------------: |
| 选择排序 |  $O(N^2)$  |     $O(1)$     |          无          |
| 冒泡排序 |  $O(N^2)$  |     $O(1)$     |   有（相等时不换）   |
| 插入排序 |  $O(N^2)$  |     $O(1)$     | 有（相等时不向前插） |
| 归并排序 | $O(NlogN)$ |     $O(N)$     | 有（先拷贝左组稳定） |
| 随机快排 | $O(NlogN)$ |   $O(logN)$    |          无          |
|  堆排序  | $O(NlogN)$ |     $O(1)$     |          无          |
| 计数排序 |   $O(N)$   |     $O(M)$     |          有          |
| 基数排序 |   $O(N)$   |     $O(N)$     |          有          |

其中，基数排序和计数排序都属于 **不基于比较** 的排序算法。

### 排序的稳定性

稳定性是指同样大小的样本再排序之后不会改变相对次序。

- 对基础类型而言，稳定性毫无意义
- 对非基础类型而言，稳定性有重要意义

### 工程上排序的改进

思考方向：

- 稳定性的考虑

  `Arrays.sort()` 的讨论

-  $O(NlogN)$ 与 $O(N^2)$ 的各自的优势







