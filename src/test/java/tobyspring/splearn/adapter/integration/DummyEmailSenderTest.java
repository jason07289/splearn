package tobyspring.splearn.adapter.integration;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;
import tobyspring.splearn.domain.shared.Email;

import static org.assertj.core.api.Assertions.assertThat;

class DummyEmailSenderTest {
    @Test
    @StdIo//Standard Input/Output를 캡처해줌
    void dummyEmailSender(StdOut out) {
        DummyEmailSender dummyEmailSender = new DummyEmailSender();

        dummyEmailSender.send(new Email("toby@splearn.app"), "subject", "body");

        assertThat(out.capturedLines()[0])//캡처했던 I/O를 테스트 할 수 있음.
                .isEqualTo("DummyEmailSender send email: Email[address=toby@splearn.app]");
    }

}