package com.cliniterra.util;

/**
 * @author Aayush Shrestha
 */
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	
	public static void setSessionAt(HttpServletRequest req, String key, Object val) { 
		HttpSession session = req.getSession(); 
		session.setAttribute(key, val);
	}
	public static String getUsername(HttpServletRequest req) {
	    Object username = getAt(req, "username");
	    return username != null ? username.toString() : null;
	}

	public static Object getAt(HttpServletRequest req, String key) { 
		HttpSession session = req.getSession(false); 
		if (session != null) { 
			return session.getAttribute(key); 
		}
		else { 
			return null; 
		}
	}
	
	public static void destroySes(HttpServletRequest req) { 
		HttpSession session = req.getSession(false); 
		if (session != null) { 
			session.invalidate();
		}
	}
	public static void remAt(HttpServletRequest req, String key) { 
		HttpSession session = req.getSession(); 
		if (session != null ) { 
			session.removeAttribute(key);
		}
	}
	
	
}