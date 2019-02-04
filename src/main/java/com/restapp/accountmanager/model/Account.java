package com.restapp.accountmanager.model;


import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Value", length = 64, nullable = false)
    private long value;

    public Account() {
    }

    public Account(int id, long value) {
        this.id = id;
        this.value = value;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + id +
                ", value=" + value +
                '}';
    }
}
