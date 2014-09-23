package org.boom.rpc.common.export;

import org.boom.rpc.example.RPCObject.Person;

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
	public Person query(String name) {
		Person.Builder builder = Person.newBuilder();
		builder.setId(0);
		builder.setName(name);
		Person person = builder.build();
		return person;
	}

	@Override
	public String throwError() {
		throw new RuntimeException("err");
	}

}
