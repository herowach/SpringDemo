package com.demo.spring.webservice.soap.client;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.ws.BindingProvider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.spring.webservice.soap.client.api.Account;
import com.demo.spring.webservice.soap.client.api.AccountWS;
import com.demo.spring.webservice.soap.client.api.CxfFileWrapper;

/*
	wsimport -keep -d "C:\my tools\temp" -p com.spring.test.ws.client.api http://localhost:8080/Spring_Test/services/AccountService?wsdl
	
	-keep generate the source file
	-p the package name of the client fiels
	-d the output path
*/
public class AccountClientWithSpring {

	public static void main(String[] args){
		ClassPathXmlApplicationContext  ctx = new ClassPathXmlApplicationContext("classpath*:spring/ws/spring-ws-client.xml");  
        AccountWS ws = ctx.getBean("accountWS", AccountWS.class);  
        
        //the server side will create a new session for each method invocation by default, add the below line will reuse the session
        ((BindingProvider)ws).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        
        //insert
        Account acc = new Account();
		acc.setId(10);
		acc.setName("second test");
		ws.insertAccount(acc);
		System.out.println("------------------>insert account success"); 
		//get list
        for(Account u : ws.getAccounts("test")){  
	        System.out.println("List Account [id:"+u.getId()+"][name:"+u.getName()+"]");  
	    }
        System.out.println("------------------>get account list success"); 
        
        //upload: can not pass a big file, otherwise OutOfMemoryError will be thrown
        CxfFileWrapper fileWrapper = new CxfFileWrapper();
        fileWrapper.setFileName("GFTS_BIGDATA_CLUSTER_INFOR.xlsx");
        fileWrapper.setFileExtension("xlsx");
        
        DataSource source = new FileDataSource(new File("C:\\Users\\cw67094\\Desktop\\GFTS_BIGDATA_CLUSTER_INFOR.xlsx"));
        fileWrapper.setFile(new DataHandler(source));
        boolean success = ws.upload(fileWrapper);
        System.out.println("------------------>upload " + (success ? "success" : "failed"));  
        //download
        if(success) {
        	CxfFileWrapper downloadedFile = ws.download("GFTS_BIGDATA_CLUSTER_INFOR.xlsx");
        	
        	InputStream is = null;
        	BufferedOutputStream bos = null;
        	
        	try {
				File dest = new File("C:\\my tools\\temp\\download\\" + fileWrapper.getFileName());
				is = downloadedFile.getFile().getInputStream();
			    bos = new BufferedOutputStream(new FileOutputStream(dest));

			    byte[] buffer = new byte[1024];
			    int len = 0;
			    while ((len = is.read(buffer)) != -1) {
			        bos.write(buffer, 0, len);
			    }

			    bos.flush();
			    System.out.println("------------------>download success");  
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				closeResource(bos, is);
			}
        }
        
        ctx.close();
	}
	
	public static void closeResource(Closeable ...resources) {
		for(Closeable resource: resources) {
			try {
				resource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
