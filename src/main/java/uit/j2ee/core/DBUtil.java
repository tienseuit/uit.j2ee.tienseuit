package uit.j2ee.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import uit.j2ee.been.Device;
import uit.j2ee.util.Json;
 
public class DBUtil {

    private static final SessionFactory factory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            factory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return factory;
    }
    
    public static<T> T insert(T data){
        Session session = factory.openSession();
        Transaction tx = null; 
      try{
         tx = session.beginTransaction();
         session.save(data);
         tx.commit();
      }catch (HibernateException e) {
         data = null;
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return data;
   }
      
    public static<T> List<T> getList(Class<T> c){
        Session session = factory.openSession();
        
        List<T> re = session.createCriteria(c).list();
         
         session.close(); 
         
         return re;
 
   }
     
    
}
