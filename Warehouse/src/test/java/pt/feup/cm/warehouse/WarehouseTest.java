package pt.feup.cm.warehouse;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import pt.feup.cm.warehouse.entities.DbUser;
import pt.feup.cm.warehouse.exception.ServiceException;
import pt.feup.cm.warehouse.manager.WarehouseManager;

public class WarehouseTest {

	@Test
	public void testGetAll() {
		try {
			WarehouseManager manager = new WarehouseManager();
			List<DbUser> users 	= manager.getAllUser();
			for (DbUser user : users) {
				System.out.println(user.toString());
			}
			assertTrue(!users.isEmpty());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
