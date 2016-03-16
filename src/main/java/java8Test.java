import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by yjj on 16/1/18.
 */
public class java8Test {


    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }


    public static void interfaceTest() {

        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };

        System.out.println(formula.calculate(100));

        System.out.println(formula.sqrt(1000));
    }

    public static void sortTest() {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");


        //Collections.sort(names, (a, b) -> b.compareTo(a));
        //Collections.sort(names, (a, b) -> a.compareTo(b));

        Collections.sort(names, String::compareTo);


        System.out.println(Joiner.on(" ").join(names));

        Collections.sort(names, (a, b) -> b.compareTo(a));

        System.out.println(Joiner.on(" ").join(names));

    }

    @FunctionalInterface
    interface Convert<F, T> {

        T convert(F from);
    }


    public static void functionTest() {

        Convert<String, Integer> convert = (Integer::valueOf);

        Integer value = convert.convert("123");
        System.out.println(value);


    }

    static class Person {
        String firstName;
        String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String secondName);
    }


    public static void factoryTest() {

        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("firstName", "secondName");

        System.out.printf(ToStringBuilder.reflectionToString(person).concat("\n"));

    }

    public static void funcationTest() {

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("1234"));


        //  Predicate<String> isEmpty = (s) -> s != null && s.length() > 0;


    }


    public static void matchTest() {

        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(1, 11);
        map.put(2, 22);
        map.put(3, 33);

        map.forEach((integer, integer2) -> {
            System.out.println(integer + "\t" + integer2);
        });

    }

    public static void streamTest() {

        int max = 10;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        values.stream().forEach(System.out::println);

        values.parallelStream().sorted((a, b) -> b.compareTo(a));



        System.out.println("------------------------------");
        values.stream().forEach(System.out::println);

    }


    public static void main(String[] args) {

        interfaceTest();
        sortTest();
        functionTest();
        factoryTest();

        funcationTest();
        matchTest();

        streamTest();
        System.out.printf("yy");

    }
}
