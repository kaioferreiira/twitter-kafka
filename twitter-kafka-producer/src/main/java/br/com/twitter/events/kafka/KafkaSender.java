package br.com.twitter.events.kafka;

import br.com.twitter.events.kafka.dto.RequisicaoTransacaoDTO;
import br.com.twitter.events.kafka.dto.RequisicaoTransacaoDTO2;
import br.com.twitter.events.kafka.dto.TransactionDTO;
import br.com.twitter.events.kafka.dto.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class KafkaSender {

    public static final String HEADER_VALUE = "999";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic}")
    private String topic;

    @Autowired
    private ObjectMapper objectMapper;

    public RequisicaoTransacaoDTO2 send(RequisicaoTransacaoDTO2 transactionDTO) {

//        transactionDTO.setUui(getId());
        try {

            String payload = objectMapper.writeValueAsString(transactionDTO);
            Message<String> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic)
//                .setHeader(KafkaHeaders.MESSAGE_KEY, HEADER_VALUE)  nao precisa
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
//                .setHeader("X-CoffeeAndIT-Header", "transaction" + getId())  nao precisa
                .build();

            kafkaTemplate.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return transactionDTO;
    }

    public void sendAll(int qtd) {
         int i=0;
        try {
            do{

            RequisicaoTransacaoDTO2 requisicaoTransacaoDTO2 = new RequisicaoTransacaoDTO2();
            requisicaoTransacaoDTO2.setUui(getId());
            String payload = objectMapper.writeValueAsString(requisicaoTransacaoDTO2);
            Message<String> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
                .build();

            kafkaTemplate.send(message);
            i =i+1;
            }while(i <= qtd);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // void
    public void send(final TransactionDTO transactionDTO) {
        try {

            String payload = objectMapper.writeValueAsString(transactionDTO);
            Message<String> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
                .build();

            kafkaTemplate.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Tweet send(Tweet mensagem) {

        try {
            String payload = objectMapper.writeValueAsString(mensagem);
            Message<String> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
                .build();

            kafkaTemplate.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mensagem;
    }




        private UUID getId() {
        return UUID.randomUUID();
    }
}