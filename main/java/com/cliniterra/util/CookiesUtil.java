package com.cliniterra.util;
/**
 * @author Aayush Shrestha
 */
import java.util.Arrays;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Ways to add, retrieve and delete cookies stored in browser 
 */
public class CookiesUtil {
	public static void addCookie(HttpServletResponse res, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/"); // Make cookie available to the entire application
		res.addCookie(cookie);
	}
	public static Cookie get(HttpServletRequest req, String name) { 
		if(req.getCookies() !=null) {
			return Arrays.stream(req.getCookies()).filter(cookie -> name.equals(cookie.getName())).findFirst()
					.orElse(null);
		}
		return null;
	}
	
	public static void delete(HttpServletResponse res, String name) { 
		Cookie cookie = new Cookie(name, null); 
		cookie.setMaxAge(0); 
		cookie.setPath("/");
		res.addCookie(cookie); 
	}
	
	 public static void addAppointmentCookie(HttpServletResponse res, String appointmentId, String appointmentDate, int maxAge) {
	        addCookie(res, "appointmentId", appointmentId, maxAge);
	        addCookie(res, "appointmentDate", appointmentDate, maxAge);
	    }
	 
	 public static Cookie getAppointmentCookie(HttpServletRequest req, String name) {
	        return get(req, name);
	    }
	 
	 public static void deleteAppointmentCookies(HttpServletResponse res) {
	        delete(res, "appointmentId");
	        delete(res, "appointmentDate");
	    }
	 
}
