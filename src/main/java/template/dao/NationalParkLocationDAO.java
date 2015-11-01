package template.dao;

import java.util.List;

import template.models.NationalParkLocation;

public interface NationalParkLocationDAO {
	public void saveOrUpdate(NationalParkLocation contact);
    
    public void delete(int contactId);
     
    public NationalParkLocation get(int contactId);
     
    public List<NationalParkLocation> list();
}
