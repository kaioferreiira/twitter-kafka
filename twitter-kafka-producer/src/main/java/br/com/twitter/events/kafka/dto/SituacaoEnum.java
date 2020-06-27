package br.com.twitter.events.kafka.dto;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum SituacaoEnum {

    ANALISADA,
    NAO_ANALISADA,
    EM_ANALISE_HUMANA,
    EM_SUSPEITA_FRAUDE,
    RISCO_CONFIRMADO;

}
