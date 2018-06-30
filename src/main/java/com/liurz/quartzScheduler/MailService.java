package com.liurz.quartzScheduler;
import com.liurz.util.MailUtil;

public class MailService implements Runnable{
     
	public void run() {
		MailUtil m=new MailUtil();
    	try {
			m.sendMail_Spring();
			System.out.print(" ---email  send success:--");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("---email  send failure :--");
		} 
	}
} 