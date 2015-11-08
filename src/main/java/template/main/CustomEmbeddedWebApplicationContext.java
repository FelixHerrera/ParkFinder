package template.main;

import org.springframework.boot.context.embedded.XmlEmbeddedWebApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
public class CustomEmbeddedWebApplicationContext extends XmlEmbeddedWebApplicationContext {
	public CustomEmbeddedWebApplicationContext() {}
	
	public CustomEmbeddedWebApplicationContext(Resource resource) {
		this.load(resource);
	}
	
	@Override
	protected ResourcePatternResolver getResourcePatternResolver() {
		return new PathMatchingResourcePatternResolver(this.getClassLoader());
	}
}