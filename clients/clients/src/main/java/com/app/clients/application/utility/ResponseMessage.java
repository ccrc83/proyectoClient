package com.app.clients.application.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private Object data;
    private int code;
    private String message;
    private String pagination;
}
