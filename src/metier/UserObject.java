package metier;

public class UserObject {

	private String login;
	private String mdp;
	private boolean isProf;
	
	public UserObject(){};
	public UserObject(Object login, Object mdp, Object isProf){
		this.login = (String) login;
		this.mdp = (String) mdp;
		this.isProf = (boolean) isProf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public boolean isProf() {
		return isProf;
	}

	public void setProf(boolean isProf) {
		this.isProf = isProf;
	}
	
	
}
