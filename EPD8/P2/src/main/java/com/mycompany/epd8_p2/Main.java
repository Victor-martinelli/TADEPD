/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.epd8_p2;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lydia
 */
public class Main {

    public static void main(String[] args) {

        try {
            // Conectar con mongodb server 
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            // Conectar con la base de datos 
            DB db = mongoClient.getDB("gestionVuelos");
            System.out.println("Connect to database successfully");

            // Crear documento pasajeros
            BasicDBObject pasajero1 = new BasicDBObject();
            BasicDBObject pasajero2 = new BasicDBObject();
            BasicDBObject pasajero3 = new BasicDBObject();

            //Documento psajero 1
            pasajero1.append("nombre", "Pepe");
            pasajero1.append("nacionalidad", "España");
            pasajero1.append("pasaporte", "123456789");

            //Documento psajero 2
            pasajero2.append("nombre", "Juan");
            pasajero2.append("nacionalidad", "España");
            pasajero2.append("pasaporte", "987654321");

            //Documento psajero 3
            pasajero3.append("nombre", "Ana");
            pasajero3.append("nacionalidad", "España");
            pasajero3.append("pasaporte", "456789123");

            db.getCollection("pasajeros").drop();
            DBCollection collectionPasajeros = db.getCollection("pasajeros");
            collectionPasajeros.insert(pasajero1);
            collectionPasajeros.insert(pasajero2);
            collectionPasajeros.insert(pasajero3);

            //Crear documento revisiones
            BasicDBObject revi1 = new BasicDBObject();
            revi1.append("fecha", "01/03/2019");
            revi1.append("resultado", "favorable");

            BasicDBObject revi2 = new BasicDBObject();
            revi2.append("fecha", "04/02/2019");
            revi2.append("resultado", "favorable");

            BasicDBObject revi3 = new BasicDBObject();
            revi3.append("fecha", "01/03/2018");
            revi3.append("resultado", "favorable");

            BasicDBObject revi4 = new BasicDBObject();
            revi4.append("fecha", "04/02/2018");
            revi4.append("resultado", "favorable");

            BasicDBList revisiones1 = new BasicDBList();
            revisiones1.add(revi1);
            revisiones1.add(revi2);

            BasicDBList revisiones2 = new BasicDBList();
            revisiones2.add(revi1);
            revisiones2.add(revi2);
          
            db.getCollection("revisiones").drop();
            DBCollection collectionRevisiones = db.getCollection("revisiones");
            collectionRevisiones.insert(revi1);
            collectionRevisiones.insert(revi2);
            collectionRevisiones.insert(revi3);
            collectionRevisiones.insert(revi4);

            // Crear documento aviones
            BasicDBObject avion1 = new BasicDBObject();
            BasicDBObject avion2 = new BasicDBObject();

            //Avion 1
            avion1.append("modelo", "Airbus 1");
            avion1.append("anio", "2019");
            avion1.append("revisiones", revisiones1);

            //Avion 2
            avion2.append("modelo", "Airbus 2");
            avion2.append("anio", "2018");
            avion2.append("revisiones", revisiones2);

            db.getCollection("aviones").drop();
            DBCollection collectionAviones = db.getCollection("aviones");
            collectionAviones.insert(avion1);
            collectionAviones.insert(avion2);

            // Crear documento vuelos
            BasicDBObject vuelo1 = new BasicDBObject();
            BasicDBObject vuelo2 = new BasicDBObject();

            // Documento vuelo 1
            vuelo1.append("avion", avion1);
            vuelo1.append("origen", "Sevilla");
            vuelo1.append("destino", "Punta Cana");

            BasicDBList listPasajeros1 = new BasicDBList();
            listPasajeros1.add(pasajero1);
            listPasajeros1.add(pasajero3);
            vuelo1.append("pasajeros", listPasajeros1);

            // Documento vuelo 2
            vuelo2.append("avion", avion2);
            vuelo2.append("origen", "Madrid");
            vuelo2.append("destino", "Aleania");
            
            BasicDBList listPasajeros2 = new BasicDBList();
            listPasajeros2.add(pasajero3);
            listPasajeros2.add(pasajero2);
            vuelo2.append("pasajeros", listPasajeros2);

            db.getCollection("vuelos").drop();
            DBCollection collectionVuelos = db.getCollection("vuelos");
            collectionVuelos.insert(vuelo1);
            collectionVuelos.insert(vuelo2);

            menu(collectionPasajeros, collectionAviones, collectionVuelos);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void menu(DBCollection collectionPasajeros, DBCollection collectionAviones, DBCollection collectionVuelos) {
        String opc = "-1";
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Seleccione una opcion: \n"
                    + "\t1. Buscar pasajeros\n"
                    + "\t2. Buscar aviones\n"
                    + "\t3. Buscar vuelos\n"
                    + "\t0. Exit\n");
            opc = sc.next();

            switch (opc) {
                case "1":
                    System.out.println(buscarPasageros(collectionPasajeros));
                    break;
                case "2":
                    System.out.println(buscarAviones(collectionAviones));
                    break;
                case "3":
                    System.out.println(buscarVuelos(collectionVuelos));
                default:
                    break;
            }
        } while (!"0".equals(opc));
    }

    private static String buscarPasageros(DBCollection collectionPasajeros) {
        String resultado = "";
        DBCursor cursor = collectionPasajeros.find();

        int i = 1;
        while (cursor.hasNext()) {
            BasicDBObject aux = (BasicDBObject) cursor.next();
            resultado += "Pasajero " + i + ": "
                    + "\n\t - Nombre: " + aux.get("nombre")
                    + "\n\t - Nacionalidad: " + aux.get("nacionalidad")
                    + "\n\t - Pasaporte: " + aux.get("pasaporte")
                    + "\n";
            
            i++;
        }

        return resultado;
    }

    private static String buscarAviones(DBCollection collectionAviones) {
        String resultado = "";
        DBCursor cursor = collectionAviones.find();

        int i = 1;
        while (cursor.hasNext()) {
            BasicDBObject aux = (BasicDBObject) cursor.next();
            
            BasicDBList revisiones = (BasicDBList) aux.get("revisiones");
            String resultRevisiones = "";
            
            for(Object r : revisiones){
                BasicDBObject auxR = (BasicDBObject) r;
                resultRevisiones = "\n\t\t > Fecha: " + auxR.get("fecha") + ", Resultado: " + auxR.get("resultado");
            }
            
            resultado += "Avion " + i + ": "
                    + "\n\t - Modelo: " + aux.get("modelo")
                    + "\n\t - Año: " + aux.get("anio")
                    + "\n\t - Revisiones: " + resultRevisiones 
                    + "\n";
            i++;
        }

        return resultado;
    }

    private static String buscarVuelos(DBCollection collectionVuelos) {
        String resultado = "";
        DBCursor cursor = collectionVuelos.find();

        int i = 1;
        while (cursor.hasNext()) {
            BasicDBObject aux = (BasicDBObject) cursor.next();

            BasicDBList pasajeros = (BasicDBList) aux.get("pasajeros");
            String resultPasajero = "";
            
            for(Object p : pasajeros){
                BasicDBObject auxP = (BasicDBObject) p;
                resultPasajero += "\n\t * Nombre: " + auxP.getString("nombre") + ", Nacionalidad: " +
                        auxP.getString("nacionalidad") + ", Pasaporte: " + auxP.getString("pasaporte");                
            }
            
            BasicDBObject auxV = (BasicDBObject) aux.get("avion");
            BasicDBList revisiones = (BasicDBList) auxV.get("revisiones");
            String resultRevisiones = "";
            
            for(Object r : revisiones){
                BasicDBObject auxR = (BasicDBObject) r;
                resultRevisiones = "\n\t\t > Fecha: " + auxR.get("fecha") + ", Resultado: " + auxR.get("resultado");
            }           
            
            
            resultado += "Vuelo " + i + ":" +
                    "\n\t- Avion: \n\t * Modelo: " + auxV.get("modelo") + ", Año: " + auxV.get("anio") + ", Revisiones: " + resultRevisiones +
                    "\n\t- Ciudad origen: " + aux.getString("origen") +
                    "\n\t- Ciudad destino: " + aux.getString("destino") + 
                    "\n\t- Pasajeros: " + resultPasajero + "\n";
            i++;
        }

        return resultado;
    }

}
