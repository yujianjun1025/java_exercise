package SerializationTest;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/16/14
 * Time: 10:55 AM
 */


public class ObjectMapTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {


        test1();
    }

    public static void test1() throws IOException {

        List<User> users = Lists.newArrayList();
        for (Integer i = 0; i < 3; i++) {
            User guestUser = new User(2L, "guest");
            users.add(guestUser);
        }

        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonString2 = mapper.writeValueAsString(users);
        System.out.println(jsonString2);
        List<User> groupList2 = JSONArray.parseArray(jsonString2, User.class);
        for (Integer i = 0; i < groupList2.size(); i++) {
            System.out.println(groupList2.get(i));
        }

    }

    static class User {
        private Long id;
        private String name;

        User() {
        }

        User(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {

            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


}
