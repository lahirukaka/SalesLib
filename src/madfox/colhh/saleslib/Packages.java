/**
 * Copyright (c) 2014 Lahiru <lahirukaka@gmail.com>
 * 
 * This Code Section Packages.java in Package madfox.colhh.saleslib for Project SalesLib was 
 * developed by Lahiru under MadFox Brand Name at Nov 21, 2014.
 * 
 * All rights reserved by MadFox Developer Lahiru
 */
package madfox.colhh.saleslib;




public enum Packages {
	
	JOKES_SENDER("madfox.colhh.jokessender","Jokes Sender",R.drawable.joke_sender_64_64),
	QUOTES_SENDER("madfox.colhh.quotessender","Quotes Sender",R.drawable.quotes_sender_64_64),
	BD_WISHES_SENDER("madfox.colhh.birthdaywishessender","Birthday Wishes Sender",R.drawable.bd_wishes_sender_64_64),
	XMAS_SENDER("madfox.colhh.xmaswishessender","Christmas Wishes Sender",R.drawable.xmas_wishes_sender_64_64),
	SEC_CON("madfox.colhh.secretcontacts","Secret Contacts",R.drawable.sc_64_64),
	SSP("msg.madfox.first","SMS Scheduler Plugin",R.drawable.mssp_64_64),
	USACELEB("madfox.colhh.usacelebrityquiz","USA Celebrity Quiz",R.drawable.mssp_64_64);
	
	private String pkg;
	private String name;
	private int logo;
	
	Packages(String pkg, String name,int logo)
	{
		this.pkg=pkg;
		this.name=name;
		this.logo=logo;
	}
	
	public final String getName()
	{
		return name;
	}
	
	public final String getPackage()
	{
		return pkg;
	}
	
	public final int getLogo()
	{
		return logo;
	}
}
