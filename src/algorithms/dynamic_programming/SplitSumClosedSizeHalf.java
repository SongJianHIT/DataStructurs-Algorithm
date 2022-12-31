/**
 * @projectName Algorithm
 * @package algorithms.dynamic_programming
 * @className algorithms.dynamic_programming.SplitSumClosedSizeHalf
 */
package algorithms.dynamic_programming;

/**
 * SplitSumClosedSizeHalf
 * @description 分裂数组2
 * @author SongJian
 * @date 2022/12/31 10:28
 * @version
 */
public class SplitSumClosedSizeHalf {

    /**
     * ================================================================================================================
     * 暴力递归
     * @title right
     * @author SongJian
     * @param: arr
     * @updateTime 2022/12/31 10:32
     * @return: int
     * @throws
     * @description
     */
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            // 长度是偶数
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {
            // 长度是奇数
            return Math.max(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
        }
    }

    /**
     * @title process
     * @author SongJian
     * @param: arr
     * @param: i
     * @param: picks
     * @param: rest
     * @updateTime 2022/12/31 10:32
     * @return: int
     * @throws
     * @description arr[i....]自由选择，挑选的个数一定要是 picks 个，累加和 <=rest, 离 rest 最近的返回
     */
    public static int process(int[] arr, int i, int picks, int rest) {
        if (i == arr.length) {
            // 不是 picks 个数，则无效，返回 -1
            return picks == 0 ? 0 : -1;
        } else {
            int p1 = process(arr, i + 1, picks, rest);
            int p2 = -1;
            int next = -1;
            if (arr[i] <= rest) {
                next = process(arr, i + 1, picks - 1, rest - arr[i]);
            }
            if (next != -1) {
                // 需要保证后续是个有效的，才会计算p2
                p2 = arr[i] + next;
            }
            return Math.max(p1, p2);
        }
    }

    /**
     * ================================================================================================================
     * 动态规划
     * @title dp1
     * @author SongJian
     * @param: arr
     * @updateTime 2022/12/31 10:46
     * @return: int
     * @throws
     * @description
     */
    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        // 向上取整
        int M = (N + 1) / 2;
        int[][][] dp = new int[N + 1][M + 1][sum + 1];
        // 最初认为所有都是非法的
        for (int i = 0; i <= N; ++i) {
            for (int j = 0; j <= M; ++j) {
                for (int k = 0; k <= sum; ++k) {
                    dp[i][j][k] = -1;
                }
            }
        }
        // 填 basecase
        for (int rest = 0; rest <= sum; ++rest) {
            dp[N][0][rest] = 0;
        }
        for (int i = N - 1; i >= 0; --i) {
            for (int picks = 0; picks <= M; ++picks) {
                for (int rest = 0; rest <= sum; ++rest) {
                    int p1 = dp[i + 1][picks][rest];
                    int p2 = -1;
                    int next = -1;
                    if (picks - 1 >= 0 && arr[i] <= rest) {
                        next = dp[i + 1][picks - 1][rest - arr[i]];
                    }
                    if (next != -1) {
                        // 需要保证后续是个有效的，才会计算p2
                        p2 = arr[i] + next;
                    }
                    dp[i][picks][rest] =  Math.max(p1, p2);
                }
            }
        }
        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][sum];
        } else {
            return Math.max(dp[0][arr.length / 2][sum], dp[0][(arr.length / 2) + 1][sum]);
        }
    }


    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}

