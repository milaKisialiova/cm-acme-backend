package pt.feup.cm.service;

import pt.feup.cm.warehouse.manager.WarehouseManager;

public class BaseService {

	private static WarehouseManager warehouseManager;

	
	public static WarehouseManager getWarehouseManager() {
		if (warehouseManager == null) {
			warehouseManager = new WarehouseManager();
		}
		return warehouseManager;
	}
}
