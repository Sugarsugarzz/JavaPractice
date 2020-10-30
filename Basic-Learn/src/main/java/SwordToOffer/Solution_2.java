package SwordToOffer;

public class Solution_2 {

    /**
     * 题目：请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * 解析：从前往后替换，后面的字符要不断往后移动，要多次移动，所以效率低下
     *      从后往前，先计算需要多少空间，然后从后往前移动，则每个字符只为移动一次，这样效率更高一点。
     */


    public static String replaceSpace(StringBuffer str) {

        //solution1
        //return str.toString().replace(" ", "%20");

        //solution2
        int spacenum = 0;  //记录空格数
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                spacenum++;
        }

        int indexold = str.length() - 1;  //原来字符串的光标
        int newlength = str.length() + spacenum*2;  //新字符串的长度
        int indexnew = newlength - 1;  //新字符串的光标
        str.setLength(newlength);  //设置长度，防止越界
        while (indexold >= 0 && indexold < newlength) {  //遇到空格就写入%20，不是空格就写入原字符
            if (str.charAt(indexold) == ' ') {
                str.setCharAt(indexnew--, '0');
                str.setCharAt(indexnew--, '2');
                str.setCharAt(indexnew--, '%');
            } else {
                str.setCharAt(indexnew--, str.charAt(indexold));
            }

            indexold--;
        }

        return str.toString();

    }


    public static void main(String[] args) {

        StringBuffer str = new StringBuffer("We Are Happy.");
        System.out.println(replaceSpace(str));

    }
}
