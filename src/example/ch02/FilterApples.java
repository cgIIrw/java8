import java.util.ArrayList;
import java.util.List;

/**
 * 这段代码旨在说明《Java 8实战》第二章小结的结论：
 * 传递代码,就是将新行为作为参数传递给方法。但在Java 8之前这实现起来很啰嗦。为接
 * 口声明许多只用一次的实体类而造成的啰嗦代码,在Java 8之前可以用匿名类来减少。
 */
public class FilterApples {

    public static void main(String[] args) {

        Apple apple01 = new Apple(50, "red");
        Apple apple02 = new Apple(100, "blue");
        Apple apple03 = new Apple(70, "yellow");
        Apple apple04 = new Apple(90, "green");

        List<Apple> result = new ArrayList<>();
        result.add(apple01);
        result.add(apple02);
        result.add(apple03);
        result.add(apple04);

        List<Apple> list = filterApple(result, x -> x.getColor() == "green");

        System.out.println(list.get(0).getColor());
    }

    public static <T> List<T> filterApple(List<T> list, ApplePredicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}