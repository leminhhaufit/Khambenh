package ram;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppConfig;
import entity.Benhnhan;
import model.Product;
import receiver.MessageReceiver;

public class MessageConsumerApp
{

	public static void main(String[] args)
	{
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		MessageReceiver messageReceiver = (MessageReceiver) context.getBean("messageReceiver");
		Benhnhan bn = messageReceiver.receiveMessage();
		System.out.println("Message Received = " + bn);
		System.out.println(bn.getHoten());
		((AbstractApplicationContext) context).close();
	}

}
