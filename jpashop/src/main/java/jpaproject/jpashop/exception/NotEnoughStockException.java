package jpaproject.jpashop.exception;

public class NotEnoughStockException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotEnoughStockException() {
	 }
	 public NotEnoughStockException(String message) {
	 super(message);
	 }
	 public NotEnoughStockException(String message, Throwable cause) {
	 super(message, cause);
	 }
	 public NotEnoughStockException(Throwable cause) {
	 super(cause);
	 }

}
