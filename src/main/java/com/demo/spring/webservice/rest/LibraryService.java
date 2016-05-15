package com.demo.spring.webservice.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.demo.spring.webservice.rest.domain.Book;

@Path("/library")
@Produces(MediaType.TEXT_XML)
@Service("libraryServiceBean")
public class LibraryService {
	private Map<String, Book> books = new HashMap<String, Book>();

    public LibraryService(){
        init();
    }
    
    /**
     * init books
     */
    private void init(){
        Book book = null;
        book = new Book();      
        book.setAuthor("CSDN");
        book.setId("blog");
        book.setName("book1");
        book.setPrice(3.0);     
        books.put(book.getId(), book);

        book = new Book();
        book.setAuthor("Me");
        book.setId("app");
        book.setName("book2");
        book.setPrice(30.0);
        books.put(book.getId(), book);

        book = new Book();
        book.setAuthor("CSDN");
        book.setId("resource");
        book.setName("book3");
        book.setPrice(5.0);
        books.put(book.getId(), book);

        book = new Book();
        book.setAuthor("ItEye");
        book.setId("rs");
        book.setName("book4");
        book.setPrice(15.0);
        books.put(book.getId(), book);
    }

    /**
     * get a book by id
     */
    @GET
    @Path("/books/{id}/")
    public Book getBook(@PathParam("id") String id){
        return books.get(id);
    }

    /**
     * update a book
     */
    @PUT
    @Path("/books/")
    public Response updateBook(Book book){
        Response r;
        if(book == null){
            r = Response.noContent().build();
            return r;
        }

        String id = book.getId();
        Book b = books.get(id);
        if(b != null){
            books.put(id, book);
            r = Response.ok(true, MediaType.TEXT_PLAIN).build();
        }else{
            r = Response.notModified().build();
        }

        return r;
    }

    /**
     * add a book
     */
    @POST
    @Path("/books/")
    public Response addBook(Book book){
        Response r;
        if(book == null){
            r = Response.notModified().build();
        }else{
            books.put(book.getId(), book);
            r = Response.ok(true, MediaType.TEXT_PLAIN).build();
        }

        return r;
    }

    /**
     * delete a book
     */
    @DELETE
    @Path("/books/{id}/")
    public Response deleteBook(@PathParam("id") String id){
        Response r;
        Book book = books.get(id);

        if(book == null){
            r = Response.notModified("book doesn't exist").build();
        }else{
            books.remove(id);
            r = Response.ok(book, MediaType.APPLICATION_XML).build();
        }

        return r;
    }
}
