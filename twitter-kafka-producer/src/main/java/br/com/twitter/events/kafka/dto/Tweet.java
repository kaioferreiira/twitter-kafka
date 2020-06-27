package br.com.twitter.events.kafka.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tweet {

  private static final long serialVersionUID = -2373666249858166489L;

  @ApiModelProperty(value = "message tweet")
  private String message;

}
