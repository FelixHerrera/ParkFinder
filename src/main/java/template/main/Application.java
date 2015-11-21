package template.main;

import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public class Application {
	private static final String APPLICATION_CONTEXT = "configuration/applicationContext.xml";

	public Application(String[] args) {
	
	}

	public void run() {
		// TODO JONATHAN: I have not figured out how to use the spring api with this, so we are creating it manually
		CacheManager cm = CacheManager.create();
		
		Cache getPlaceDetailsCache = new Cache(
				new CacheConfiguration("getPlaceDetails", 1000) // Create a new cache that can contain 1000 objects 
				    .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU) 
				    .eternal(false)  
				    .diskExpiryThreadIntervalSeconds(3600)
				    .diskPersistent(true)); 
		cm.addCache(getPlaceDetailsCache);
		
		@SuppressWarnings("resource")
		CustomEmbeddedWebApplicationContext context = 
				new CustomEmbeddedWebApplicationContext(new ClassPathResource(APPLICATION_CONTEXT));

		context.refresh();
		context.start();
		
		context.registerShutdownHook();
	}

	public static void run(String[] args) {
		new Application(args).run();
	}
}
