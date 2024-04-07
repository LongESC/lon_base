package lon.demo;

/**
 * @projectName: lon_base
 * @package: lon.demo
 * @className: KnapsackProblemComplete
 * @author: LONZT
 * @description: TODO
 * @date: 2024/3/1 16:38
 * @version: 1.0
 */


import java.util.Arrays;
import java.util.function.ToLongBiFunction;
import java.util.stream.IntStream;


/**
 * 完全背包问题
 * @author CSDN编程小猹
 * @date 2023/11/07
 */
public class KnapsackProblemComplete {

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
    static void print(int[][] dp) {
        System.out.println("   " + "-".repeat(63));
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%5d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%5d ".repeat(d.length)) + "%n", array);
        }
    }
    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(1, "青铜", 2, 3),    // c
                new Item(2, "白银", 3, 4),    // s
                new Item(3, "黄金", 4, 5),    // a
        };
        System.out.println(select(items, 9));
    }

    private static int select(Item[] items, int total) {
        int [][]dp=new int[items.length][total+1];
        Item item0=items[0];
        System.out.println(item0);
        //第零行不符合递推式，需要另外赋值
        for (int j=0;j<total+1;j++){
            //装得下
            if (j>=item0.weight){
                dp[0][j]=dp[0][j-item0.weight]+item0.value;
            }
            System.out.println(dp[0][j]);
        }
        print(dp);
        for (int i=1;i<items.length;i++){
            for (int j=0;j<total+1;j++){
                //装得下
                Item item=items[i];
                //上次的最大价值
                int x=dp[i-1][j];
                if (j>=item.weight){
                    dp[i][j]=Integer.max(x,dp[i][j-item.weight]+item.value);
                }
                else {
                    dp[i][j]=x;
                }
            }
        }
        print(dp);
        return dp[items.length-1][total];
    }
    //优化：把缓存结果的数组降维
    private static int select2(Item[] items, int total) {
        int[] dp = new int[total + 1];
        for (Item item : items) {
            for (int j = 0; j < total + 1; j++) {
                if (j >= item.weight) {
                    dp[j]= Integer.max(dp[j], dp[j - item.weight] + item.value);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[total];
    }
}
