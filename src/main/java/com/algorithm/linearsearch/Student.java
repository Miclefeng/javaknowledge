package com.algorithm.linearsearch;

import java.sql.Struct;
import java.util.Objects;

/**
 * @author miclefengzss
 * 2021/8/2 上午9:12
 */
public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    //    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//
//        if (obj == this) {
//            return true;
//        }
//
//        if (this.getClass() != obj.getClass()) {
//            return false;
//        }
//
//        Student anther = (Student) obj;
//        return this.name.equals(anther.name);
//    }
}
