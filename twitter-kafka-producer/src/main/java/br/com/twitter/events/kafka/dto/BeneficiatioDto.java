package br.com.twitter.events.kafka.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BeneficiatioDto implements Serializable {

    private static final long serialVersionUID = 2806421543985360625L;

    @ApiModelProperty(value = "CPF do Beneficiário")
    private Long CPF;
    @ApiModelProperty(value = "Código do banco destino")
    private Long codigoBanco;
    @ApiModelProperty(value = "Agência de destino")
    private String agencia;
    @ApiModelProperty(value = "Conta de destino")
    private String conta;
    @ApiModelProperty(value = "Nome do Favorecido")
    private String nomeFavorecido;


}
