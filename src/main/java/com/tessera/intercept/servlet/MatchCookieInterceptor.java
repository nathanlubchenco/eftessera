package com.tessera.intercept.servlet;

import java.util.*;
import java.util.regex.*;

import javax.servlet.http.*;

import com.tessera.intercept.*;

/**
 * TODO: Comment goes here. 
 *  
 * @author Lee Crawford (crawford@twofish.com)
 * @copyright Copyright (c) 2006-2009, Twofish, Inc.
 */

public class MatchCookieInterceptor 
	extends PredicateInterceptorSupport
{
	public
	MatchCookieInterceptor (final Map<String ,String> props)
	{
		super (props); 
		return; 
	}

	interface PROP
	{
		public String NAME = "name";
		public String PATTERN = "pattern"; 
	}

	@Override
	public
	void init ()
		throws Exception
	{
		super.init (); 
		this.name = require (PROP.NAME);
		this.pattern = Pattern.compile (require (PROP.PATTERN)); 
		return;
	}

	protected String name; 
	protected Pattern pattern;

	@Override
	public
	boolean evaluate (final HttpServletRequest req, final HttpServletResponse res) 
	{
		final String val = CookieUtil.getCookie (req, name); 
		return val != null && pattern.matcher (val).matches ();  
	} 
}

// EOF
