package usedbooks.purchase.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import usedbooks.purchase.domain.Member;

public class MemberRepository {
	private final DbConnect dbConnect = new DbConnect();

	public Member findById(long memberId) {

		Connection conn = dbConnect.getOracle();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Member member = null;

		try {
			ps = conn.prepareStatement(Sqls.FIND_MEMBER_BY_ID);
			ps.setLong(1, memberId);
			rs = ps.executeQuery();

			if (rs.next()) {
				memberId = rs.getLong("member_id");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				Date birth = rs.getDate("birth");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");

				member = new Member(memberId, id, password, name, birth, phoneNumber, address);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnect.dbClose(rs, ps, conn);
		}

		return member;
	}
}
