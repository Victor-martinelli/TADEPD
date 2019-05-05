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
import java.io.FileInputStream;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tad.grupo1.proyecto.objects.UserComment;
import tad.grupo1.proyecto.objects.UserVideo;

public class DAO {

    String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

    MongoClientURI uri = new MongoClientURI(
            "mongodb://mongoUser:mongoPassword@proyectotad2019-shard-00-00-dpclw.azure.mongodb.net:27017,proyectotad2019-shard-00-01-dpclw.azure.mongodb.net:27017,proyectotad2019-shard-00-02-dpclw.azure.mongodb.net:27017/test?ssl=true&replicaSet=proyectoTAD2019-shard-0&authSource=admin&retryWrites=true");

    /*
    Metodo que crea una nueva conexión con la base de datos (hacia el exterior)
     */
    public DBCollection getDatabaseCollection() {

        return new MongoClient(uri).getDB("database").getCollection("proyectoTAD");

    }

    public FileOutputStream uploadVideo(String username, String filename) {
        
        FileOutputStream fos = null;
 
        String videoTitle = filename.substring(0, filename.indexOf("."));
        
        try {
            String folderPath = basepath + File.separator + "users" + File.separator + username + File.separator + "videos" + File.separator + videoTitle;
            //Create video folder
            boolean success = (new File(folderPath)).mkdirs();
            if (success) {
                String videoPath = folderPath + File.separator + filename;
                // Open the file for writing.
                File file = new File(videoPath);
                fos = new FileOutputStream(file);
                //addVideo(videoTitle,username);
                
                //Add Default thumb
                copyFile(basepath+File.separator+"thumb.png",folderPath+File.separator+"thumb.png");
            } else {

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fos;
    }
    
    public FileOutputStream uploadThumbnail(String username, String title,String filename) {
        
        FileOutputStream fos = null;
 
        
        try {
            String folderPath = basepath + File.separator + "users" + File.separator + username + File.separator + "videos" + File.separator + title;
            //Create video folder
                String thumbPath = folderPath + File.separator + filename;
                // Open the file for writing.
                File file = new File(thumbPath);
                
                fos = new FileOutputStream(file);
                
               // file.delete();//Deletes original upload

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fos;
    }
    
    public void moveThumbnail(String username, String title,String filename)
    {
        //Primero borramos la miniatura por defecto
        new File(basepath + File.separator + "users" + File.separator + username + File.separator + "videos" + File.separator + title+File.separator+"thumb.png").delete();
        
        
        //Le cambiamos el nombre a la miniatura subida
        new File(basepath + File.separator + "users" + File.separator + username + File.separator + "videos" + File.separator + title+File.separator+filename).renameTo(new File(basepath + File.separator + "users" + File.separator + username + File.separator + "videos" + File.separator + title+File.separator+"thumb.png"));
    }
    

    public UserVideo getVideo(String username, String title) {

        return new UserVideo(title, (Date) getVideoInfo(title, "date"), getVideoPath(username, title), (int) getVideoInfo(title, "views"), (List) getVideoInfo(title, "likes"), (List) getVideoInfo(title, "dislikes"), getVideoComments(title));
    }

    public String getVideoPath(String username, String title) {
        return basepath + File.separator + "users" + File.separator + username + File.separator + "videos" + File.separator + title + File.separator + title + ".mp4";
    }

    public String getVideoThumbnailPath(String username, String title) {
        return basepath + File.separator + "users" + File.separator + username + File.separator + "videos" + File.separator + title + File.separator + "thumb.png";
    }

    public String getUserProfilePath(String username) {
        return basepath + File.separator + "users" + File.separator + username + File.separator + "profile.png";
    }

    public String getLogoPath() {
        return basepath + File.separator + "logo.png";
    }

    private List<UserComment> getVideoComments(String title) {

        List<UserComment> list = new ArrayList();
        //Cogemos a los comentarios
        BasicDBList comments = (BasicDBList) getVideoInfo(title, "comments");

        //Iteramos sobre ellos
        Iterator it = comments.iterator();
        while (it.hasNext()) {
            DBObject currentComentario = (DBObject) it.next();

            list.add(new UserComment((Date) currentComentario.get("date"), currentComentario.get("comment").toString(), currentComentario.get("username").toString()));
        }

        return list;
    }

    public int getUserSuscriptores(String username) {
        return (int) getUserInfo(username, "suscriptores");
    }

    public boolean isUserSuscribed(String user, String uploader) {
        List<String> suscripciones = (List<String>) getUserInfo(user, "suscripciones");

        return suscripciones.contains(uploader);
    }

    public void removeSuscripcion(String user, String uploader) {

        //Quitamos al usuario de su lista de suscripciones
        BasicDBObject newDocument
                = new BasicDBObject().append("$pull",
                        new BasicDBObject().append("suscripciones", uploader));

        getDatabaseCollection().update(new BasicDBObject().append("username", user), newDocument);

        closeConnection();
        //Decrementamos el número de suscriptores

        BasicDBObject newDocument2
                = new BasicDBObject().append("$inc",
                        new BasicDBObject().append("suscriptores", -1));

        getDatabaseCollection().update(new BasicDBObject().append("username", uploader), newDocument2);

        closeConnection();
    }

    public void addSuscripcion(String user, String uploader) {

        //Agregamos al usuario de su lista de suscripciones
        BasicDBObject newDocument
                = new BasicDBObject().append("$push",
                        new BasicDBObject().append("suscripciones", uploader));

        getDatabaseCollection().update(new BasicDBObject().append("username", user), newDocument);

        closeConnection();
        //Incrementamos el número de suscriptores

        BasicDBObject newDocument2
                = new BasicDBObject().append("$inc",
                        new BasicDBObject().append("suscriptores", 1));

        getDatabaseCollection().update(new BasicDBObject().append("username", uploader), newDocument2);

        closeConnection();
    }

    public Object getUserInfo(String username, String searchedParameter) {
        DBCollection collection = this.getDatabaseCollection();

        Object result = new Object();

        BasicDBObject whereQuery = new BasicDBObject();
        //Donde el titulo sea el buscado
        whereQuery.put("username", username);

        //Resultado de la buscada
        DBCursor cursor = collection.find(whereQuery);

        //Solamente queda coger el resultado
        while (cursor.hasNext()) {
            DBObject current = cursor.next();

            result = current.get(searchedParameter);

        }

        cursor.close();
        closeConnection();
        return result;
    }

    //Método que busca lo que le pidamos
    private Object getVideoInfo(String title, String searchedParameter) {
        DBCollection collection = this.getDatabaseCollection();

        Object result = new Object();

        BasicDBObject whereQuery = new BasicDBObject();
        //Donde el titulo sea el buscado
        whereQuery.put("videos.title", title);

        //Resultado de la buscada
        DBCursor cursor = collection.find(whereQuery);

        //Solamente queda coger el resultado
        while (cursor.hasNext()) {
            DBObject current = cursor.next();

            //Cogemos todos sus videos
            BasicDBList currentVideos = (BasicDBList) current.get("videos");

            //Iteramos sobre ellos
            Iterator it = currentVideos.iterator();
            while (it.hasNext()) {
                DBObject currentVideo = (DBObject) it.next();
                result = currentVideo.get(searchedParameter);
            }

        }

        cursor.close();
        closeConnection();

        return result;
    }

    public void incrementVideoViews(String title) {
        BasicDBObject newDocument
                = new BasicDBObject().append("$inc",
                        new BasicDBObject().append("videos.$.views", 1));

        getDatabaseCollection().update(new BasicDBObject().append("videos.title", title), newDocument);

        closeConnection();
    }

    public void unlikeVideo(String title, String username) {

        BasicDBObject newDocument
                = new BasicDBObject().append("$pull",
                        new BasicDBObject().append("videos.$.likes", username));

        getDatabaseCollection().update(new BasicDBObject().append("videos.title", title), newDocument);

        closeConnection();
    }

    public void likeVideo(String title, String username) {

        BasicDBObject newDocument
                = new BasicDBObject().append("$push",
                        new BasicDBObject().append("videos.$.likes", username));

        getDatabaseCollection().update(new BasicDBObject().append("videos.title", title), newDocument);

        closeConnection();
    }

    public void undislikeVideo(String title, String username) {

        BasicDBObject newDocument
                = new BasicDBObject().append("$pull",
                        new BasicDBObject().append("videos.$.dislikes", username));

        getDatabaseCollection().update(new BasicDBObject().append("videos.title", title), newDocument);

        closeConnection();
    }

    public void dislikeVideo(String title, String username) {

        BasicDBObject newDocument
                = new BasicDBObject().append("$push",
                        new BasicDBObject().append("videos.$.dislikes", username));

        getDatabaseCollection().update(new BasicDBObject().append("videos.title", title), newDocument);

        closeConnection();
    }

    public void publishComment(String title, String username, String comment) {

        // Crear documento usurio
        BasicDBObject usuario = new BasicDBObject();

        usuario.append("date", new Date());
        usuario.append("username", username);
        usuario.append("comment", comment);

        BasicDBObject newDocument
                = new BasicDBObject().append("$push",
                        new BasicDBObject().append("videos.$.comments", usuario));

        getDatabaseCollection().update(new BasicDBObject().append("videos.title", title), newDocument);

        closeConnection();
    }
    
    private void addVideo(String title,String username)
    {
        // Crear documento video
        BasicDBObject video = new BasicDBObject();

        video.append("title", title);
        video.append("date", new Date());
        video.append("likes", new ArrayList());
        video.append("dislikes", new ArrayList());
        video.append("views", 0);
        video.append("comments", new ArrayList());

        BasicDBObject newDocument
                = new BasicDBObject().append("$push",
                        new BasicDBObject().append("videos", video));

        getDatabaseCollection().update(new BasicDBObject().append("username", username), newDocument);

        closeConnection();
    }

    /**
     * Método para insertar un nuevo usuario
     *
     * @param username
     * @param email
     * @param password
     */
    public void insertUsuario(String username, String email, String password) {
        DBCollection collection = this.getDatabaseCollection();

        // Crear documento usurio
        BasicDBObject usuario = new BasicDBObject();

        usuario.append("username", username);
        usuario.append("password", password);
        usuario.append("email", email);
        usuario.append("suscriptores", 0);

        BasicDBList listSuscripciones = new BasicDBList();
        usuario.append("suscripciones", listSuscripciones);

        BasicDBList listVideos = new BasicDBList();
        usuario.append("videos", listVideos);

        collection.insert(usuario);

        closeConnection();
    }

    /**
     * Método para logarse
     *
     * @param username
     * @param password
     * @return
     */
    public Boolean login(String username, String password) {
        DBCollection collection = this.getDatabaseCollection();

        BasicDBObject query = new BasicDBObject("username", username).append("password", password);
        DBObject user = collection.findOne(query);
        closeConnection();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que comprueba si el nombre de usuario ya existe
     *
     * @param username
     * @return
     */
    public Boolean getUsername(String username) {
        DBCollection collection = this.getDatabaseCollection();
        BasicDBObject query = new BasicDBObject("username", username);
        DBObject user = collection.findOne(query);

        closeConnection();

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    //Cierra la conexión con Mongo
    public void closeConnection() {
        new MongoClient(uri).close();
    }

    /**
     * Método para obtener las suscripciones de un usuario
     *
     * @param username
     * @return
     */
    public Object getSuscripciones(String username) {
        DBCollection collection = this.getDatabaseCollection();
        BasicDBObject query = new BasicDBObject("username", username);
        DBObject user = collection.findOne(query);

        BasicDBList listSuscripciones = (BasicDBList) user.get("suscripciones");

        return listSuscripciones;
    }

    /**
     * Método para obtener los videos de los canales a los que un usuario esta
     * suscrito
     *
     * @param username
     * @return
     */
    public List getSuscritoVideo(String username) {
        DBCollection collection = this.getDatabaseCollection();
        BasicDBObject query = new BasicDBObject("username", username);
        DBObject user = collection.findOne(query);

        BasicDBList listVideos = (BasicDBList) user.get("videos");
        return listVideos;
    }
    
    public void copyFile(String original, String destination)
    {
        
        InputStream inStream = null;
	OutputStream outStream = null;
		
    	try{
    		
    	    File afile =new File(original);
    	    File bfile =new File(destination);
    		
    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);
        	
    	    byte[] buffer = new byte[1024];
    		
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){
    	  
    	    	outStream.write(buffer, 0, length);
    	 
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    
    	    
    	}catch(IOException e){
    	    e.printStackTrace();
    	}
        
    }

}
