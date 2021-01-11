package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarsController {
    private List<Car> cars = new ArrayList<>();

    {
        cars.add(new Car("Hyundai", "Solaris", 2015));
        cars.add(new Car("BMW", "X6", 2016));
        cars.add(new Car("Audi", "A4", 2017));
        cars.add(new Car("Nissan", "350Z", 2018));
        cars.add(new Car("Toyota", "Corolla", 2019));
    }

    @GetMapping("/cars")
    public String showCars(@RequestParam(value = "count", required = false) String count, Model model) {
        if (count != null) {
            List<Car> newCars = cars.stream().limit(Long.parseLong(count)).collect(Collectors.toList());
            model.addAttribute("cars", newCars);
        } else {
            model.addAttribute("cars", cars);
        }
        return "cars/carList";
    }
}
