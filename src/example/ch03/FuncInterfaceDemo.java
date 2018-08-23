package ch03;

import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import java.util.function.ToIntFunction;

/**
 * Predicate返回boolean，Consumer没有返回，这里测试Function实例；
 * 所谓函数式接口指包含一个方法的接口，这里的一个方法不包括默认方法；
 */
public class FuncInterfaceDemo {
    public static void main(String[] args) {

        ToIntFunction<Integer> func = x -> 3;
        func.applyAsInt(32);


        // 函数式接口不允许抛异常，但可以这样：
        Function<String, String> func02 = x -> {
            try {
                return "OK!";
            } catch (Exception e) {
                throw new RuntimeException();
            }
        };
    }
}
