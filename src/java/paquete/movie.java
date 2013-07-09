/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import javax.enterprise.inject.Model;
import javax.validation.constraints.Size;

/**
 *
 * @author yepesk8r
 */
@Model
public class movie {
    @Size(min=1, max=20)
    private String name;
    
    @Size(min=1, max=20)
    private String languaje;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguaje() {
        return languaje;
    }

    public void setLanguaje(String languaje) {
        this.languaje = languaje;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    private int year;
    
    public BasicDBObject toDBObject(){
        BasicDBObject doc = new BasicDBObject();
        
        doc.put("name", name);
        doc.put("year", year);
        doc.put("languaje", languaje);
        
        return doc;
    }
    
    public static movie fromDBObject(DBObject doc){
        movie m =new movie();
        
        m.name=(String) doc.get("name");
        m.year=(int) doc.get("year");
        m.languaje = (String) doc.get("languaje");
        
        return m;
    
    }
    @Override
    public String toString(){
    return name +","+year+","+languaje;
    }
    
}
