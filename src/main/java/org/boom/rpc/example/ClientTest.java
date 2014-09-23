package org.boom.rpc.example;

import org.boom.rpc.client.RPCClient;
import org.boom.rpc.common.export.Service;

public class ClientTest {

	public static void main(String[] args) {
		String serverName = "test";
		String rpcUri = "rpc://127.0.0.1:1082";
		RPCClient client = new RPCClient(rpcUri);
		client.registDecodeEncode(new PersonDecodeEncode());
		client.connectService();
		
		Service service = client.getRemoteService(serverName, Service.class);
		System.out.println(service.test("12"));
		client.close();
	}
}
