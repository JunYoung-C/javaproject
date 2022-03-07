package usedbooks.purchase.domain;

import java.sql.Date;
import java.time.LocalDateTime;

public class Book {
  private Long bookId;
  private Long MemberId;
  private String name;
  private String author;
  private Date publicationDate;
  private int price;
  private String quality;

  public Book(Long bookId, Long memberId, String name, String author, Date publicationDate,
      int price, String quality) {
    super();
    this.bookId = bookId;
    MemberId = memberId;
    this.name = name;
    this.author = author;
    this.publicationDate = publicationDate;
    this.price = price;
    this.quality = quality;
  }

  public Long getBookId() {
    return bookId;
  }

  public Long getMemberId() {
    return MemberId;
  }

  public String getName() {
    return name;
  }

  public String getAuthor() {
    return author;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public int getPrice() {
    return price;
  }

  public String getQuality() {
    return quality;
  }
}
