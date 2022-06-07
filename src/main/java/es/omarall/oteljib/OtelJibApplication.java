package es.omarall.oteljib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class OtelJibApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtelJibApplication.class, args);
    }
}

record LogEntry(Long id, Instant time) {
}

@RestController
class MyController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public LogEntry process() {
        return new LogEntry(counter.incrementAndGet(), Instant.now());
    }
}

