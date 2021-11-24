package utils;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailUtil {

    private static String username = ConfigUtil.getMailUsername();
    private static String password = ConfigUtil.getMailPassword();
    private static Store store;

    private static void init()  {

        Properties props = new Properties();
        props.put("mail.imap.host", ConfigUtil.getMailHost());
        props.put("mail.imap.auth", "true");
        props.put("mail.imap.port", ConfigUtil.getMailPort());
        props.put("mail.imap.socketFactory.port", ConfigUtil.getMailPort());
        props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props);

        try {
            Store store = session.getStore("imap");
            store.connect(username, password);
            MailUtil.store = store;

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static boolean checkMessage(String productName) {

        if(store == null) {
            init();
        }

        try {
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();

            Message message = messages[messages.length - 1];
            String messageSubject = message.getSubject();

            if (
                    (messageSubject == null) ||
                    !messageSubject.contains(productName) ||
                    !(message.getContent() instanceof MimeMultipart)
            ) {
                return  false;
            }

            MimeMultipart messageContent = (MimeMultipart) message.getContent();
            String messageText = messageContent.getBodyPart(0).getContent().toString();
            int beginIndex = messageText.indexOf("https://");
            int endIndex = messageText.indexOf(")", beginIndex);
            String linkFromMessage = messageText.substring(beginIndex, endIndex);

            return linkFromMessage.contains(ConfigUtil.getLinkTextToCheck());

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
