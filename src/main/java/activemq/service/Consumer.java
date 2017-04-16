package activemq.service;

import main.email.credentials.MailSenderCredentials;
import main.email.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by sangeet on 4/13/2017.
 */

@Component() public class Consumer {
  @Autowired()
  private SendEmailService sendEmailService;

  @JmsListener(destination = "user-ms") public void receiveQueue(final String email) {
    MailSenderCredentials mailSenderCredentials = new MailSenderCredentials();
    mailSenderCredentials.setSubject("la herum na ta");
    mailSenderCredentials.setFrom("locationassister@gmail.com");
    mailSenderCredentials.setTo(email);
    mailSenderCredentials.setBody("your account has been set up. Enjoy");
    sendEmailService.sentEmail(mailSenderCredentials);
  }

}
