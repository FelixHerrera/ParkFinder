/* 
 * Filename: NationalParkLocationService.java
 * Description: Declaration of interface for NationalParkLocationService
 */

package template.services;

import java.util.List;

import org.springframework.stereotype.Service;

import template.models.NationalParkLocation;

@Service
public interface NationalParkLocationService {
	
	public List<NationalParkLocation> getAllParkLocations();

}
