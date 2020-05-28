package Helper;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLConvert<T> {
	private T type;

	public XMLConvert(T type) {
		this.type = type;
	}
	
	public XMLConvert() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public T xml2Object(String xml) throws Exception{
		JAXBContext ctx= JAXBContext.newInstance(type.getClass());
		Unmarshaller ms = ctx.createUnmarshaller();
		StringReader reader = new StringReader(xml);
		T sv = (T) ms.unmarshal(reader);
		return sv;
	} 
	public String object2XML(T obj) throws JAXBException
	{
		JAXBContext ctx = JAXBContext.newInstance(type.getClass());
		Marshaller ms = ctx.createMarshaller();
		StringWriter sw = new StringWriter();
		ms.marshal(obj, sw);
		return sw.toString();
	}
}
