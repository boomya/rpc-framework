package org.boom.rpc.example;

import com.alibaba.fastjson.JSON;
import org.boom.rpc.client.RPCClient;
import org.boom.rpc.share.domain.PostDTO;
import org.boom.rpc.share.domain.PostQueryDTO;
import org.boom.rpc.share.domain.RPCObject;
import org.boom.rpc.share.domain.UserDTO;
import org.boom.rpc.share.service.Service;

public class ClientTest {

	public static void main(String[] args) {
		String serverName = "test";
		String rpcUri = "rpc://127.0.0.1:1082";
		RPCClient client = new RPCClient(rpcUri);
//		client.registDecodeEncode(new PersonDecodeEncode());
        client.registDecodeEncode(new JSONDecodeEncode());
		client.connectService();
		
		Service service = client.getRemoteService(serverName, Service.class);
        try{
//            System.out.println(service.test("12"));
//            RPCObject.Person preson = service.query("12");
            UserDTO userDTO = service.find("test");
            System.out.println(JSON.toJSONString(userDTO));

//            UserDTO userDTO2 = service.find("test");
//            System.out.println(JSON.toJSONString(userDTO2));
//
//            UserDTO userDTO3 = service.find("test");
//            System.out.println(JSON.toJSONString(userDTO3));

            PostDTO postDTO = service.findPostById(1000);
            System.out.println(JSON.toJSONString(postDTO));

            PostQueryDTO postQueryDTO = new PostQueryDTO();
            postQueryDTO.setId(200);
            postQueryDTO.setContent("222222222");
            PostDTO postDTO2 = service.updatePost(postQueryDTO);
            System.out.println(JSON.toJSONString(postDTO2));


        }catch (Exception ex){
            ex.printStackTrace();
        }

		client.close();
	}
}
