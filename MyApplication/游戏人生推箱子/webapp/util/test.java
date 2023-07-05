package sxt.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @create
 */
public class test {
    public static void main(String[] args) {
        List blks  = new ArrayList();
        int[][] box={
                {0,0,1,1,1,0,0,0,},
                {0,0,1,3,1,0,0,0,},
                {0,0,1,2,1,1,1,1,},
                {1,1,1,4,2,4,3,1,},
                {1,3,2,4,2,1,1,1,},
                {1,1,1,1,4,1,0,0,},
                {0,0,0,1,3,1,0,0,},
                {0,0,0,1,1,1,0,0,}
        };
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box.length; j++) {
                if (box[i][j] == 3) {
                    blks.add((i+1)*10+j);
                }
            }
        }
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box.length; j++) {
                if (blks.contains((i+1)*10+j)){
                    System.out.println("哈哈哈");
                }
            }
        }

    }
}
