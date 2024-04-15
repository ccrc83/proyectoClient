package com.app.clients.application.utility.queries;

import com.app.clients.application.utility.ResponseMessage;
import com.app.clients.application.utility.constants.Constants;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EvaluateResponse {
    private EvaluateResponse() {
    }

    public static ResponseEntity<Object> build(JSONObject configToResponse)
    {
        try {
            if (validResponse(configToResponse))
            {
                ResponseMessage responseMessage = new ResponseMessage(configToResponse.toMap(), HttpStatus.OK.value(), HttpStatus.OK.name(), null);
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);
            }else {
                ResponseMessage responseMessage = new ResponseMessage(configToResponse.toMap(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name(), null);
                return new ResponseEntity<>(responseMessage, HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            ResponseMessage responseMessage = new ResponseMessage(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), null);
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static boolean validResponse(JSONObject responseAutorization){
        return (responseAutorization.get(Constants.PARAMETER_AUTHORIZED).equals(Constants.PARAMETER_OK)) ;

    }

    public static ResponseEntity<Object> buildWithOutValidation(JSONObject configToResponse)
    {
        try {
                ResponseMessage responseMessage = new ResponseMessage(configToResponse.toMap(), HttpStatus.OK.value(), HttpStatus.OK.name(), null);
                return new ResponseEntity<>(responseMessage, HttpStatus.OK);

        }catch (Exception e){
            ResponseMessage responseMessage = new ResponseMessage(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), null);
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static ResponseEntity<Object> buildWithOutValidation(Object configToResponse)
    {
        try {
            ResponseMessage responseMessage = new ResponseMessage(configToResponse, HttpStatus.OK.value(), HttpStatus.OK.name(), null);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);

        }catch (Exception e){
            ResponseMessage responseMessage = new ResponseMessage(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), null);
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
