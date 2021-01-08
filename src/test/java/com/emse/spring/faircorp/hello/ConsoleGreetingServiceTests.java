package com.emse.spring.faircorp.hello;

 import org.assertj.core.api.Assertions;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.springframework.boot.test.system.CapturedOutput;
 import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class) // (1)
class GreetingServiceTest {

    @Test
    public void testGreeting(CapturedOutput output) {
        GreetingService greetingService = new ConsoleGreetingService(); // (2)
        greetingService.greet("Spring");
        Assertions.assertThat(output.getAll()).contains("Hello, Spring!");
        greetingService.greetings("Spring","test");
        Assertions.assertThat(output.getAll()).contains("Hello, Spring test!");


    }
}
