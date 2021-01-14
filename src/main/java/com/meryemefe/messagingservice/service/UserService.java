package com.meryemefe.messagingservice.service;

import com.meryemefe.messagingservice.entity.Message;
import com.meryemefe.messagingservice.entity.User;
import com.meryemefe.messagingservice.model.MessageDTO;
import com.meryemefe.messagingservice.model.UserDTO;
import com.meryemefe.messagingservice.repo.MessageRepository;
import com.meryemefe.messagingservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private static final String USER_NOT_FOUND = "User is not found!";

    private UserRepository userRepository;
    private MessageRepository messageRepository;

    @Autowired
    public UserService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public User save(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encoder.encode(userDTO.getPassword()));

        return userRepository.save(user);
    }

    public User update(long id, UserDTO userDTO){
        Optional<User> byId = userRepository.findById(id);

        if (byId.isPresent()){
            User user = byId.get();
            user.setUsername(userDTO.getUsername());
            user.setPassword(encoder.encode(userDTO.getPassword()));
            return userRepository.save(user);
        }
        throw new IllegalArgumentException(USER_NOT_FOUND);
    }

    public User delete(long id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            User user = byId.get();
            user.setActive(false);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException(USER_NOT_FOUND);
    }

    private Set<User> getUsers(Set<Long> idList){
        Set<User> userList = new HashSet<>();
        for (long id: idList){
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()){
                userList.add(user.get());
            } else {
                throw new IllegalArgumentException(USER_NOT_FOUND);
            }
        }
        return userList;
    }

    public User updateContactList(long id, Set<Long> contactIds){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            Set<User> contactList = getUsers(contactIds);
            user.get().setContactList(contactList);
            userRepository.save(user.get());
            return user.get();
        }
        throw new IllegalArgumentException((USER_NOT_FOUND));
    }

    public User updateBlockList(long id, Set<Long> blockIds){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            Set<User> blockList = getUsers(blockIds);
            user.get().setBlockList(blockList);
            userRepository.save(user.get());
            return user.get();
        }
        throw new IllegalArgumentException((USER_NOT_FOUND));
    }

    public Message sendMessage(long senderId, long receiverId, MessageDTO messageDTO){

        Optional<User> sender = userRepository.findById(senderId);
        Optional<User> receiver = userRepository.findById(receiverId);

        if( sender.isPresent() && receiver.isPresent()){
            Message message = new Message();
            message.setSender(sender.get());
            message.setReceiver(receiver.get());
            message.setText(messageDTO.getMessageBody());
            message.setTimeToSent(new Date());

            return messageRepository.save(message);
        }
        throw new IllegalArgumentException(USER_NOT_FOUND);
    }

}