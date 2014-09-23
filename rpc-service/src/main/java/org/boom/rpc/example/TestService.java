package org.boom.rpc.example;

import org.boom.rpc.share.domain.RPCObject;
import org.boom.rpc.share.service.Service;

public class TestService implements Service {

	@Override
	public int addx(int a, int b) {
		return a + b;
	}

	public String test(String name) {
		StringBuilder sb = new StringBuilder("hello:");
		sb.append(name);
		return sb.toString();
	}

	@Override
	public RPCObject.Person query(String name) {
		RPCObject.Person.Builder builder = RPCObject.Person.newBuilder();
		builder.setId(0);
		builder.setName(name);
		RPCObject.Person person = builder.build();
		return person;
	}

	@Override
	public String throwError() {
		throw new RuntimeException("err");
	}

}
