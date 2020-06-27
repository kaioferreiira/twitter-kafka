package br.com.twitter.api;

import br.com.twitter.events.kafka.KafkaSender;
import br.com.twitter.events.kafka.dto.RequisicaoTransacaoDTO;
import br.com.twitter.events.kafka.dto.RequisicaoTransacaoDTO2;
import br.com.twitter.events.kafka.dto.TransactionDTO;
import br.com.twitter.events.kafka.dto.Tweet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequestMapping("/v1")
public class TransactionTwitterController {

  @Value("${app.timeout}")
  private int timeout;

  @Value("${app.retries}")
  public int numberRetries;

  private KafkaSender kafkaSender;

  @Autowired
  public TransactionTwitterController(KafkaSender kafkaSender) {
    this.kafkaSender = kafkaSender;
  }

//  @ApiOperation(value = "API para criar uma transação", response = TransactionDTO.class, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping(path = "/twitt", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
//  @ApiResponses(value = {@ApiResponse(code = 201, message = "Retorno para a inclusão da mensagem no tópico de transações", response = TransactionDTO.class),
//      @ApiResponse(code = 401, message = "Erro de autenticação dessa API"),
//      @ApiResponse(code = 403, message = "Erro de autorização dessa API"),
//      @ApiResponse(code = 404, message = "Recurso não encontrado")}
//  )
  public Mono<Tweet> save(@RequestBody Tweet tweet) {

    return Mono.just(kafkaSender.send(tweet)).timeout(Duration.ofSeconds(timeout))
        .doOnSuccess(result -> {
          log.info(String.format("Property sended.: %s", result.toString()));
        })
        .doOnError(throwable -> log.error(throwable.getLocalizedMessage()))
//        .doFirst(() -> changeStatusUnanalyzed(transactionDTO))
        .doFirst(() -> System.out.println("Init post message twitter... "))
        .retry(numberRetries);
  }



//  @ApiOperation(value = "API para criar uma transação", response = TransactionDTO.class, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping(path = "/twitts")
//  @ResponseBody
//  @ApiResponses(value = {@ApiResponse(code = 201, message = "Retorno para a inclusão da mensagem no tópico de transações", response = TransactionDTO.class),
//      @ApiResponse(code = 401, message = "Erro de autenticação dessa API"),
//      @ApiResponse(code = 403, message = "Erro de autorização dessa API"),
//      @ApiResponse(code = 404, message = "Recurso não encontrado")}
//  )
  public ResponseEntity<Void>  saveAll(@RequestParam int qtd) {

    kafkaSender.sendAll(qtd);

    return ResponseEntity.ok().build();
  }

  private void changeStatusUnanalyzed(RequisicaoTransacaoDTO2 transactionDTO) {
//    transactionDTO.naoAnalisada();
  }

}
