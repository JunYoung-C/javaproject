package usedbooks.purchase.repository;

public class Sqls {
  public static final String FIND_ALL_BOOK = "select * from book";
  public static final String FIND_BOOK_BY_NAME = "select * from book where book_name like ?";
}
