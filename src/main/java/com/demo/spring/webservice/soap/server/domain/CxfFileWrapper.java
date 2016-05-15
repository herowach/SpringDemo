package com.demo.spring.webservice.soap.server.domain;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;

/**
 * DataHandler can not get the file name and type, so need a wrapped class
 */
@XmlType(name = "CxfFileWrapper")
public class CxfFileWrapper {
	private String fileName;
    
    private String fileExtension;
    //binary data
    private DataHandler file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @XmlMimeType("application/octet-stream")
    public DataHandler getFile() {
        return file;
    }

    public void setFile(DataHandler file) {
        this.file = file;
    }
}
