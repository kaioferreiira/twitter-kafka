package br.com.twitter.business;

import br.com.twitter.business.dto.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class TweetConsumer {

    private ObjectMapper objectMapper;

    public TweetConsumer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${app.topic}")
    public void onConsume(final String message) {

        CompletableFuture.supplyAsync(() -> {
            try {
                var transaction = getTransaction(message);
                System.out.println("lendo transaction: " + transaction);
                return transaction;
            } catch (IOException exception) {
                log.error(exception.getMessage(), exception);
                throw new RuntimeException(exception);
            }
        }).whenCompleteAsync((item, throwable) -> {
            if (Objects.nonNull(throwable)) {
                log.error(throwable.getMessage(), throwable);
            }
        });

    }

    private Tweet getTransaction(String message) throws IOException {
        Tweet transactionDTO = objectMapper.readValue(message, Tweet.class);
        return transactionDTO;
    }
}
