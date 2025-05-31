package com.kenny.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * 
 * @Title: CookieUtils.java
 * @Package com.kenny.utils
 * @Description: Cookie utility class
 * Copyright: Copyright (c)
 * Company: www.kenny.com
 *
 * @author Kenny
 * @version V1.0
 */
public final class CookieUtils {

    final static Logger logger = LoggerFactory.getLogger(CookieUtils.class);
	
	/**
	 * 
	 * @Description: Get Cookie value without encoding
	 * @param request
	 * @param cookieName
	 * @return
	 */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }
    
    /**
     * 
     * @Description: Get Cookie value
     * @param request
     * @param cookieName
     * @param isDecoder
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
                    } else {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 
     * @Description: Get Cookie value
     * @param request
     * @param cookieName
     * @param encodeString
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
        	 e.printStackTrace();
        }
        return retValue;
    }

    /**
     * @Description: Set Cookie value with encoding, without setting effective time
     * Created on the server, returned to the client, and saved on the client
     * If SETMAXAGE(int seconds) is set, the cookie will be saved on the client's hard disk
     * If not set, the cookie will be saved in the browser's memory by default
     * Once setPath() is set: the cookie information can only be obtained through the set path
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }

    /**
     * 
     * @Description: Set Cookie value with expiration time, without encoding
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * @Description: Set Cookie value without setting effective time, but encode
     * Created on the server, returned to the client, and saved on the client
     * If SETMAXAGE(int seconds) is set, the cookie will be saved on the client's hard disk
     * If not set, the cookie will be saved in the browser's memory by default
     * Once setPath() is set: the cookie information can only be obtained through the set path
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param isEncode
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

   /**
    * @Description: Set Cookie value to be effective within the specified time, with encoding parameter
    */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * @Description: Set Cookie value to be effective within the specified time, with encoding parameter (specified encoding)
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * @Description: Delete Cookie with domain
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName) {
        doSetCookie(request, response, cookieName, null, -1, false);
//        doSetCookie(request, response, cookieName, "", -1, false);
    }

    
    /**
     * @Description: Set Cookie value and make it effective within the specified time
     * @param cookieMaxage Maximum seconds for cookie to be effective
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) { // Set domain cookie
            	String domainName = getDomainName(request);
                logger.info("========== domainName: {} ==========", domainName);
                if (!"localhost".equals(domainName)) {
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }

    /**
     * @Description: Set Cookie value and make it effective within the specified time
     * @param cookieMaxage Maximum seconds for cookie to be effective
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) { // Set domain cookie
            	String domainName = getDomainName(request);
                logger.info("========== domainName: {} ==========", domainName);
                if (!"localhost".equals(domainName)) {
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }

    /**
     * @Description: Get the domain name of the cookie
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            if (serverName.indexOf(":") > 0) {
            	String[] ary = serverName.split("\\:");
            	serverName = ary[0];
            }

            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3 && !isIp(serverName)) {
            	// www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }
        return domainName;
    }
    
    public static String trimSpaces(String IP){//Remove all spaces before and after the IP string  
        while(IP.startsWith(" ")){  
               IP= IP.substring(1,IP.length()).trim();  
            }  
        while(IP.endsWith(" ")){  
               IP= IP.substring(0,IP.length()-1).trim();  
            }  
        return IP;  
    }  
    
    public static boolean isIp(String IP){//Check if it is an IP  
        boolean b = false;  
        IP = trimSpaces(IP);  
        if(IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")){  
            String s[] = IP.split("\\.");  
            if(Integer.parseInt(s[0])<255)  
                if(Integer.parseInt(s[1])<255)  
                    if(Integer.parseInt(s[2])<255)  
                        if(Integer.parseInt(s[3])<255)  
                            b = true;  
        }  
        return b;  
    }  

}
