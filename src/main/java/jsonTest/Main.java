package jsonTest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-11-12 Time: 下午7:42 To change this template use File | Settings
 * | File Templates.
 */
public class Main {

    public enum Degree {
        A, B, C, D
    }

    public static class Student implements Serializable {
        private String name;
        private Integer score;
        private Degree degree;

        Student() {

            name = "name";
            score = 100;
            degree = Degree.A;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public Degree getDegree() {
            return degree;
        }

        public void setDegree(Degree degree) {
            this.degree = degree;
        }

        public void setDegree(String degree) {
            this.degree = Degree.valueOf(degree.toUpperCase());
        }
    }

    private static void setStudent(Student student) {
        student.setName("name");
        student.setScore(100);
        student.setDegree(Degree.A);
    }

    public static void main(String[] args) {

        Student student = new Student();
        student.setName("aa");
        // setStudent(student);
        // System.out.println(ToStringBuilder.reflectionToString(student));
        String oneStrudent = JSON.toJSONString(student);
        System.out.println(oneStrudent);

        List<Student> studentList = Lists.newArrayList();

        for (Integer i = 0; i < 10; i++) {

            Student student1 = new Student();
            student1.setScore(i * 1);
            studentList.add(student1);
        }

        String manyStudent = JSON.toJSONString(studentList);

        System.out.println(manyStudent);

        List<Student> students = JSON.parseArray(manyStudent, Student.class);
        for (Student student1 : studentList) {
            System.out.println(ToStringBuilder.reflectionToString(student1));
        }

    }
}
