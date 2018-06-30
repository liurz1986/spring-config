package com.liurz.quartzScheduler;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 该类用于定时任务调用的
 * @author Administrator
 *
 */
public class QuartzStack {
	 private static int counter = 0;  
	// 创建一个可重用固定线程数的线程池,创建10个线程
	 private ExecutorService pool = Executors.newFixedThreadPool(10);
	 protected void execute()  {  
	        long ms = System.currentTimeMillis();
	        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
	        MailService mailService=new MailService();
	        // 将线程放入池中进行执行
	        pool.execute(mailService);
	        System.out.println(new Date(ms));  
	      
	    }
	 
	
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		QuartzStack.counter = counter;
	}  
	 
	/**
	 * 测试窗口
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		 MailService mailService=new MailService();
	        // 将线程放入池中进行执行
	      pool.execute(mailService);
	}
}
