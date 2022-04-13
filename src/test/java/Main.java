import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/4/11 14:33
 */
public class Main {

    public static void main(String[] args) {
//        test01();
        test02();
    }

    private static void test02() {

        //step1:输入
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
//        String s ="3,9,7,8";
        String[] split = s.split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if( Integer.valueOf(split[i])<1 || Integer.valueOf(split[i])>9) {
                System.out.println(-1);
            }
            Integer integer = Integer.valueOf(split[i]);
            if (integer==2||integer==5) {
            }
            list.add(integer);

        }
        if (list.contains(5)&&list.contains(2)){
            System.out.println(-1);
        }
        List list2 =new ArrayList();
        list2.addAll(list);
         //step2：排序且获取最大值：
         list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        Integer max = list.get(list.size() - 1);
        ArrayList<Integer> list69 = new ArrayList<>();
        list69.add(6);
        list69.add(9);

        ArrayList<Integer> list25 = new ArrayList<>();
        list25.add(2);
        list25.add(5);

        //step3：组数：三个条件，位数，顺序
        //位数
        int count =0;
        int element =-1;
        for (int i = 0; i < list.size(); i++) {

            for (int j = 0; j<list.size(); j++) {
                Integer x = list.get(j);
                if (i <1){
                    count ++;
                    if (count ==max) {
                        System.out.println(element);
                        break;
                    }
                    continue;
                }
                for (int k = 0; k < list.size() ; k++) {
                    if  ((j==k)){
                        continue;
                    }
                    Integer y = list.get(k);

                    if (i <2){
                        count ++;
                        element=x*(int)Math.pow(10,1)+y*(int)Math.pow(10,0);
                        if (count ==max) {
                            System.out.println(element);
                            break;

                        }
                        continue;

                    }
                    for (int l = 0; l < list.size() ; l++) {
                        Integer z = list.get(l);
                        if  ( (k==i) && (k==j)){
                            continue;
                        }

                        if (i <3){
                            count ++;
                            element=x*(int)Math.pow(10,2)+y*(int)Math.pow(10,1)+z*(int)Math.pow(10,1);
                            if (count ==max) {
                                System.out.println(element);
                                break;
                            }
                            continue;

                        }
                        for (int m = 0; m <list.size()   ; m++) {
                            if  ( (m==i) && (m==j)  && (m==k)){
                                continue;
                            }
                            Integer u = list.get(m);
                            element =
                                    x*(int)Math.pow(10,3)  +
                                            y*(int)Math.pow(10,2)  +
                                            z*(int)Math.pow(10,1)  +
                                            u;
                            if (count ==max) {
                                System.out.println(element);
                                break;
                            }
                        }

                    }

                }
            }

        }
        



    }

//    private static void test01() {
//
//        //step 0 ：输入
//        Scanner scanner = new Scanner(System.in);
//        String s1 = scanner.nextLine();
//        String s2 = scanner.nextLine();
//        scanner.close();
//        //step 1:排序
//        String[] split = s1.split(",");
//        int[] ints = new int[split.length];
//        for (int i = 0; i < split.length; i++) {
//            ints[i] = Integer.parseInt(split[i]);
//        }
//
//        for (int i = 0; i < ints.length; i++) {
//            for (int j = i; j <ints.length-i ; j++) {
//                int temp =0;
//                if ( ints[i]>ints[j]){
//                    temp = ints[i];
//                    ints[i] =ints[j];
//                    ints[j] =temp;
//                }
//            }
//        }
//        int limit = Integer.parseInt(s2);
//        int sum =0;
//        for (int i = 0; i < ints.length; i++) {
//            //step 2：累加知道超过前一个
//            sum +=ints[i];
//            //step3 ：输出
//            if (sum > limit){
//                System.out.println(i);
//            }
//        }
//
//
//
//    }
}
