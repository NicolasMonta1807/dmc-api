package com.montanez.dmc_api.data;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.montanez.dmc_api.model.user.UserAccount;

public interface UserRepository extends MongoRepository<UserAccount, ObjectId> {

}
