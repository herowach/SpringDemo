
package com.demo.spring.webservice.soap.client.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.spring.test.ws.client.api package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Upload_QNAME = new QName("http://ws.test.spring.com/", "upload");
    private final static QName _GetAccountResponse_QNAME = new QName("http://ws.test.spring.com/", "getAccountResponse");
    private final static QName _GetAccountsResponse_QNAME = new QName("http://ws.test.spring.com/", "getAccountsResponse");
    private final static QName _DownloadResponse_QNAME = new QName("http://ws.test.spring.com/", "downloadResponse");
    private final static QName _UploadResponse_QNAME = new QName("http://ws.test.spring.com/", "uploadResponse");
    private final static QName _Download_QNAME = new QName("http://ws.test.spring.com/", "download");
    private final static QName _GetAccounts_QNAME = new QName("http://ws.test.spring.com/", "getAccounts");
    private final static QName _GetAccount_QNAME = new QName("http://ws.test.spring.com/", "getAccount");
    private final static QName _InsertAccount_QNAME = new QName("http://ws.test.spring.com/", "insertAccount");
    private final static QName _InsertAccountResponse_QNAME = new QName("http://ws.test.spring.com/", "insertAccountResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.spring.test.ws.client.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertAccountResponse }
     * 
     */
    public InsertAccountResponse createInsertAccountResponse() {
        return new InsertAccountResponse();
    }

    /**
     * Create an instance of {@link InsertAccount }
     * 
     */
    public InsertAccount createInsertAccount() {
        return new InsertAccount();
    }

    /**
     * Create an instance of {@link GetAccount }
     * 
     */
    public GetAccount createGetAccount() {
        return new GetAccount();
    }

    /**
     * Create an instance of {@link GetAccounts }
     * 
     */
    public GetAccounts createGetAccounts() {
        return new GetAccounts();
    }

    /**
     * Create an instance of {@link Download }
     * 
     */
    public Download createDownload() {
        return new Download();
    }

    /**
     * Create an instance of {@link UploadResponse }
     * 
     */
    public UploadResponse createUploadResponse() {
        return new UploadResponse();
    }

    /**
     * Create an instance of {@link DownloadResponse }
     * 
     */
    public DownloadResponse createDownloadResponse() {
        return new DownloadResponse();
    }

    /**
     * Create an instance of {@link GetAccountsResponse }
     * 
     */
    public GetAccountsResponse createGetAccountsResponse() {
        return new GetAccountsResponse();
    }

    /**
     * Create an instance of {@link GetAccountResponse }
     * 
     */
    public GetAccountResponse createGetAccountResponse() {
        return new GetAccountResponse();
    }

    /**
     * Create an instance of {@link Upload }
     * 
     */
    public Upload createUpload() {
        return new Upload();
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link CxfFileWrapper }
     * 
     */
    public CxfFileWrapper createCxfFileWrapper() {
        return new CxfFileWrapper();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Upload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "upload")
    public JAXBElement<Upload> createUpload(Upload value) {
        return new JAXBElement<Upload>(_Upload_QNAME, Upload.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "getAccountResponse")
    public JAXBElement<GetAccountResponse> createGetAccountResponse(GetAccountResponse value) {
        return new JAXBElement<GetAccountResponse>(_GetAccountResponse_QNAME, GetAccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccountsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "getAccountsResponse")
    public JAXBElement<GetAccountsResponse> createGetAccountsResponse(GetAccountsResponse value) {
        return new JAXBElement<GetAccountsResponse>(_GetAccountsResponse_QNAME, GetAccountsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "downloadResponse")
    public JAXBElement<DownloadResponse> createDownloadResponse(DownloadResponse value) {
        return new JAXBElement<DownloadResponse>(_DownloadResponse_QNAME, DownloadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "uploadResponse")
    public JAXBElement<UploadResponse> createUploadResponse(UploadResponse value) {
        return new JAXBElement<UploadResponse>(_UploadResponse_QNAME, UploadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Download }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "download")
    public JAXBElement<Download> createDownload(Download value) {
        return new JAXBElement<Download>(_Download_QNAME, Download.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccounts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "getAccounts")
    public JAXBElement<GetAccounts> createGetAccounts(GetAccounts value) {
        return new JAXBElement<GetAccounts>(_GetAccounts_QNAME, GetAccounts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "getAccount")
    public JAXBElement<GetAccount> createGetAccount(GetAccount value) {
        return new JAXBElement<GetAccount>(_GetAccount_QNAME, GetAccount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAccount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "insertAccount")
    public JAXBElement<InsertAccount> createInsertAccount(InsertAccount value) {
        return new JAXBElement<InsertAccount>(_InsertAccount_QNAME, InsertAccount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertAccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.spring.com/", name = "insertAccountResponse")
    public JAXBElement<InsertAccountResponse> createInsertAccountResponse(InsertAccountResponse value) {
        return new JAXBElement<InsertAccountResponse>(_InsertAccountResponse_QNAME, InsertAccountResponse.class, null, value);
    }

}
