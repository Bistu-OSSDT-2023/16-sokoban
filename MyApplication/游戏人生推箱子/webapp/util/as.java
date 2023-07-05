package sxt.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @create
 */
public class as {

    public static void main(String[] args) {
        int[][] box1 = {
                {0, 0, 1, 1, 1, 0, 0, 0, 4, 4, 4, 4},
                {0, 0, 1, 3, 1, 0, 0, 0, 4, 4, 4, 4},
                {0, 0, 1, 2, 1, 1, 1, 1, 4, 4, 4, 4},
                {1, 1, 1, 4, 2, 4, 3, 1, 4, 4, 4, 4},
                {1, 3, 2, 4, 2, 1, 1, 1, 4, 4, 4, 4},
                {1, 1, 1, 1, 4, 1, 0, 0, 4, 4, 4, 4},
                {0, 0, 0, 1, 3, 1, 0, 0, 4, 4, 4, 4},
                {0, 0, 0, 1, 1, 1, 0, 0, 4, 4, 4, 4}
        };
        int[][] box1Rest = {
                {0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 3, 1, 0, 0, 0},
                {0, 0, 1, 2, 1, 1, 1, 1},
                {1, 1, 1, 4, 2, 4, 3, 1},
                {1, 3, 2, 4, 2, 1, 1, 1},
                {1, 1, 1, 1, 4, 1, 0, 0},
                {0, 0, 0, 1, 3, 1, 0, 0},
                {5, 5, 5, 5, 5, 5, 5, 5}
        };
        box1 = box1Rest;
        for (int i = 0; i < box1Rest.length; i++) {
            for (int j = 0; j < box1Rest[0].length; j++) {
                System.out.print(box1[i][j]);
            }
            System.out.println("");
        }


    }
    //    int[] box1 = {1,2,3,4,5};
//    List arr = new ArrayList();
//        arr.add(box1);
//    int[] o = (int[]) arr.get(0);
//        for (int i = 0; i < o.length; i++) {
//        System.out.println(o[i]);
//    }
}
