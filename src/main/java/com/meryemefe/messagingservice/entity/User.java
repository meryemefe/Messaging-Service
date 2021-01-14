package com.meryemefe.messagingservice.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User extends EntityBase{

    @Column(name = "USERNAME", length = 255, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @ManyToMany
    @JoinTable(name = "CONTACT_LIST",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CONTACT_ID", referencedColumnName = "ID")})
    private Set<User> contactList;

    @ManyToMany
    @JoinTable(name = "BLOCK_LIST",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BLOCK_ID", referencedColumnName = "ID")})
    private Set<User> blockList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<User> getContactList() {
        return contactList;
    }

    public void setContactList(Set<User> contactList) {
        this.contactList = contactList;
    }

    public Set<User> getBlockList() {
        return blockList;
    }

    public void setBlockList(Set<User> blockList) {
        this.blockList = blockList;
    }
}
