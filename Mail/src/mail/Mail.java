package mail;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mail {        
    public static void main(String[] args){
        
        
        try {
            
            
            Properties properties = new Properties();
            
            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.user", "luisarobles393@gmail.com");
            properties.setProperty("mail.smtp.clave", "jovenes2020");
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            Session session = Session.getDefaultInstance(properties);
            
            String correoRemitente = "luisarobles393@gmail.com"; 
            String PasswordRemitente = "jovenes2020";
            String correoReceptor = "armando.ro.chivas@gmail.com";
            String asunto = "mi segundo correo";
            String mensaje = "hola <br> juan </b> <h4>le informamos que su codigo es 223-h32 </h4>";
            
            BodyPart texto = new MimeBodyPart();
            //texto.setText("Texto del mensaje");
            texto.setContent(mensaje,"text/html");
            
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\Dell\\Pictures\\unnamed.jpg")));
            adjunto.setFileName("unnamed.jpg");
            
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            
            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(correoRemitente));
            } catch (MessagingException ex) {
                Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            } catch (MessagingException ex) {
                Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                message.setSubject(asunto);
            } catch (MessagingException ex) {
                Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                //message.setText(mensaje,"ISO-8859-1","HTML");
                message.setContent(multiParte);
            } catch (MessagingException ex) {
                Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Transport t;
            t = session.getTransport("smtp");
            t.connect(correoRemitente,PasswordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();
            
            System.out.println("correo enviado");
            
            
            
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
       
    }
}
