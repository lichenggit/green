import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by duoyi on 17-3-27.
 */
public class LambdaTest {
    @Test
    public void testSplit() {
        String str = "1,2,3,,";
        System.out.println(str.split("\\,").length);


        String splitString = "\\|";
        String s = "|42345|||";
        s = s + "| ";
        String info[] = s.split(splitString);
        System.out.println(info.length);
        for (int i = 0; i < info.length; i++) {
            System.out.println(info[i] + ">>>> " + i);

        }
    }

    @Test
    public void testRunnable() {
        new Thread(() -> {
            System.out.println("hello world");
        }).start();
        System.out.println("hello e");
    }

    @Test
    public void testSort() {
        Student student1 = new Student();
        student1.setName("1");
        student1.setGrade(1);
        Student student2 = new Student();
        student2.setName("2");
        student2.setGrade(2);
        Student student3 = new Student();
        student3.setName("3");
        student3.setGrade(3);

        List<Student> list = new ArrayList<Student>();
        list.add(student1);
        list.add(student3);
        list.add(student2);
        list.sort((a, b) -> {
            return (a.getGrade() - b.getGrade());
        });
        System.out.println(list);
    }

    @Test
    public void testForEach() {
        List list = Arrays.asList("1", "2", "3");
        list.forEach((a) -> {
            System.out.println(a);
        });
    }

    @Test
    public void testFilter() {
        List list = Arrays.asList("1", "2", "3");
        list.stream().filter((a) -> {
            return !a.equals("2");
        }).forEach((a) -> {
            System.out.println(a);
        });
    }

    @Test
    public void testMap() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        List list = costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testReduce() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        Double total = costBeforeTax.stream().map((cost) -> cost + cost * 0.0).reduce((sum, cost) -> sum + cost).get();
        System.out.println(total);
    }

    @Test
    public void testDistinct() {
        Student student1 = new Student();
        student1.setName("1");
        student1.setGrade(1);
        Student student2 = new Student();
        student2.setName("2");
        student2.setGrade(2);
        Student student3 = new Student();
        student3.setName("2");
        student3.setGrade(2);

        List<Student> students = new ArrayList<Student>();
        students.add(student1);
        students.add(student3);
        students.add(student2);
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 200, 400, 500);
        List<Student> list = students.stream().filter(distinctByKey(p -> p.getName())).collect(Collectors.toList());
        System.out.println(list);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    class Student {
        private String name;
        private Integer grade;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getGrade() {
            return grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "name:" + name + "," + "grade:" + getGrade();
        }

        @Override
        public boolean equals(Object obj) {
            if (name == null) {
                return false;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Student)) {
                return false;
            }
            Student student = (Student) obj;
            return name.equals(student.getName());
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}
