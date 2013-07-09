/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;
import paquete.movie;

/**
 *
 * @author yepesk8r
 */
@Stateless
@Named
public class MovieSessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Inject movie movie;
    @Inject movie movie1;
    
   DBCollection movieColl;
   
   @PostConstruct
   private void initDB(){
   Mongo m;
            try{
            m = new Mongo();
            DB db =m.getDB("movieDB");
            movieColl = db.getCollection("movies");
            if(movieColl == null){
                movieColl = db.createCollection("movies", null);
            }
            }catch(UnknownHostException ex){
                //Logger.getLogger(MovieSessionBean.class).getName()).log(Level.SEVERE, null, ex);
            }
   }
   public void createMovie(){
       BasicDBObject doc = movie.toDBObject();
       movieColl.insert(doc);
   }
   public List<movie> getMovies(){
       List<movie> movies = new ArrayList();
       DBCursor cur = movieColl.find();
       for(DBObject dbo : cur.toArray()){
           movies.add(movie.fromDBObject(dbo));
       }
       return movies;
   }

      public void delete() {
       BasicDBObject o = movie.toDBObject();
       movieColl.remove(o);
   }
   public void updateMovie(String name,int year,String languaje) {
	
       BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name", name);
        newDocument.put("year", year);
        newDocument.put("languaje", languaje);
 
	//BasicDBObject searchQuery = new BasicDBObject().append("name", movie.getName());
        BasicDBObject searchQuery = movie.toDBObject();
	movieColl.update(searchQuery, newDocument);
   }
}
