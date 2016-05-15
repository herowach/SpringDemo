package com.demo.spring.webservice.rest.domain;

import javax.xml.bind.annotation.XmlRootElement;

/*
<Book>
	<author>cheng wang</author>
	<id>10</id>
	<name>a book name</name>
	<price>3.0</price>
</Book>
*/
@XmlRootElement(name = "Book")
public class Book {
	private String id;
    private String name;
    private String author;
    private Double price;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
