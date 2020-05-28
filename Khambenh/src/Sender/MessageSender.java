package Sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import entity.Benhnhan;
import model.Product;

@Component
public class MessageSender
{

	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMessage(final Benhnhan bn)
	{

		jmsTemplate.send(new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				ObjectMessage objectMessage = session.createObjectMessage(bn);
				return objectMessage;
			}
		});
	}
	public void sendMessage2(final Benhnhan bn)
	{

		jmsTemplate.send(new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				ObjectMessage objectMessage = session.createObjectMessage(bn);
				return objectMessage;
			}
		});
	}

}
