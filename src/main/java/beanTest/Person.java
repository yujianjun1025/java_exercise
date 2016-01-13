package beanTest;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-7-27 Time: 下午9:09 To change this template use File | Settings |
 * File Templates.
 */
public class Person {

    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
