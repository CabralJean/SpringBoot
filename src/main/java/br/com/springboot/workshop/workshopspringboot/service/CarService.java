package br.com.springboot.workshop.workshopspringboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.workshop.workshopspringboot.domain.Car;
import br.com.springboot.workshop.workshopspringboot.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CpfRemoteService cpfRemoteService;

	public Car create(final Car car) throws Exception {

		final Car carAlreadyExists = this.carRepository.findOneByNameIgnoreCase(car.getName());
		
		if (carAlreadyExists != null)
			throw new Exception("Carro já existe");
		
		cpfRemoteService.findCpfByName(car.getName()).ifPresent(cpf -> car.setOwner(cpf.get("cpf")));
		
		final LocalDateTime now = LocalDateTime.now();

		car.setCreatedAt(now);
		car.setUpdatedAt(now);

		return this.carRepository.insert(car);
	}

	public Car update(final Car car) {

		car.setUpdatedAt(LocalDateTime.now());

		return this.carRepository.save(car);
	}

	public void delete(final String id) {

		final Car car = this.findOne(id);

		this.carRepository.delete(car);
	}

	public List<Car> findAll() {
		return this.carRepository.findAll();
	}

	public Car findOne(final String id) {
		return this.carRepository.findOne(id);
	}

}
