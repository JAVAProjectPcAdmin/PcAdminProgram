package test;

public class BookVO {
	private int bookId;
	private String title;
	private String publisher;
	private int price;
	private String year;
	
	public BookVO() {
	}
	public BookVO(int bookId, String title, String publisher, int price, String year) {
		this.bookId = bookId;
		this.title = title;
		this.publisher = publisher;
		this.price = price;
		this.year = year;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", title=" + title + ", publisher=" + publisher + ", price=" + price
				+ ", year=" + year + "]";
	}
	
	
}
