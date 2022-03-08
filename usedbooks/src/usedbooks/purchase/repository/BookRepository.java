package usedbooks.purchase.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import usedbooks.DbConnect;
import usedbooks.purchase.domain.Book;

public class BookRepository {
	private final DbConnect dbConnect = new DbConnect();

	// 전체 검색
	public List<Book> findAll() {
		Connection conn = dbConnect.getOracle();
		Statement stmt = null;
		ResultSet rs = null;

		List<Book> books = new Vector<Book>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sqls.FIND_ALL_BOOK);

			while (rs.next()) {
				Long bookId = rs.getLong("book_id");
				Long MemberId = rs.getLong("member_id");
				String name = rs.getString("book_name");
				String author = rs.getString("author");
				Date publicationDate = rs.getDate("publication_date");
				int price = rs.getInt("price");
				String quality = rs.getString("quality");

				books.add(new Book(bookId, MemberId, name, author, publicationDate, price, quality));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnect.dbClose(rs, stmt, conn);
		}

		return books;
	}

	// 이름으로 검색
	public List<Book> findAllByName(String BookName) {
		Connection conn = dbConnect.getOracle();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Book> books = new Vector<Book>();

		try {
			ps = conn.prepareStatement(Sqls.FIND_BOOK_BY_NAME);
			ps.setString(1, "%" + BookName + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				Long bookId = rs.getLong("book_id");
				Long MemberId = rs.getLong("member_id");
				String name = rs.getString("book_name");
				String author = rs.getString("author");
				Date publicationDate = rs.getDate("publication_date");
				int price = rs.getInt("price");
				String quality = rs.getString("quality");

				books.add(new Book(bookId, MemberId, name, author, publicationDate, price, quality));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnect.dbClose(rs, ps, conn);
		}

		return books;
	}

	public Book findById(long bookId) {
		Connection conn = dbConnect.getOracle();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;

		try {
			ps = conn.prepareStatement(Sqls.FIND_BOOK_BY_ID);
			ps.setLong(1, bookId);
			rs = ps.executeQuery();

			if (rs.next()) {
				bookId = rs.getLong("book_id");
				Long MemberId = rs.getLong("member_id");
				String name = rs.getString("book_name");
				String author = rs.getString("author");
				Date publicationDate = rs.getDate("publication_date");
				int price = rs.getInt("price");
				String quality = rs.getString("quality");

				book = new Book(bookId, MemberId, name, author, publicationDate, price, quality);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnect.dbClose(rs, ps, conn);
		}

		return book;
	}

	public void deleteById(long bookId) {
		Connection conn = dbConnect.getOracle();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(Sqls.DELETE_BOOK_BY_ID);
			ps.setLong(1, bookId);

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnect.dbClose(ps, conn);
		}

	}
}
