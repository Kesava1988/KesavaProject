/**
 * 
 */
package solutions.egen.rrs.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Kesava
 * Jersey configuration and url mapping
 */
@ApplicationPath("/api")
public class RouteConfig extends ResourceConfig
{

	/**
	 * Constructor defines where rest of the implementation 
	 * for server calls are located
	 */
	public RouteConfig()
	{
		packages("solutions.egen.rrs.impl");
	}
	
}
