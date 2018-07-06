package com.maruszka.dao;

import java.util.List;

import com.maruszka.entity.Hop;

public interface HopDAO {

	public List<Hop> getHops();
	
	public void saveHop (Hop theHop);
	
	public Hop getHop(int theId);
	
	public void deleteHop(int theId);
	
}
