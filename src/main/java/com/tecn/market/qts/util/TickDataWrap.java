package com.tecn.market.qts.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TickDataWrap<T> {
	private T[] quoteArray = null;
	private long recvDataTime = 0;
	public TickDataWrap(long tstime, T[] quoteList)
	{
		recvDataTime = tstime;
		quoteArray = quoteList;
	}
	
	public long GetDataTime()
	{
	    SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    if(recvDataTime <= 0)
	    	return 0;
	    Date dataTime = new Date(recvDataTime);
	    String formatDataTime = sdFormatter.format(dataTime);
	    return Long.parseLong(formatDataTime);
	}
	
	public T[] GetDataList()
	{
	    return quoteArray;
	}
}
