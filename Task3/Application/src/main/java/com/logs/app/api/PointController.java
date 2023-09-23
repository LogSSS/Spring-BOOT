package com.logs.app.api;

import com.logs.app.service.PointService;
import com.logs.app.model.Point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class PointController {

    private final PointService service;

    @Autowired
    public PointController(PointService service) {
        this.service = service;
    }

    @PostMapping
    public void addDataSequence(@RequestBody List<Point> data) {
        service.addDataSequence(data);
    }

    @GetMapping
    public Iterable<Point> getAllDataSequences() {
        return service.getAllDataSequences();
    }

    @GetMapping("/calculate")
    public double calculateY(@RequestParam double x) {
        return service.calculateY(x);
    }
}
