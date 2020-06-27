package br.com.twitter.events.kafka.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@EqualsAndHashCode(of = "uui")
@ToString
@ApiModel(value = "TransactionDTO", description = "Objeto de transporte para o envio de uma promessa de transação")
public class TransactionDTO implements Serializable {

    private static final long serialVersionUID = 2806421523585360625L;

    @ApiModelProperty(value = "Valor da transação")
    private BigDecimal valor;
    @ApiModelProperty(value = "Data/hora/minuto e segundo da transação")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;
    @ApiModelProperty(value = "Conta de origem da transação")
    private Conta conta;
    @ApiModelProperty(value = "Beneficiário da transação")
    private BeneficiatioDto beneficiario;
    @ApiModelProperty(value = "Tipo de transação")
    private TipoTransacao tipoTransacao;
    @ApiModelProperty(value = "Código de identificação da transação")
    private UUID uui;
    @ApiModelProperty(value = "Situação da transação")
    private SituacaoEnum situacao;

    public void naoAnalisada() {
        setSituacao(SituacaoEnum.NAO_ANALISADA);
    }


}
