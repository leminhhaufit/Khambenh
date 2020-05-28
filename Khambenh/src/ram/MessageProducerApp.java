package ram;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import Sender.MessageSender;
import config.AppConfig;
import entity.Benhnhan;


public class MessageProducerApp
{

	public static void main(String[] args)
	{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		MessageSender messageSender = context.getBean(MessageSender.class);

		
		/*
		 * String hoten = txtHoten.getText(); String msbn = txtmsbn.getText(); String
		 * cmnd = txtcmnd.getText(); String dc = txtDiachi.getText();
		 */
		
		Benhnhan bn = new Benhnhan();
		//bn.setMsbn("23232");
		//bn.setCmnd("3232323");
		bn.setHoten("Hua");
		bn.setDiachi("dsadxzczc");
		messageSender.sendMessage(bn);
		System.out.println("Message has been sent successfully...");

		((AbstractApplicationContext) context).close();

		
	}

}
