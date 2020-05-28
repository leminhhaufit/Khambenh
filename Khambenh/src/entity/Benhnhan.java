package entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BenhNhan")
public class Benhnhan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maso;
	private String socm;
	private String hoten;
	private String diachi;
	public Benhnhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@XmlElement
	public String getMaso() {
		return maso;
	}
	public void setMaso(String maso) {
		this.maso = maso;
	}
	@XmlElement
	public String getSocm() {
		return socm;
	}
	public void setSocm(String socm) {
		this.socm = socm;
	}
	@XmlElement
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	@XmlElement
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public Benhnhan(String maso, String socm, String hoten, String diachi) {
		super();
		this.maso = maso;
		this.socm = socm;
		this.hoten = hoten;
		this.diachi = diachi;
	}
	@Override
	public String toString() {
		return "Benhnhan [maso=" + maso + ", socm=" + socm + ", hoten=" + hoten + ", diachi=" + diachi + "]";
	}
	
	
	
	
}
