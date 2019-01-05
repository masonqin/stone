package com.qinxc.basic;


import java.util.Comparator;

/**
 * Created by qxc on 2018/5/15.
 */
public class LambdaTest {

    public static final Comparator<Person> BY_FIRST =
            (lhs, rhs) -> lhs.firstName.compareTo(rhs.lastName);

    public void staticFunction() {
        System.out.println("Test static function");
    }

    public static void main(String[] args) throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
            return;
        } finally {
            System.out.println("Hello World!");
        }
    }

}

class Person {
    String firstName;
    String lastName;
}

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}
