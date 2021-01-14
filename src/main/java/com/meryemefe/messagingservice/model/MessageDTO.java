package com.meryemefe.messagingservice.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MessageDTO {

    @NotBlank
    @Size(max = 255, min = 1)
    private String messageBody;

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
