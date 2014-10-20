package org.boom.rpc.example;

import org.boom.rpc.share.domain.PostDTO;
import org.boom.rpc.share.domain.PostQueryDTO;
import org.boom.rpc.share.domain.RPCObject;
import org.boom.rpc.share.domain.UserDTO;
import org.boom.rpc.share.service.Service;

public class TestService implements Service {

	@Override
	public int addx(int a, int b) {
		return a + b;
	}

	public String test(String name) {
//        try {
//            Thread.sleep(12000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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

    @Override
    public UserDTO find(String name) {
        System.out.println("-------find start");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1000);
        userDTO.setName(name);
        System.out.println("-------find end");
        return userDTO;
    }

    @Override
    public PostDTO findPostById(int id) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(id);
        postDTO.setContent("test");

        return postDTO;
    }

    @Override
    public PostDTO updatePost(PostQueryDTO postQueryDTO) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(postQueryDTO.getId());
        postDTO.setContent(postQueryDTO.getContent());
        return postDTO;
    }

}
