package intergrationtier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class DataSource implements ServletContextListener{
	private static SessionFactory FACTORY = null;
//	private static SessionFactory FACTORY = getSessionFactory();
    private static Session session = null;
    
    private SessionFactory getSessionFactory() {
//    	Configuration configuration = new Configuration();
//    	configuration.configure();
//    	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
//    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	BootstrapServiceRegistry bootstrapServiceRegistry = 
    			  new BootstrapServiceRegistryBuilder().build();
    			StandardServiceRegistryBuilder standardServiceRegistryBuilder = 
    			  new StandardServiceRegistryBuilder(bootstrapServiceRegistry);
		StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder
				  .configure()
				  .build();
		SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    	return sessionFactory;
    }
    
    public static Session getSession() {
    	if (session == null) {
    		session = FACTORY.openSession();
    	}
    	return FACTORY.getCurrentSession();
        
    }
    
    public static void closeSession() {
    	try {
        	if (session != null) {
        		session.close();
        	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    }
    
//    public Iterable<Namespace> getNamespace() {
//    	return integrator.getDatabase().getNamespaces();
//    }
    
//    public static class MetadataExtractorIntegrator implements Integrator {
//
//	    public final static MetadataExtractorIntegrator INSTANCE = new MetadataExtractorIntegrator();
//	
//	    private Database database;
//
//		@Override
//		public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
//			database = metadata.getDatabase();
//			
//		}
//	    public Database getDatabase() {
//	        return database;
//	    }
//		@Override
//		public void disintegrate(SessionFactoryImplementor arg0, SessionFactoryServiceRegistry arg1) {
//			// TODO Auto-generated method stub
//			
//		}
//	
//
//	    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		String propertyFilePath = System.getProperty("property.file");
//		Properties properties = new Properties();
//		try (FileInputStream inputStream = new FileInputStream(propertyFilePath)) {
//		    properties.load(inputStream);
//		} catch (IOException e) {
//		    // Handle exceptions if the file cannot be loaded
//		    e.printStackTrace();
//		}
//		String databaseUsername = properties.getProperty("db.username");
//		String databasePassword = properties.getProperty("db.password");
//		String databaseUrl = properties.getProperty("db.url");
//		
		
		if (FACTORY == null) {
			FACTORY = getSessionFactory();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (FACTORY != null) {
			FACTORY.close();
		}
		
	}

}
