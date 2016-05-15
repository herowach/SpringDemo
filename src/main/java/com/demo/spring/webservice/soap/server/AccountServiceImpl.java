package com.demo.spring.webservice.soap.server;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.spring.webservice.soap.server.domain.Account;
import com.demo.spring.webservice.soap.server.domain.CxfFileWrapper;

//endpointInterface is not required, we can publish a class as a webservice as well
@WebService(
    endpointInterface = "com.spring.test.ws.AccountService",
    portName = "AccountServicePort",
    serviceName = "AccountServiceImpl"
)
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Resource  
    private WebServiceContext context;  
	
	@Resource
    private AccountDAO accountDao;
	
	@Override
	public void insertAccount(Account account) {
		printCount();
		accountDao.addAccount(account);
	}

	@Override
	public Account getAccount(int id){
		printCount();
		return accountDao.getAccount(id);
	}
	
	@Override
	public List<Account> getAccounts(String name) {
		return accountDao.getAccounts();
	}

	@Override
	public boolean upload(CxfFileWrapper file) {
		boolean result = true;

        InputStream is = null;
        BufferedOutputStream bos = null;

        try {
            is = file.getFile().getInputStream();

            File dest = new File("C:\\my tools\\temp\\upload\\" + file.getFileName());

            bos = new BufferedOutputStream(new FileOutputStream(dest));

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }

            bos.flush();

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
        	closeResource(bos, is);
        }

        return result;
	}

	@Override
	public CxfFileWrapper download(String fileName) {
		String filePath = "C:\\my tools\\temp\\upload\\";

        CxfFileWrapper fileWrapper = new CxfFileWrapper();
        fileWrapper.setFileName(fileName);
        fileWrapper.setFileExtension("xlsx");

        DataSource source = new FileDataSource(new File(filePath));
        fileWrapper.setFile(new DataHandler(source));

        return fileWrapper;
	}
	
	private void printCount(){
		MessageContext ctx = context.getMessageContext();  
		HttpSession session =((HttpServletRequest)ctx.get(MessageContext.SERVLET_REQUEST)).getSession();
		Integer count = (Integer)session.getAttribute("count");
		if(count == null){  
			count = 1;
        }else{  
            count++;  
        }
		session.setAttribute("count", count);
		logger.info("have invoked " + count + " times");
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
	
	public static String getClassPath() {
		URL location = AccountServiceImpl.class.getProtectionDomain().getCodeSource().getLocation();
	    File file = new File(location.getFile());
	    return file.getParent();
	}

}
