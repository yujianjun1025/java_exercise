package SerializationTest;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/16/14
 * Time: 12:14 PM
 */
public class TmpBean {
    @Override
    public String toString() {
        return "TmpBean{" +
                "city='" + city + '\'' +
                '}';
    }

    public TmpBean() {
    }

    public TmpBean(String city) {
        this.city = city;
    }

    private String city;

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }
}
