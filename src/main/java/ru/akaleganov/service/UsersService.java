package ru.akaleganov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akaleganov.domain.Users;
import ru.akaleganov.repository.UsersRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Service
public class UsersService implements Store<Users> {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public Users add(Users users) {
        Users test = this.findByLogin(users);
        if (test.getId() != 0L) {
            return users;
        } else {
            return this.usersRepository.save(users);
        }
    }

    @Override
    public Users delete(Users users) {
        this.usersRepository.delete(users);
        return users;
    }

    @Override
    public Users edit(Users users) {
        return this.usersRepository.save(users);
    }

    @Override
    public List<Users> findAll() {
        return (List<Users>) this.usersRepository.findAll();
    }

    @Override
    public Users findByID(Users users) {
        return this.usersRepository.findById(users.getId()).orElse(new Users(0));
    }

    @Override
    public List<Users> findByName(Users users) {
        String sql = "from Users where name = '" + users.getName() + "'";
        return this.usersRepository.findAllByName(users.getName());
    }

    @Override
    public Users findByLoginPass(Users users) {
        return this.usersRepository.findByLoginAndPassword(users.getLogin(), users.getPassword()).orElse(new Users());
    }

    @Override
    public Users findByLogin(Users users) {
        return this.usersRepository.findByLogin(users.getLogin()).orElse(new Users());
    }


}
