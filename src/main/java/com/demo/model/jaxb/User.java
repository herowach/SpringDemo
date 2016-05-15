package com.demo.model.jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
-------------O/X mapping---------------
xsd:string			java.lang.String
xsd:integer			java.math.BigInteger
xsd:int				int
xsd.long			long
xsd:short			short
xsd:decimal			java.math.BigDecimal
xsd:float			float
xsd:double			double
xsd:boolean			boolean
xsd:byte			byte
xsd:QName			javax.xml.namespace.QName
xsd:dateTime		javax.xml.datatype.XMLGregorianCalendar
xsd:base64Binary	byte[]
xsd:hexBinary		byte[]
xsd:unsignedInt		long
xsd:unsignedShort	int
xsd:unsignedByte	short
xsd:time			javax.xml.datatype.XMLGregorianCalendar
xsd:date			javax.xml.datatype.XMLGregorianCalendar
xsd:g				javax.xml.datatype.XMLGregorianCalendar
xsd:anySimpleType	java.lang.Object
xsd:anySimpleType	java.lang.String
xsd:duration		javax.xml.datatype.Duration
xsd:NOTATION		javax.xml.namespace.QName
*/

@XmlAccessorType(XmlAccessType.FIELD)//means all non-static property will be added into xml
@XmlType(name = "", propOrder = {
	    "userId",
	    "userName",
	    "userType",
	    "log"
	})//means this class can be converted to xml
@XmlRootElement(name = "User")//means xml root is User
public class User {
	@XmlElement(required = true)
	private int userId;
	private String userName;
	@XmlElement(defaultValue="admin", required=true)
	private String userType;
	@XmlElementWrapper(name="logs")//add a warp element logs for log list
	private ArrayList<Log> log;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public ArrayList<Log> getLog() {
		return log;
	}
	public void setLogs(ArrayList<Log> logs) {
		this.log = logs;
	}
}