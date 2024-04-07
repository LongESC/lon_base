package lon.demo;

/**
 * @projectName: lon_base
 * @package: lon.demo
 * @className: KnapsackProblem
 * @author: LONZT
 * @description: TODO
 * @date: 2024/3/1 10:06
 * @version: 1.0
 */

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 背包问题-动态规划
 * @author CSDN编程小猹
 * @date 2023/11/07
 */
public class KnapsackProblem {
    static class Item {
        int index;
        String name;
        int weight;
        int value;

        public Item(int index, String name, int weight, int value) {
            this.index = index;
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item(" + name + ")";
        }
    }
    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(1, "黄金", 10, 35000),
                new Item(2, "宝石", 1, 2400),
                new Item(3, "白银", 5, 30),
                new Item(4, "钻石", 2, 10000),
                new Item(5, "钻石", 1, 20000),
        };
        System.out.println(select2(items, 12));
    }

    //算法优化：数组降维
    static int select2(Item[] items, int total) {
        int[] dp = new int[total + 1];
        for (Item item : items) {
            //注意此处要逆序
            for (int j = total; j > 0; j--) {
                if (j >= item.weight) { // 装得下
                    dp[j] = Integer.max(dp[j], item.value + dp[j - item.weight]);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[total];
    }

    private static int select(Item[] items, int total) {
        int[][] dp = new int[items.length][total + 1];
        //第零行不符合递推公式，需要特殊处理
        Item item0 = items[0];
        for (int j = 0; j < total + 1; j++) {
            if (j >= item0.weight) {
                // 装得下
                dp[0][j] = item0.value;
            } else {
                // 装不下
                dp[0][j] = 0;
            }
        }
        //调试打印
        print(dp);
        for (int i=1;i<dp.length;i++){
            Item item=items[i];
            for (int j=0;j<total+1;j++){
                int x=dp[i-1][j];
                if (j>item.weight){
                    dp[i][j]=Integer.max(x,dp[i-1][j-item.weight]+item.value);
                }
                else {
                    dp[i][j]=x;
                }
            }
        }
        print(dp);
        return dp[dp.length-1][total];
    }
    static void print(int[][] dp) {
        System.out.println("   " + "-".repeat(63));
        System.out.println(1);
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%5d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%5d ".repeat(d.length)) + "%n", array);
        }
    }

}
