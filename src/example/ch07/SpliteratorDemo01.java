package ch07;

/**
 * 本实例用传统迭代方法统计单词数量
 */
public class SpliteratorDemo01 {

    public final static String SENTENCE = "adad sada aaaaaa    asd  s   fffsa  afasf";

    // 传统的迭代版本，
    public static int countWords(String s) {
        int counter = 0;
        boolean lastSpace = true;

        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace)
                    counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println(countWords(SENTENCE));
    }
}
