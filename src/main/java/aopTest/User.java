package aopTest;

import org.springframework.stereotype.Repository;

/**
 * Created by yjj on 15/10/17.
 */

@Repository
public class User {

    public void add(){

        System.out.println("add one user");

    }
}
