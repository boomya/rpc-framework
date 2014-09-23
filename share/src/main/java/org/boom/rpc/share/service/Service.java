package org.boom.rpc.share.service;

import org.boom.rpc.share.domain.RPCObject;

public interface Service {

	int addx(int a, int b);
	
	String test(String name);
	
	RPCObject.Person query(String name);
	
	String throwError();
}
