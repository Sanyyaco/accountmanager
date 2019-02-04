package com.restapp.accountmanager.repository;

import com.restapp.accountmanager.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Account,Integer> {
}
