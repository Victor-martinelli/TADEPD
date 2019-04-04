/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.p1;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.text.SimpleDateFormat;

/**
 *
 * @author eps
 */
public class MongoDBJDBC {
    public static void main(String args[]) {
        DBCursor cursor = null;
        try {
            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            // Now connect to your databases
            DB db = mongoClient.getDB("P1");
            System.out.println("Connect to database successfully");
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            
            BasicDBObject betis1 = new BasicDBObject();
            betis1.append("nombre", "Joaquin");
            betis1.append("dorsal", "1");
            betis1.append("fecha nacimiento", formatter.parse("10-09-1980"));
            
            BasicDBObject betis2 = new BasicDBObject();
            betis2.append("nombre", "Enrique");
            betis2.append("dorsal", "10");
            betis2.append("fecha nacimiento", formatter.parse("10-09-1979"));
            
            BasicDBList jugadoresBetis = new BasicDBList();
            jugadoresBetis.add(betis1);
            jugadoresBetis.add(betis2);
            
            BasicDBObject galaxy1 = new BasicDBObject();
            galaxy1.append("nombre", "Bubble");
            galaxy1.append("dorsal", "10");
            galaxy1.append("fecha nacimiento", formatter.parse("11-09-1980"));
            
            BasicDBObject galaxy2 = new BasicDBObject();
            galaxy2.append("nombre", "Tank");
            galaxy2.append("dorsal", "20");
            galaxy2.append("fecha nacimiento", formatter.parse("15-09-1979"));
            
            BasicDBList jugadoresGalaxy = new BasicDBList();
            jugadoresGalaxy.add(galaxy1);
            jugadoresGalaxy.add(galaxy2);
            
            BasicDBObject equipo= new BasicDBObject();
            equipo.append("nombre", "Real Betis");
            equipo.append("nombre estadio", "Betis Estadio");
            equipo.append("jugadores", jugadoresBetis);
            
            BasicDBObject equipo2= new BasicDBObject();
            equipo2.append("nombre", "LA Galaxy");
            equipo2.append("nombre estadio", "Galaxy Estadio");
            equipo2.append("jugadores", jugadoresGalaxy);
            
            
            //Obtención colección "usuario"
            DBCollection collection = db.getCollection("P1");
            //En caso de no existir colección "usuario" la crea
            if (!db.collectionExists("Equipos")) {
                collection = db.createCollection("Equipos", equipo);
            }
            //Inserta documento
            collection.insert(equipo);
            collection.insert(equipo2);
            System.out.println("Documento insertado");
            //Recorrido de la colección
            System.out.println("Recorrido de la colección:");
            cursor = collection.find();
            int i = 1;
            while (cursor.hasNext()) {

                System.out.println("Documento leído: " + i);
                System.out.println(cursor.next());
                i++;
            }
           
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            cursor.close();
        }
    }
}
