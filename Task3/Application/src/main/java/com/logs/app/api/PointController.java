package com.logs.app.api;

import com.logs.app.dao.PointRepository;
import com.logs.app.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class PointController {
    @Autowired
    private PointRepository repository;

    @PostMapping
    public void addDataSequence(@RequestBody List<Point> data) {
        repository.deleteAll();

        repository.saveAll(data);
    }

    @GetMapping
    public Iterable<Point> getAllDataSequences() {
        return repository.findAll();
    }

    @GetMapping("/calculate")
    public double calculateY(@RequestParam double x) {

        Iterable<Point> sequences = repository.findAll();
        SimpleRegression regression = new SimpleRegression();

        for (Point point : sequences)
            regression.addData(point.getX(), point.getY());


        double intercept = regression.getIntercept();
        double slope = regression.getSlope();

        return intercept + slope * x;
    }
}
