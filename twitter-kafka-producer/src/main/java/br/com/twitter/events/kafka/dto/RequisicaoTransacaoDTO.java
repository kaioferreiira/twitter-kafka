package br.com.twitter.events.kafka.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "RequisicaoTransacaoDTO", description = "Objeto de transporte para o envio de uma promessa de transação")
public class RequisicaoTransacaoDTO extends TransactionDTO {

    @JsonIgnore
    private SituacaoEnum situacao;
    @JsonIgnore
    private LocalDateTime data;

}
