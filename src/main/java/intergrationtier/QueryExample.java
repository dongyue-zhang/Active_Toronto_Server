/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergrationtier;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.xml.stream.events.Namespace;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Table;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import businesstier.transferobject.Activity;
import businesstier.transferobject.Address;
import businesstier.transferobject.Availability;
import businesstier.transferobject.Category;
import businesstier.transferobject.Facility;
import businesstier.transferobject.Type;
import businesstier.transferobject.TypeContent;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;

import intergrationtier.dao.AvailabilityDaoImpl;
import intergrationtier.dao.TypeDaoImpl;
import intergrationtier.dao.FacilityDaoImpl;
import intergrationtier.dao.AddressDaoImpl;
import intergrationtier.dao.CategoryDaoImpl;

import businesstier.TypeLogic;
import businesstier.ActivityLogic;
import businesstier.FacilityLogic;

/**
 *
 * @author Producer
 */
public class QueryExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        	FacilityLogic logic = FacilityLogic.getInstance();

        	List<Facility> list = logic.getWithTypeId(2077);
        	//List<Availability> availabilities = dao.findByType("Cards");
//        	for (Availability avai: availabilities) {
//        		avai.getFacility().getTranslation().getLanguageTranslations().forEach(r -> System.out.println(r.getDescription()));
//        	}
//        	addresses.forEach(r->r.getTranslation().getLanguageTranslations().forEach(a->System.out.println(a.getDescription())));
        	//availability.getFacility().getTranslation().getLanguageTranslations().forEach(r -> System.out.println(r.getDescription()));
//        	addresses.getTranslation().getLanguageTranslations().forEach(r -> System.out.println(r.getDescription()));
//        	System.out.println(address.getId());
//        	for (Activity con:list) {
//        		System.out.println(con.getType().getId());
//        	}
        	
        }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }       
    
        //StandardServiceRegistryBuilder.destroy(registry);
    
    }
    
    public static class MetadataExtractorIntegrator implements Integrator {

	    public final static MetadataExtractorIntegrator INSTANCE = new MetadataExtractorIntegrator();
	
	    private Database database;

		@Override
		public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
			database = metadata.getDatabase();
			
		}
	    public Database getDatabase() {
	        return database;
	    }
	

	    }
	    }

    

