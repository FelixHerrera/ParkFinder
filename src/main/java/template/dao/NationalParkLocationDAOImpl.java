package template.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import template.models.NationalParkLocation;

public class NationalParkLocationDAOImpl implements NationalParkLocationDAO {
	
	private JdbcTemplate jdbcTemplate;

	public NationalParkLocationDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveOrUpdate(NationalParkLocation location) {
		if (location.getId() > 0) {
			String sql = "UPDATE nationalParkLocation SET lat_coord=?, long_coord=?, name=?, terrain=? WHERE id=?";
			jdbcTemplate.update(sql, location.getLatCoord(), location.getLongCoord(), location.getName(), location.getTerrain(), location.getId());
		}
		else {
			String sql = "INSERT INTO nationalParkLocation (lat_coord, long_coord, name, terrain) VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, location.getLatCoord(), location.getLongCoord(), location.getName(), location.getTerrain());
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM nationalParkLocation WHERE id=?";
	    jdbcTemplate.update(sql, id);
	}

	@Override
	public NationalParkLocation get(int id) {
	    String sql = "SELECT * FROM contact WHERE contact_id=" + id;
	    return jdbcTemplate.query(sql, new ResultSetExtractor<NationalParkLocation>() {
	 
	        @Override
	        public NationalParkLocation extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                NationalParkLocation aNPL= new NationalParkLocation();
		            aNPL.setId(rs.getInt("id"));
		            aNPL.setLatCoord(Integer.parseInt(rs.getString("lat_coord")));
		            aNPL.setLongCoord(Integer.parseInt(rs.getString("address")));
		            aNPL.setName(rs.getString("name"));
		            aNPL.setTerrain(Integer.parseInt(rs.getString("telephone")));
	                return aNPL;
	            }
	 
	            return null;
	        }
	 
	    });
	}

	@Override
	public List<NationalParkLocation> list() {
	    String sql = "SELECT * FROM nationalParkLocation";
	    List<NationalParkLocation> listContact = jdbcTemplate.query(sql, new RowMapper<NationalParkLocation>() {
	 
	        @Override
	        public NationalParkLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
	            NationalParkLocation aNPL = new NationalParkLocation();
	 
	            aNPL.setId(rs.getInt("id"));
	            aNPL.setLatCoord(Integer.parseInt(rs.getString("lat_coord")));
	            aNPL.setLongCoord(Integer.parseInt(rs.getString("address")));
	            aNPL.setName(rs.getString("name"));
	            aNPL.setTerrain(Integer.parseInt(rs.getString("telephone")));
	 
	            return aNPL;
	        }
	 
	    });
	 
	    return listContact;
	}

}
