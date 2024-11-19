package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


@XmlRootElement(name="currency")

public class Amount {
	private double value;
	private final String currency = "€";
	
	public Amount(double value) {
		super();
		this.value = value;
	}
	public Amount() {

	}
	
	
	@XmlAttribute(name="currency")
	public String getCurrency() {
		return currency;
	}

	@XmlValue
	public double getValue() {
		return value;
	}
	
	public void setValue(double Value) {
		this.value = Value;
	}
	
	@Override
	public String toString(){
		return value + currency;
	}
}
