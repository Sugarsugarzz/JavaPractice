package Leetcode.ByTags.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * Easy
 */

public class _0118_generate {

    public static void main(String[] args) {

        int numRows = 5;
        List<List<Integer>> res = generate(numRows);
        for (List<Integer> list: res) {
            for (Integer v : list) {
                System.out.print(v + "  ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> generate(int numRows) {

        // 一、自己的方法
//        List<List<Integer>> res = new ArrayList<>();
//        List<Integer> tmp = null;
//
//        for (int i = 0; i < numRows; i++) {
//            List<Integer> list = new ArrayList<>();
//            list.add(1);
//            if (tmp != null) {
//                for (int j = 0; j < tmp.size() - 1; j++) {
//                    list.add(tmp.get(j) + tmp.get(j+1));
//                }
//            }
//            res.add(list);
//            tmp = list;
//
//            if (i > 0) {
//                list.add(1);
//            }
//        }
//        return res;

        // 二、官方动态规划（好像和自己写的没啥区别...）
        List<List<Integer>> res = new ArrayList<>();

        if (numRows == 0)
            return res;

        res.add(new ArrayList<>());
        res.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> preRow = res.get(rowNum - 1);

            row.add(1);

            for (int j = 1; j < rowNum; j++) {
                row.add(preRow.get(j-1) + preRow.get(j));
            }

            row.add(1);

            res.add(row);
        }

        return res;
    }
}
