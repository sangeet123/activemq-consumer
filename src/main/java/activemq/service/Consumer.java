package activemq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.email.credentials.MailSenderCredentials;
import main.email.service.SendEmailService;
import model.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import template.email.message.AccountCreatedMessage;

/**
 * Created by sangeet on 4/13/2017.
 */

@Component() public class Consumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
  private static ObjectMapper objectMapper = new ObjectMapper();
  private static String SUBJECT = "User account created";
  private static String FROM = "locationassister@gmail.com";
  @Autowired() private SendEmailService sendEmailService;

  @JmsListener(destination = "user-ms") public void receiveQueue(final String createdUser) {
    try {
      final UserResponse userResponse = objectMapper.readValue(createdUser, UserResponse.class);
      MailSenderCredentials mailSenderCredentials = new MailSenderCredentials();
      mailSenderCredentials.setSubject(SUBJECT);
      mailSenderCredentials.setFrom(FROM);
      mailSenderCredentials.setTo(userResponse.getEmail());
      mailSenderCredentials.setBody(AccountCreatedMessage
          .getMessage(userResponse.getFirstName(), userResponse.getLastName()));
      sendEmailService.sentEmail(mailSenderCredentials);
    } catch (final Exception ex) {
      //This should never happen
      LOGGER.error("unable to deserilize user ex{}", ex);
    }
  }

}
