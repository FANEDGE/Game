import java.util.*;

class ArrayRandom {
    public static int[] arr = new int[8];// 存放1-8的随机排列
    protected boolean flag = false;// 用于判断是否需要重新生成

    public ArrayRandom() {
        // 使用Fisher–Yates洗牌算法产生随机数组b，用于随机按钮位置
        int a[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
        for (int i = 0; i < arr.length; i++) {
            int random = (int) (Math.random() * a.length);
            arr[i] = a[random];
            a[random] = a[a.length - 1];
            a = Arrays.copyOf(a, a.length - 1);
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    flag = !flag;
                }
            }
        }
        if (flag) {// 偶数个逆序为false，才会跳过此if
            new ArrayRandom();
        }
    }
}