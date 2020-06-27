package br.com.twitter.events.kafka.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Conta implements Serializable {

    private static final long serialVersionUID = 2806412403585360625L;

    @ApiModelProperty(value = "Código da Agência")
    private Long codigoAgencia;
    @ApiModelProperty(value = "Código da Conta")
    private Long codigoConta;
}
