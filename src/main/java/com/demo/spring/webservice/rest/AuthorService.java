package com.demo.spring.webservice.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
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

import com.demo.spring.webservice.rest.domain.Author;

@Path("/author")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Service("authorServiceBean")
public class AuthorService {
	private Map<String, Author> authors = new HashMap<String, Author>();

    public AuthorService(){
        init();
    }
    
    /**
     * init authors
     */
    private void init(){
    	Author author = null;
    	author = new Author();      
    	author.setId("1");
    	author.setName("Cheng");
    	author.setGender("male");
    	author.setAge(21);  
    	authors.put(author.getId(), author);

        author = new Author();      
        author.setId("2");
        author.setName("Wang");
        author.setGender("female");
        author.setAge(27);  
        authors.put(author.getId(), author);
        
        author = new Author();      
        author.setId("3");
        author.setName("Charlie");
        author.setGender("male");
        author.setAge(33);  
        authors.put(author.getId(), author);
    }

    /**
     * get an author by id
     */
    @GET
    @Path("/{id}/")
    public Author getAuthor(@PathParam("id") String id){
        return authors.get(id);
    }
    
    @GET
    @Path("/list")
    public List<Author> getAuthorList(){
    	List<Author> list = new ArrayList<Author>();
    	for(Author author: authors.values()){
    		list.add(author);
    	}
        return list;
    }

    /**
     * update an author
     */
    @PUT
    @Path("/")
    public Response updateAuthor(Author author){
        Response r;
        if(author == null){
            r = Response.noContent().build();
            return r;
        }

        String id = author.getId();
        Author a = authors.get(id);
        if(a != null){
        	authors.put(id, author);
            r = Response.ok("{\"success\":true}", MediaType.APPLICATION_JSON).build();
        }else{
            r = Response.notModified().build();
        }

        return r;
    }

    /**
     * add an author
     */
    @POST
    @Path("/")
    public Response addAuthor(Author author){
        Response r;
        if(author == null){
            r = Response.notModified().build();
        }else{
        	authors.put(author.getId(), author);
            r = Response.ok(true, MediaType.APPLICATION_JSON).build();
        }

        return r;
    }

    /**
     * delete an author
     */
    @DELETE
    @Path("/{id}/")
    public Response deleteAuthor(@PathParam("id") String id){
        Response r;
        Author author = authors.get(id);

        if(author == null){
            r = Response.notModified("author doesn't exist").build();
        }else{
        	authors.remove(id);
            r = Response.ok(author, MediaType.APPLICATION_JSON).build();
        }

        return r;
    }
}
