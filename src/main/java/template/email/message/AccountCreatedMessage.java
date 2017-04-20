package template.email.message;

/**
 * Created by sangeet on 4/17/2017.
 */
public class AccountCreatedMessage {

  private final static String MESSAGE =
      "Hi <FIRST_NAME> <LAST_NAME>\n " + "Your account has been created successfully.\n "
          + "Cheers\n Account creation team.";

  public static String getMessage(final String firstName, final String lastName) {
    return MESSAGE.replace("<FIRST_NAME>", firstName).replace("<LAST_NAME>", lastName);
  }
}
