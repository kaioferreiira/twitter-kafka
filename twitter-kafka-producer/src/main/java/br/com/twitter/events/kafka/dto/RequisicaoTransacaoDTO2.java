package br.com.twitter.events.kafka.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "RequisicaoTransacaoDTO2", description = "Objeto de transporte para o envio de uma promessa de transação")
public class RequisicaoTransacaoDTO2  {

    @ApiModelProperty(value = "Código de identificação da transação")
    private UUID uui;
    @ApiModelProperty(value = "Situação da transação")
    private SituacaoEnum situacao;

}
