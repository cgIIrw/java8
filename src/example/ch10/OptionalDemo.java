package ch10;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 这个类用来测试一些Optional的用法
 */
public class OptionalDemo {

    public static void main(String[] args) {
        Person person = null;
//        Optional<Person> optionalPerson =
        System.out.println(Optional.ofNullable(person).flatMap(Person::getCar).orElse(new Car()));

//        System.out.println(optionalPerson.flatMap(Person::getCar).orElse(null));
//
//        Map<String, String> map = new HashMap<>();
//
//        Optional<String> value = Optional.ofNullable(map.get("key"));
    }

    public static class Person {
        private Optional<Car> car;

        public Optional<Car> getCar() {
            return car;
        }
    }

    public static class Car {
        String name = "carsdads"; // 测试

        public String getName() {
            return name;
        }

        private Optional<Insurance> insurance;

        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    public class Insurance {
        private String name;

        public String getName() {
            return name;
        }
    }
}
