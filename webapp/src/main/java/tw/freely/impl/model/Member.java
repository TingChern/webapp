package tw.freely.impl.model;

public class Member{
	private String id;
	public String getId() {
		return id;
	}
	
	public Member() {
	}

	public Member(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
