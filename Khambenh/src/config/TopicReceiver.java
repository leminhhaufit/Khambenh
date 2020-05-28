package config;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.BasicConfigurator;

public class TopicReceiver {
	private Connection con;
	private InitialContext ctx;
	private static TopicReceiver instance=null;
	
	private TopicReceiver() throws Exception {
		BasicConfigurator.configure();
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		ctx = new InitialContext(settings);
		//lookup JMS Connection Factory
		ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
		//tạo connection
		con = factory.createConnection("admin", "admin");
	}
	public static TopicReceiver getInstance() throws Exception
	{
		if (instance==null)
			instance = new TopicReceiver();
		return instance;
	}
	public void Connect() throws JMSException
	{
		con.start();
	}
	public MessageConsumer getMessageConsumer() throws Exception
	{
		Session session = con.createSession(false, 	Session.CLIENT_ACKNOWLEDGE);
		//tạo consumer
		Destination destination = (Destination) ctx.lookup("dynamicQueues/minhhau");
		return session.createConsumer(destination);
	}
	public void receiveMessage(String xml) throws Exception
	{
		
		
		
		
		/*
		 * MessageProducer producer = session.createProducer(destination); TextMessage
		 * msg = session.createTextMessage(xml); producer.send(msg);
		 */
	}
	
	
}
