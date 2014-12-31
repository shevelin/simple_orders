package com.scotch.simpleorders.entity;

/**
 * Created by sutupin on 25.12.2014.
 */


//@Entity
//@Table(name="customer")
public class Customer {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name="name")
    private String name;

//    @Column(name="password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", password=" + password +"]";
    }

}
