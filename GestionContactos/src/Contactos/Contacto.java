package Contactos;

public class Contacto {

	private String name;
	private String phone;
	private String mail;
	
	
	public Contacto(String n, String p, String m) {
		this.name = n;
		this.phone = p;
		this.mail = m;
	};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
