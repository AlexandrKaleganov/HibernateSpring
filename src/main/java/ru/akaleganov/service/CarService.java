package ru.akaleganov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akaleganov.domain.Car;
import ru.akaleganov.repository.CarRepository;

import java.util.List;

@Service
public class CarService implements Store<Car> {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car add(Car car) {
        this.carRepository.save(car);
        return car;
    }

    @Override
    public Car delete(Car car) {
        error();
        return null;
    }

    @Override
    public Car edit(Car car) {
        this.carRepository.save(car);
        return car;
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
    public Car findByLoginPass(Car car) {
        error();
        return null;
    }

    @Override
    public Car findByLogin(Car car) {
        error();
        return null;
    }
}
