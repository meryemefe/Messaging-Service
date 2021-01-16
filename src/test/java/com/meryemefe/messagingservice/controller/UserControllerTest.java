package com.meryemefe.messagingservice.controller;

import com.meryemefe.messagingservice.entity.Message;
import com.meryemefe.messagingservice.entity.User;
import com.meryemefe.messagingservice.model.MessageDTO;
import com.meryemefe.messagingservice.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController controller;

    @Test
    void post1() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("meryem");
        userDTO.setPassword("pass123");
        ResponseEntity<User> response = controller.post(userDTO);
        // Assert
        assertEquals(Objects.requireNonNull(response.getBody()).getUsername(), userDTO.getUsername());
    }

    @Test
    void post2() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("efe");
        userDTO.setPassword("xxx123");
        ResponseEntity<User> response = controller.post(userDTO);
        // Assert
        assertEquals(Objects.requireNonNull(response.getBody()).getUsername(), userDTO.getUsername());
    }

    @Test
    void post3() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("sungur");
        userDTO.setPassword("sefeXX");
        ResponseEntity<User> response = controller.post(userDTO);
        // Assert
        assertEquals(Objects.requireNonNull(response.getBody()).getUsername(), userDTO.getUsername());
    }

    @Test
    void post4() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("firat");
        userDTO.setPassword("efe123");
        ResponseEntity<User> response = controller.post(userDTO);
        // Assert
        assertEquals(Objects.requireNonNull(response.getBody()).getUsername(), userDTO.getUsername());
    }

    @Test
    void post5() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("bahar");
        userDTO.setPassword("bhrBHR");
        ResponseEntity<User> response = controller.post(userDTO);
        // Assert
        assertEquals(Objects.requireNonNull(response.getBody()).getUsername(), userDTO.getUsername());
    }

    @Test
    void update() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("meryem_efe");
        userDTO.setPassword("1234");
        ResponseEntity<User> response = controller.update(1, userDTO);
        // Assert
        assertEquals(Objects.requireNonNull(response.getBody()).getId(), 1);
    }


    @Test
    void delete() {
        ResponseEntity<User> response = controller.delete(3);
        assertFalse(Objects.requireNonNull(response.getBody()).isActive());
    }

    @Test
    void updateContact() {
        Set<Long> contacts = new HashSet<>();
        contacts.add((long) 2);
        contacts.add((long) 3);
        ResponseEntity<User> response = controller.updateContact(1, contacts);
        assertEquals(Objects.requireNonNull(response.getBody()).getContactList().size(), contacts.size());
    }

    @Test
    void updateBlock() {
        Set<Long> blocked = new HashSet<>();
        blocked.add((long) 4);
        blocked.add((long) 5);
        ResponseEntity<User> response = controller.updateBlock(1, blocked);
        assertEquals(Objects.requireNonNull(response.getBody()).getBlockList().size(), blocked.size());
    }

    @Test
    void sendMessage() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessageBody("Hello");
        ResponseEntity<Message> response = controller.sendMessage(1,3, messageDTO);
        assertEquals(Objects.requireNonNull(response.getBody()).getText(), messageDTO.getMessageBody());
    }

}