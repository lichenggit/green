package mail;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class MailSend {

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
    private static final String mailHost = "smtp.126.com";
    //发件人邮箱的账户和密码
    private static final String mailAccount = "licheng_work@126.com";
    private static final String mailPassword = "licheng00";

    private static Properties props = new Properties();

    static {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        props.setProperty("mail.transport.protocol", "smtp");// 使用的协议（JavaMail规范要求）
        props.setProperty("mail.host", mailHost);// 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");// 请求认证，参数名称与具体实现有关
    }

    private String mailReceiver;

    public MailSend(String mailReceiver) {
        this.mailReceiver = mailReceiver;
    }

    public static void main(String[] args) throws Exception {
        //收件人邮箱
        MailSend main = new MailSend("362627943@qq.com");
        main.sendMail("主题", "内容");
    }

    public void sendMail(String subject, String mailContent) throws Exception {
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);// 设置为debug模式, 可以查看详细的发送 log
        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, subject, mailContent);
        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
        // 这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
        transport.connect(mailAccount, mailPassword);
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        // 7. 关闭连接
        transport.close();
    }

    private MimeMessage createMimeMessage(Session session, String subject, String mailContent) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(mailAccount, "校园共享平台", "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailReceiver, "用户", "UTF-8"));
        // 4. Subject: 邮件主题
        message.setSubject(subject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        // 6. 创建文本“节点”
        MimeBodyPart text1 = formatText(mailContent);

        // 7. （文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text1);
        mm_text_image.setSubType("mixed");//关联关系

        message.setContent(mm_text_image);
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }

    /**
     * 封装文本节点
     *
     * @param text
     * @throws MessagingException
     */
    public MimeBodyPart formatText(String text) throws MessagingException {
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent(text, "text/html;charset=UTF-8");
        return textBodyPart;
    }

    /**
     * 封装图片节点
     *
     * @param imageName
     * @throws MessagingException
     */
    public MimeBodyPart formatImage(String imageName) throws MessagingException {
        MimeBodyPart imageBodyPart = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource(imageName)); // 读取本地文件
        imageBodyPart.setDataHandler(dh);// 将图片数据添加到“节点”
        imageBodyPart.setContentID("image_" + System.currentTimeMillis());// 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
        return imageBodyPart;
    }

    /**
     * 封装附件节点
     *
     * @param fileName
     * @throws MessagingException
     */
    public MimeBodyPart formatFile(String fileName) throws MessagingException, UnsupportedEncodingException {
        MimeBodyPart fileBodyPart = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource(fileName));// 读取本地文件
        fileBodyPart.setDataHandler(dh);// 将附件数据添加到“节点”
        fileBodyPart.setFileName(MimeUtility.encodeText(dh.getName()));// 设置附件的文件名（需要编码）
        return fileBodyPart;
    }
}
