package cn.lxt.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserByName(String name);
}
