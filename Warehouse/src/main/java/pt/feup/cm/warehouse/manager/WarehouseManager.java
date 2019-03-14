package pt.feup.cm.warehouse.manager;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import pt.feup.cm.warehouse.entities.DbCart;
import pt.feup.cm.warehouse.entities.DbCartItem;
import pt.feup.cm.warehouse.entities.DbPayment;
import pt.feup.cm.warehouse.entities.DbProduct;
import pt.feup.cm.warehouse.entities.DbUser;
import pt.feup.cm.warehouse.enums.ErrorCode;
import pt.feup.cm.warehouse.enums.CartStatus;
import pt.feup.cm.warehouse.exception.BusinessException;
import pt.feup.cm.warehouse.exception.ServiceException;

public class WarehouseManager {

	public EntityManager em;

	public WarehouseManager() {
		em = Persistence.createEntityManagerFactory("WarehouseService").createEntityManager();
	}

	public DbUser addUser(String name, String password, String fiscalNumber) throws ServiceException {
		try {
			em.getTransaction().begin();
			DbUser dbUser = em.merge(new DbUser(name, password, fiscalNumber));
			em.getTransaction().commit();
			return dbUser;
		} catch (RollbackException e) {
			if (e.getMessage().contains("column fis_num is not unique")) {
				throw new BusinessException(e, ErrorCode.CODE_AUTH_FIS_NUM_UNIQUE);
			} else if (e.getMessage().contains("column name is not unique")) {
				throw new BusinessException(e, ErrorCode.CODE_AUTH_NAME_UNIQUE);
			}
			throw new ServiceException(e);
		}
	}

	public void deleteUser(long id) throws ServiceException {
		em.getTransaction().begin();
		em.remove(em.find(DbUser.class, id));
		em.getTransaction().commit();
	}

	public DbUser getUser(long id) throws ServiceException {
		return em.find(DbUser.class, id);
	}

	public DbUser getUserByName(String name) throws ServiceException {
		try {
			TypedQuery<DbUser> namedQuery = em.createNamedQuery("User.getByName", DbUser.class);
			namedQuery.setParameter("name", name);
			return namedQuery.getSingleResult();
		} catch (NoResultException e) {
			throw new BusinessException(e, ErrorCode.CODE_AUTH_NOT_FOUND);
		}
	}

	public List<DbUser> getAllUser() throws ServiceException {
		TypedQuery<DbUser> namedQuery = em.createNamedQuery("User.getAll", DbUser.class);
		return namedQuery.getResultList();
	}

	public boolean isValidUser(String name, String password) throws ServiceException {
		return password.equals(getUserByName(name).getPassword());
	}

	public List<DbProduct> getAllProducts() throws ServiceException {
		TypedQuery<DbProduct> namedQuery = em.createNamedQuery("Product.getAll", DbProduct.class);
		return namedQuery.getResultList();
	}

	public DbProduct getProductByBarcode(String barcode) throws ServiceException {
		TypedQuery<DbProduct> namedQuery = em.createNamedQuery("Product.getByBarcode", DbProduct.class);
		namedQuery.setParameter("barcode", barcode);
		return namedQuery.getSingleResult();
	}

	public DbProduct getProduct(long productId) throws ServiceException {
		return em.find(DbProduct.class, productId);
	}

	public void addUserCart(long id) throws ServiceException {
		try {
			em.getTransaction().begin();
			em.merge(new DbCart(id, CartStatus.ACTIVE.getValue()));
			em.getTransaction().commit();
		} catch (RollbackException e) {
			throw new ServiceException(e);
		}
	}

	public List<DbCart> getUserCart(DbUser user) throws ServiceException {
		TypedQuery<DbCart> namedQuery = em.createNamedQuery("Cart.getByUser", DbCart.class);
		namedQuery.setParameter("userId", user.getId());
		return namedQuery.getResultList();
	}

	public List<DbCartItem> getCartItems(DbCart cart) throws ServiceException {
		TypedQuery<DbCartItem> namedQuery = em.createNamedQuery("Product.getByCart", DbCartItem.class);
		namedQuery.setParameter("cartId", cart.getId());
		return namedQuery.getResultList();
	}

	public DbCartItem getCartItem(long cartItemId) throws ServiceException {
		return em.find(DbCartItem.class, cartItemId);
	}

	public void deleteCartItems(DbCart cart) throws ServiceException {
		em.getTransaction().begin();
		TypedQuery<DbCartItem> namedQuery = em.createNamedQuery("Product.deleteByCart", DbCartItem.class);
		namedQuery.setParameter("cartId", cart.getId());
		namedQuery.executeUpdate();
		em.getTransaction().commit();
	}

	public void deleteCartItem(long id) throws ServiceException {
		em.getTransaction().begin();
		em.remove(em.find(DbCartItem.class, id));
		em.getTransaction().commit();
	}

	public DbCart getUserActiveCart(DbUser user) throws ServiceException {
		TypedQuery<DbCart> namedQuery = em.createNamedQuery("Cart.getByStatus", DbCart.class);
		namedQuery.setParameter("userId", user.getId());
		namedQuery.setParameter("status", CartStatus.ACTIVE.getValue());
		return namedQuery.getSingleResult();
	}

	public void setCartPayed(DbCart dbCart) throws ServiceException {
		em.getTransaction().begin();
		dbCart.setCartStatus(CartStatus.PAYED.getValue());
		em.getTransaction().commit();
	}

	public DbCartItem addCartItem(Integer productId, Integer number, long cartId) throws ServiceException {
		try {
			em.getTransaction().begin();
			DbCartItem dbCartItem = em.merge(new DbCartItem(productId, number, cartId));
			em.getTransaction().commit();
			return dbCartItem;
		} catch (RollbackException e) {
			throw new ServiceException(e);
		}
	}

	public DbPayment doPayment(String token, long cartId, long userId, Double amount, String receipt) throws ServiceException {
		try {
			em.getTransaction().begin();
			DbPayment dbPayment = em.merge(new DbPayment(token, cartId, amount, userId, new Date(), receipt));
			em.getTransaction().commit();
			return dbPayment;
		} catch (RollbackException e) {
			throw new ServiceException(e);
		}
	}
	
	public DbPayment getPayment(String token) throws ServiceException {
		try {
			TypedQuery<DbPayment> namedQuery = em.createNamedQuery("Payment.getByToken", DbPayment.class);
			namedQuery.setParameter("token", token);
			return namedQuery.getSingleResult();
		} catch (RollbackException e) {
			throw new ServiceException(e);
		}
	}

}
