package com.senacbooks.senacbooks.users;

import com.senacbooks.senacbooks.categories.CategoryEntity;
import com.senacbooks.senacbooks.products.ProductEntity;
import com.senacbooks.senacbooks.roles.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName);

    @Query("SELECT DISTINCT obj FROM UserEntity obj INNER JOIN obj.roles cats WHERE " +
            "(COALESCE(:roles) IS NULL OR cats IN :roles) AND " +
            "(LOWER(obj.userName) LIKE LOWER(CONCAT('%',:userName,'%')) )")
    Page<UserEntity> find(List<RoleEntity> roles, String userName, Pageable pageable);
}
