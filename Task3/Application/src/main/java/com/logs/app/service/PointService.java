package com.logs.app.service;

import com.logs.app.dao.PointRepository;
import com.logs.app.model.Point;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

    public final PointRepository repository;

    @Autowired
    public PointService(PointRepository repository) {
        this.repository = repository;
    }

    public void addDataSequence(List<Point> data) {
        repository.deleteAll();
        repository.saveAll(data);
    }


    public Iterable<Point> getAllDataSequences() {
        return repository.findAll();
    }

    public double calculateY(double x) {
        Iterable<Point> sequences = repository.findAll();
        SimpleRegression regression = new SimpleRegression();

        for (Point point : sequences)
            regression.addData(point.getX(), point.getY());

        return regression.getIntercept() + regression.getSlope() * x;
    }

}