package com.asif.projectmanagementsystem1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
