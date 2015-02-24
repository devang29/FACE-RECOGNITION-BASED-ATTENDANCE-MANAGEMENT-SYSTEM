/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pre_FRAMS;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Kaushik
 */
public class SendEmailOperation {
    
    public boolean sendEmail(String emailId, String emailSubject, String emailBody) {
        final String username = "frams.project@gmail.com";
        final String password = "100050131026";
        System.out.println("uname"+username+"pass"+password+"email"+emailId+"subject"+emailSubject+"body"+emailBody);
        Properties props = new Properties();
        System.out.println("111");
        props.put("mail.smtp.auth", "true");
        System.out.println("112");
        props.put("mail.smtp.starttls.enable", "true");
        System.out.println("113");
        props.put("mail.smtp.host", "smtp.gmail.com");
        System.out.println("114");
        props.put("mail.smtp.port", "587");
        System.out.println("115");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        System.out.println("116");
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("frams.project@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailId));
            message.setSubject(emailSubject);
            //message.setText("USERNAME:"+username);
            message.setText(emailBody);

            Transport.send(message);

        } catch (MessagingException e) {
            return false;
        }
        return true;
    }
}

