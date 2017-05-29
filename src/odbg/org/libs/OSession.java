package odbg.org.libs;

import javax.servlet.http.HttpSession;

public class OSession {
	
	private HttpSession session;
	
	public OSession() {}
	
	public OSession(HttpSession session) {
		this.session = session;
	}
	
	public boolean hasSession() {
		return this.hasSession();
	}
	
	public void setSession( HttpSession httpSession ) {
		this.session = httpSession;
	}
	
	
	
}
