package com.yhbnb.entity;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 7419229779731522702L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    
    @Column(name="user_name")
    private String name;
    
    @Column(length = 50)
    private String account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}