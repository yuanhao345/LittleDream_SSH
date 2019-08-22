package com.yhbnb.service.DataJPA;

import java.util.List;

import com.yhbnb.entity.User;
import com.yhbnb.repository.DataJPA.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service

public class UserService {
	
    @Autowired
    private UserRepository userRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public String addUser(User user){
        /*if (log.isDebugEnabled()) {
            log.debug("保存用户信息[" + user.toString() + "]");
        }*/
        user = userRepository.save(user);
      /*  if (log.isDebugEnabled()) {
            log.debug("保存用户信息[" + user.toString() + "]成功！");
        }*/
        return "添加成功！";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delUser(String ids){
    /*    boolean flag = false;
        if (!ids.isEmpty()) {
            String[] id = ids.split(",");
            if (null != id && id.length > 0) {
                for (String did : id) {
                    userRepository.deleteById(did);
                    if (log.isDebugEnabled()) {
                        log.debug("删除数据点表ID为[" + id + "]的成功！");
                    }
                }
                flag = true;
            } else {
                userRepository.deleteById(ids);
                if (log.isDebugEnabled()) {
                    log.debug("删除数据点表ID为[" + id + "]成功！");
                }
                flag = true;
            }

        }
        return flag;*/
            return true;

    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<User> findAll(){
    	return userRepository.findAll();
    }
    
}