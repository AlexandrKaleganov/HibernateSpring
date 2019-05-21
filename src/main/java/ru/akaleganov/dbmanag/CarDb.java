package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Car;

import java.util.List;

public class CarDb implements Store<Car> {
    private static final CarDb INSTANCE = new CarDb();

    public static CarDb getInstance() {
        return INSTANCE;
    }
    @Override
    public Car add(Car car) {
        error();
        return null;
    }

    @Override
    public Car delete(Car car) {
        error();
        return null;
    }

    @Override
    public Car edit(Car car) {
        return openSession(session -> {
            session.saveOrUpdate(car);
            return session.load(Car.class, car.getId());
        });
    }

    @Override
    public List<Car> findAll() {
        error();
        return null;
    }

    @Override
    public Car findByID(Car car) {
        error();
        return null;
    }

    @Override
    public List<Car> findByName(Car car) {
        error();
        return null;
    }

    @Override
    public Car findByLogin(Car car) {
        error();
        return null;
    }
}
