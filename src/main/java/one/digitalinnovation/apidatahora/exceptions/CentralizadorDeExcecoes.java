package one.digitalinnovation.apidatahora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.zone.ZoneRulesException;

@RestControllerAdvice
public class CentralizadorDeExcecoes{

    private Erro buildErro(RuntimeException e){
        return Erro.newBuilder();
    }

    @ExceptionHandler(ZoneRulesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Erro handleZoneRulesException(ZoneRulesException e){
        return buildErro(new RuntimeException("Por favor, forneça um timezone válido"));
    }

    @ExceptionHandler(DateTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Erro handleZoneRulesException(DateTimeException e){
        return buildErro(new RuntimeException("timezone formato invalido"));
    }

}

class Erro {
    private LocalDateTime dataHoraErro;
    private String messages;

    public Erro(){}

    public static Erro newBuilder(){
        return new Erro();
    }

    public Erro withDateHoraErro(final LocalDateTime dataHoraErro){
        this.dataHoraErro = dataHoraErro;
        return this;
    }

    public Erro withMessages(final String messages){
        this.messages = messages;
        return this;
    }

    public LocalDateTime getDataHoraErro() {
        return dataHoraErro;
    }

    public void setDataHoraErro(LocalDateTime dataHoraErro) {
        this.dataHoraErro = dataHoraErro;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
