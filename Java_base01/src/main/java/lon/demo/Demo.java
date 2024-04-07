package lon.demo;

/**
 * @projectName: lon_base
 * @package: lon.demo
 * @className: demo
 * @author: LONZT
 * @description: TODO
 * @date: 2024/2/26 11:47
 * @version: 1.0
 */
public class Demo {


    public static void main(String[] args) {

        // 假设我们要计算1至100的和
        int n = 100;
        int sum = recursiveSum(n);
        System.out.println("1至" + n + "的和是：" + sum);
    }

    public static int recursiveSum(int n) {
        System.out.println(n);
        if (n <= 1) {
            return n;
        } else {
            return n + recursiveSum(n - 1);
        }
    }
}
