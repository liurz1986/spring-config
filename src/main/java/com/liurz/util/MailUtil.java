package com.liurz.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
/**
 * 发送接收邮箱
 * @author Administrator
 *QQ(mail.qq.com):POP3服务器（端口995）SMTP服务器（端口465或587）
 */
public class MailUtil {
    private String hostname="smtp.qq.com";//发邮件服务器目前为qq
    private String proptocol="pop3";//收邮件服务器pop3
    private String port="25";//邮件服务器端口号
    private boolean auth=true;//是否要求身份认证 
    private boolean enable=true;//是否使 STARTTLS安全连接 
    private String toEamil="451015469@qq.com";//发件人的邮箱
    private String username="451015469";//邮箱名
    private String password="vrqzhctflgtrbjff";//密码(授权码)
    private String[] revicename={"hgdxglrz1986@126.com"};//收件人邮箱
    private String Subject="Send Mail";//邮件标题
    private String text="Mail Content";//邮件内容，纯文本
    private String filepath="D:\\eclipse\\jobInfo\\build\\classes\\database.properties";//附件的路径
    private String titleF="定时邮件发定时邮件发送送附件";//附件标题
    public MailUtil(){
    	
    }
    public MailUtil(String title,String[] recivemail,String context,String tileF,String filepath){
    	this.Subject=title;
    	this.revicename=recivemail;
    	this.text=context;
    	this.titleF=tileF;
    	this.filepath=filepath;
    }
    /**
     * 在本地执行发送邮件。在spring中会报错
     * @throws AddressException
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public  void sendEmail() throws AddressException, MessagingException, UnsupportedEncodingException{
    	Properties pro=new Properties();
    	pro.put("mail.smtp.host",this.hostname);//126邮件服务器的名称
    	pro.put("mail.smtp.port", this.port); //SMTP邮件服务器默认端口
    	pro.put("mail.smtp.auth",this.auth);//是否要求身份认证 
    	pro.setProperty("mail.debug","true");//是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）  
    	pro.put("mail.smtp.starttls.enable",this.enable);//使用 STARTTLS安全连接
    	pro.put("mail.store.protocol",this.proptocol); // 收邮件协议 
        // 创建Session实例对象    
    	Session session=Session.getInstance(pro);
    	session.setDebug(true);//在后台打印发送邮件的实时信息
    	// 创建MimeMessage实例对象 
    	Message message=new MimeMessage(session);
    	//设置发件人的邮箱
    	message.setFrom(new InternetAddress(this.toEamil));
    	//标题
    	message.setSubject(this.Subject);
    	//发送 纯文本 
    	message.setText(this.text);
    	// 创建一个MIME子类型为"mixed"的MimeMultipart对象，表示这是一封混合组合类型的邮件 
        MimeMultipart mailContent = new MimeMultipart("mixed");  
        message.setContent(mailContent); 

    	 // 附件 
        MimeBodyPart attach2 = new MimeBodyPart(); 
        // 将附件和内容添加到邮件当中 
        mailContent.addBodyPart(attach2); 
        // 为附件增加内容
        if(null!=this.filepath){
        	 DataSource ds2 = new FileDataSource(this.filepath); //附件的路径
             DataHandler dh2 = new DataHandler(ds2); 
             attach2.setDataHandler(dh2); 
             attach2.setFileName(MimeUtility.encodeText(this.titleF)); //附件的名称
        }
       
    	//设置发信时间
    	message.setSentDate(new Date()); 
    	//设置收件人邮箱
    	message.setRecipients(RecipientType.TO,InternetAddress.parse(this.revicename[0]));
    	// 保存并生成最终的邮件内容 
    	message.saveChanges(); 
    	//smessage.setContent(mailContent, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富  
    	 
         
    	// 获得Transport实例对象 
        Transport transport=session.getTransport();  
    	// 打开连接 :邮箱名(用户名@前面)和密码(授权码)
    	transport.connect(this.username,this.password);  
    	// 将message对象传递给transport对象，将邮件发送出去;其中第二个参数是所有已设好的收件人地址  
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接 
        transport.close();
    }
   
	/**
	 * 在spring中发送邮件，在本地执行会报错
	 */
	public void sendMail_Spring(){
		try{
		 JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设定邮件服务器
		 senderImpl.setHost(this.hostname);
		// 建立邮件消息
		 MimeMessage mailMessage = senderImpl.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true,"utf-8");
		// 设置收件人,群发邮件
		 helper.setTo(this.revicename);
		 //发件人
		 helper.setFrom(this.toEamil);
		 //标题
		 helper.setSubject(this.Subject);
		 //邮件主体内容
		 helper.setText(this.text,true);
		//添加附件
		  ClassPathResource resource = new ClassPathResource(this.filepath);
		  helper.addAttachment("附件名称",resource);
		// 根据自己的情况,设置username
		  senderImpl.setUsername(this.username); 
		  senderImpl.setPassword(this.password); // 根据自己的情况, 设置password
		  Properties prop = new Properties();
		  prop.put(" mail.smtp.auth ", this.auth); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		  prop.put(" mail.smtp.timeout ", " 25000 ");
		  senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		  senderImpl.send(mailMessage);
		}catch(Exception e){
			e.getStackTrace();
		}
	}
    
	
	 /*
     * 测试入口
     */
    public static void main(String[] args) throws AddressException, MessagingException, UnsupportedEncodingException {
    	MailUtil m=new MailUtil();
    	m.sendEmail();
    	m.sendMail_Spring();
    }
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getProptocol() {
		return proptocol;
	}
	public void setProptocol(String proptocol) {
		this.proptocol = proptocol;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getToEamil() {
		return toEamil;
	}
	public void setToEamil(String toEamil) {
		this.toEamil = toEamil;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getRevicename() {
		return revicename;
	}
	public void setRevicename(String[] revicename) {
		this.revicename = revicename;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getTitleF() {
		return titleF;
	}
	public void setTitleF(String titleF) {
		this.titleF = titleF;
	}
	
}