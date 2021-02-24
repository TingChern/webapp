package tw.freely.impl.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.freely.impl.model.Member;

@RestController
@RequestMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

	private static HashMap<Integer, String> members;

	@PostConstruct
	private static void init() {
		members = new HashMap<>();
		members.put(1, "Apple");
		members.put(2, "Orange");
	}

	@GetMapping
	public ResponseEntity<List<Member>> get() {

		ArrayList<Member> list = new ArrayList<>();

		members.forEach((k, v) -> list.add(new Member(k.toString(), v)));

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Member> get(@PathVariable("id") String id) {

		Member member = new Member();
		member.setId(id);
		member.setName(members.get(Integer.parseInt(id)));

		return ResponseEntity.ok().body(member);
	}

	@PostMapping
	public Object post(@RequestBody Member member) {

		Integer key = members.keySet().stream().max(Integer::compare).get() + 1;
		members.put(key, member.getName());
		System.out.println(member.getName());

		Member newMember = new Member();
		newMember.setId(key.toString());
		newMember.setName(members.get(key));
		return member;
	}

	@PutMapping
	public ResponseEntity<Member> put(@RequestBody Member member) {
		Integer id = Integer.parseInt(member.getId());
		if (members.containsKey(id)) {
			members.put(id, member.getName());
			return ResponseEntity.ok(member);

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		members.remove(Integer.parseInt(id));
		return null;
	}

	public Object patch(String... pk) {
		// TODO Auto-generated method stub
		return null;
	}

}
