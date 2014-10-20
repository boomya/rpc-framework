package org.boom.rpc.share.service;

import org.boom.rpc.share.domain.PostDTO;
import org.boom.rpc.share.domain.PostQueryDTO;
import org.boom.rpc.share.domain.RPCObject;
import org.boom.rpc.share.domain.UserDTO;

public interface Service {

	int addx(int a, int b);
	
	String test(String name);
	
	RPCObject.Person query(String name);
	
	String throwError();

    UserDTO find(String name);

    PostDTO findPostById(int id);
    PostDTO updatePost(PostQueryDTO postQueryDTO);
}
