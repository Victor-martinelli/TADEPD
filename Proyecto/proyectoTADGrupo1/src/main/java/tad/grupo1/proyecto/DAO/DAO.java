/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.grupo1.proyecto.DAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Filters.eq;
import com.vaadin.server.VaadinService;
import java.io.File;
import java.util.Iterator;


public class DAO {
    
    
    String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
    
    /*
    Metodo que crea una nueva conexión con la base de datos (hacia el exterior)
    */
    public DBCollection getDatabaseCollection()
    {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://mongoUser:mongoPassword@proyectotad2019-shard-00-00-dpclw.azure.mongodb.net:27017,proyectotad2019-shard-00-01-dpclw.azure.mongodb.net:27017,proyectotad2019-shard-00-02-dpclw.azure.mongodb.net:27017/test?ssl=true&replicaSet=proyectoTAD2019-shard-0&authSource=admin&retryWrites=true");
        
        
        return new MongoClient(uri).getDB("database").getCollection("proyectoTAD");
        
    }
    
    public String getVideoPath(String username,String title)
    {
        return basepath+File.separator+"users"+File.separator+username+File.separator+"videos"+File.separator+title+File.separator+title+".mp4";
    }
    
    public String getVideoThumbnailPath(String username,String title)
    {
        return basepath+File.separator+"users"+File.separator+username+File.separator+"videos"+File.separator+title+File.separator+"thumb.png";
    }
    
    
    public int getVideoViews(String title)
    {
        DBCollection collection = this.getDatabaseCollection();
        
        int result=1;
        
        BasicDBObject whereQuery = new BasicDBObject();
        //Donde el titulo sea el buscado
	whereQuery.put("videos.title",title);
        
        //Resultado de la buscada
	DBCursor cursor = collection.find(whereQuery);
        
        //Solamente queda coger el resultado
	while(cursor.hasNext()) {
	    DBObject current = cursor.next();
            
            //Cogemos todos sus videos
                BasicDBList currentVideos = (BasicDBList) current.get("videos");
                
                //Iteramos sobre ellos
                Iterator it = currentVideos.iterator();
                while(it.hasNext())
                {
                    DBObject currentVideo = (DBObject) it.next();
                    result=(int) currentVideo.get("views");
                }
                
            
	}
        
        cursor.close();
       
        return result;
    }
    
    
}