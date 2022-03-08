package usedbooks.purchase.domain;

import java.sql.Date;
import java.time.LocalDateTime;

public class Member {

	private Long memberId;
	private String id;
	private String password;
	private String name;
	private Date birth;
	private String phoneNumber;
	private String address;

	public Member(Long memberId, String id, String password, String name, Date birth, String phoneNumber,
			String address) {
		super();
		this.memberId = memberId;
		this.id = id;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public Long getMemberId() {
		return memberId;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public Date getBirth() {
		return birth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

}
