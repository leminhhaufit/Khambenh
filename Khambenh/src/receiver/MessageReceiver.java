package receiver;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import entity.Benhnhan;
import model.Product;

@Component
public class MessageReceiver
{
	@Autowired
	JmsTemplate jmsTemplate;
	@Autowired
	MessageConverter messageConverter;

	public Benhnhan receiveMessage()
	{
		try
		{
			/*
			 * Here we receive the message.
			 */
			Message message = jmsTemplate.receive();
			Benhnhan Benhnhan = (Benhnhan) messageConverter.fromMessage(message);
			
			return Benhnhan;

		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}

		return null;
	}
}
