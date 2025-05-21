package com.Leviata.Ecommerce.controllers;

import com.Leviata.Ecommerce.dto.DevRecordDto;
import com.Leviata.Ecommerce.model.DevModel;
import com.Leviata.Ecommerce.repositories.DevRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dev")
public class DevController {

    @Autowired
    private DevRepository devRepository;

    @GetMapping
    public ResponseEntity<DevModel> createDev(@RequestBody @Valid DevRecordDto devsRecordDto) {

        DevModel devModel = new DevModel();

        try {
            BeanUtils.copyProperties(devsRecordDto, devModel);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(devModel);
    }

    @GetMapping
    public ResponseEntity<List<DevModel>> getAllDevs() {

        return ResponseEntity.ok(devRepository.findAll());
    }

    @GetMapping
    public ResponseEntity<DevModel> getDevById(@RequestParam Long id) {

        Optional<DevModel> devExist = devRepository.findByEmail(id.toString());

        try {
            if (devExist.isPresent()) {
                return ResponseEntity.ok(devExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(devExist.get());
    }

    @GetMapping
    public ResponseEntity<DevModel> getDevByEmail(@RequestParam String email) {
        Optional<DevModel> devExist = devRepository.findByEmail(email);

        try {
            if (devExist.isPresent()) {
                return ResponseEntity.ok(devExist.get());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(devExist.get());
    }

    @GetMapping
    public ResponseEntity<DevModel> updateDevById(@PathVariable(value = "id") int id,
                                                  @RequestBody DevRecordDto devsRecordDto) {

        Optional<DevModel> devExist = devRepository.findById(id);

        if (devExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        var devModel = devExist.get();
        BeanUtils.copyProperties(devsRecordDto, devModel);
        return ResponseEntity.status(HttpStatus.OK).body(devRepository.save(devModel));
    }
}
