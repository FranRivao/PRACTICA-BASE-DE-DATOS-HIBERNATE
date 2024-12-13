package bbdd;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import bbdd.model.Pasajero;
import bbdd.model.Entretenimiento;
import bbdd.model.Gasto;


public class Main 
{
    public static void main( String[] args ) throws IOException {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        // @TODO Crear un nuevo pasajero llamado "Din Djarin" y un nuevo entretenimiento
        // llamado "Bounty Hunting" y guardarlos en la base de datos. Añade un gasto de
        // 100 a "Din Djarin" para "Bounty Hunting".
        Pasajero pasajero = new Pasajero("Bounty Hunting");
        Entretenimiento entretenimiento = new Entretenimiento("Din Djarin");
        Gasto gasto = new Gasto(pasajero, entretenimiento, 100);
        session.beginTransaction();
        session.save(entretenimiento);
        session.save(pasajero);
        session.save(gasto);
        pasajero.getGastos().add(gasto);
        session.update(pasajero);
        session.getTransaction().commit();
        

        // @TODO Leer el fichero CSV gastos.csv que se encuentra en el directorio "resources" y 
        // recorrerlo usando CSVParser para crear los pasajeros, entretenimientos y gastos que
        // en él se encuentran. Dichos gastos deberán ser asignados al pasajero/a y al entretenimiento 
        // correspondientes. Se deben guardar todos estos datos en la base de datos.
        String path = "./src/main/java/resources/gastos.csv";
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CSVFormat format = CSVFormat.DEFAULT.withHeader();
        CSVParser csvParser = new CSVParser(reader, format);

        for (CSVRecord csvRecord : csvParser) {

            System.out.println(csvRecord);

        }

        csvParser.close();
        reader.close();
        session.close();

    }
}





