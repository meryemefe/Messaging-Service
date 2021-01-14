package com.meryemefe.messagingservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "SENDER_ID")
    @ElementCollection(targetClass = User.class)
    @JsonIgnore
    private User sender;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "RECEIVER_ID")
    @ElementCollection(targetClass = User.class)
    @JsonIgnore
    private User receiver;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "TIME_TO_SEND")
    @Temporal(TemporalType.DATE)
    private Date timeToSent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimeToSent() {
        return timeToSent;
    }

    public void setTimeToSent(Date timeToSent) {
        this.timeToSent = timeToSent;
    }
}
