package ch08;

/**
 * 这个例子用来说明调用重载方法时，编译器如何处理
 */
public class LambdaTestDemo {
    public static void main(String[] args) {
        int a = 10;
        Runnable r1 = () -> {
//            int a = 2;
            int b = 3;
        };

        doSomeThing((Task) () -> System.out.println());
    }

    public static void doSomeThing(Runnable r) {
        System.out.println("Runnable");
    }

    public static void doSomeThing(Task t) {
        System.out.println("Task");
    }

    @FunctionalInterface
    public static interface Task {
        public void todo();
    }
}


