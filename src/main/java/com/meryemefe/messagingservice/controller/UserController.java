package com.meryemefe.messagingservice.controller;

import com.meryemefe.messagingservice.entity.Message;
import com.meryemefe.messagingservice.entity.User;
import com.meryemefe.messagingservice.model.MessageDTO;
import com.meryemefe.messagingservice.model.UserDTO;
import com.meryemefe.messagingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<User> post(@Valid @RequestBody UserDTO userDTO){
        User user = userService.save(userDTO);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<User> update(@PathVariable long id, @Valid @RequestBody UserDTO userDTO){
        User user = userService.update(id, userDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<User> delete(@PathVariable long id){
        User user = userService.delete(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/contact-list")
    @ResponseBody
    public ResponseEntity<User> updateContact(@RequestParam(name = "userId", defaultValue = "") long userId,
                                              @RequestParam(name = "contactId", defaultValue = "") Set<Long> contactList){
        User user = userService.updateContactList(userId, contactList);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/block-list")
    @ResponseBody
    public ResponseEntity<User> updateBlock(@RequestParam(name = "userId", defaultValue = "") long userId,
                                              @RequestParam(name = "blockId", defaultValue = "") Set<Long> blockList){
        User user = userService.updateBlockList(userId, blockList);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/send-message")
    @ResponseBody
    public ResponseEntity<Message> sendMessage(@RequestParam(name = "senderId", defaultValue = "") long senderId,
                                            @RequestParam(name = "receiverId", defaultValue = "") long receiverId,
                                            @Valid @RequestBody MessageDTO messageDTO){
        Message message = userService.sendMessage(senderId, receiverId, messageDTO);
        return ResponseEntity.ok(message);
    }

}
