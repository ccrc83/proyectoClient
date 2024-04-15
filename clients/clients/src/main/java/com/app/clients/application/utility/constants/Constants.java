package com.app.clients.application.utility.constants;

public class Constants {
    public static final int CODE_200 = 200;
    public static final int CODE_400 = 400;
    public static final int CODE_422 = 422;
    public static final int CODE_500 = 500;

    public static final String MESSAGE_200 = "Consulta Exitosa.";
    public static final String MESSAGE_400 = "Falta algun parámetro en la cabecera";
    public static final String MESSAGE_422 = "Error servicio Externo";
    public static final String MESSAGE_500 = "Error Desconocido";


    public static final String PARAMETER_AUTHORIZED = "result";
    public static final String PARAMETER_OK = "OK";

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String MESSAGE_OK = "OK";
    public static final String MESSAGE_BAD_REQUEST = "Bad Request";
    public static final String MESSAGE_UNPROCESSABLE_ENTITY = "Unprocessable Entity";
    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String MESSAGE_UNKNOWN_STATUS = "Unknown Status";

    public static final String KEY_DATA = "data";
    public static final String KEY_CODE = "code";
    public static final String KEY_MESSAGE = "message";
}
