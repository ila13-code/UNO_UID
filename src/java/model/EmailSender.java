package model;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender implements Runnable //runnable perchè è essenziale inviare l'email usando un thread, altrimenti l0interfaccia utente rimarrà bloccata
    //finchè l'email non sarà stata inviata...
{
    private String to;

    public EmailSender(String to) {
        this.to = to;
    }

    @Override
    public void run() {
        sendEmail();
    }

    private void sendEmail() {
        String from = "unoUID@outlook.it";
        String username = "unoUID@outlook.it";
        String password = "Ila131202";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            //contenuto dell'email formattato grazie ai tag HTML...
            message.setContent("<html><h3 style=\"color:Red;\">Ciao " + Constants.USER + ", benvenuto nel gioco UID!</h3><br>Complimenti per esserti registrato :)<br>Ora puoi iniziare a giocare<br>" +
                    "Puoi usare questo indirizzo email (unoUID@outlook.it) per comunicare con noi, risponderemo appena possibile!<br>" +
                    "Buon divertimento!!!<br><br>" +
                    "<h5 style=\"color:White; background-color:Red; text-align:center\">Il team UNO-UID</h5><br>" +
                    "<footer><small>©2023 UNO-UID. Designed by Ilaria Frandina</small>\n" +
                    "</footer></html>", "text/html; charset=utf-8");
            //oggetto dell'email...
            message.setSubject("Benvenuto in unoUID");
            //invio della email...
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
