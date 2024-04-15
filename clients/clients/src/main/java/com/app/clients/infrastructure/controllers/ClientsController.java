package com.app.clients.infrastructure.controllers;

import com.app.clients.application.usecases.ClientsUseCase;
import com.app.clients.application.utility.ResponseErrorMessage;
import com.app.clients.application.utility.ResponseMessage;
import com.app.clients.application.utility.constants.Constants;
import com.app.clients.domain.entities.ClientDTO;
import com.app.clients.domain.entities.Clients;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@SwaggerDefinition
@RequestMapping("clients")
public class ClientsController {

   private  final ClientsUseCase clientsUseCase;

    public ClientsController(ClientsUseCase clientsUseCase) {
        this.clientsUseCase = clientsUseCase;
    }

    @GetMapping("/getAllClients")
    @ApiResponses(value = {
            @ApiResponse(code = Constants.CODE_200, message = Constants.MESSAGE_200, response = ResponseMessage.class),
            @ApiResponse(code = Constants.CODE_400, message = Constants.MESSAGE_400, response = ResponseErrorMessage.class),
            @ApiResponse(code = Constants.CODE_422, message = Constants.MESSAGE_422, response = ResponseErrorMessage.class),
            @ApiResponse(code = Constants.CODE_500, message = Constants.MESSAGE_500, response = ResponseErrorMessage.class) })
    public ResponseEntity<Object>allClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        try {
            Page<Clients> clients = clientsUseCase.getAllClients(page, size);
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            ResponseErrorMessage errorMessage = new ResponseErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        }
    }

    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = Constants.CODE_200, message = Constants.MESSAGE_200, response = ResponseMessage.class),
            @ApiResponse(code = Constants.CODE_400, message = Constants.MESSAGE_400, response = ResponseErrorMessage.class),
            @ApiResponse(code = Constants.CODE_422, message = Constants.MESSAGE_422, response = ResponseErrorMessage.class),
            @ApiResponse(code = Constants.CODE_500, message = Constants.MESSAGE_500, response = ResponseErrorMessage.class) })
    public ResponseEntity<Object>getClientBySharedKey(@RequestParam("sharedKey") String sharedKey) {
        try {
            return ResponseEntity.ok( clientsUseCase.getClientBySharedKey(sharedKey));
        }  catch (Exception e) {
            ResponseErrorMessage errorMessage = new ResponseErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMessage);
        }

    }

    @PostMapping("/createClient")
    @ApiResponses(value = {
            @ApiResponse(code = Constants.CODE_200, message = Constants.MESSAGE_200, response = ResponseMessage.class),
            @ApiResponse(code = Constants.CODE_400, message = Constants.MESSAGE_400, response = ResponseErrorMessage.class),
            @ApiResponse(code = Constants.CODE_422, message = Constants.MESSAGE_422, response = ResponseErrorMessage.class),
            @ApiResponse(code = Constants.CODE_500, message = Constants.MESSAGE_500, response = ResponseErrorMessage.class) })
    public ResponseEntity<Object> createClient(@RequestBody ClientDTO client) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clientsUseCase.createClient(client));
        }  catch (Exception e) {
                ResponseErrorMessage errorMessage = new ResponseErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno del servidor");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(errorMessage);
        }

    }

    @DeleteMapping("/deleteClient")
    @ApiResponses(value = {
            @ApiResponse(code = Constants.CODE_200, message = Constants.MESSAGE_200, response = ResponseMessage.class),
            @ApiResponse(code = Constants.CODE_400, message = Constants.MESSAGE_400, response = ResponseErrorMessage.class),
            @ApiResponse(code = Constants.CODE_422, message = Constants.MESSAGE_422, response = ResponseErrorMessage.class),
            @ApiResponse(code = Constants.CODE_500, message = Constants.MESSAGE_500, response = ResponseErrorMessage.class) })
    public ResponseEntity<Void> deleteClient(@RequestParam("sharedKey") String sharedKey) {

        clientsUseCase.deleteClient(sharedKey);
        return ResponseEntity.noContent().build();
    }


}
