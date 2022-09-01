package io.github.hrworker.resources;

import io.github.hrworker.domain.Worker;
import io.github.hrworker.repository.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("/workers")
public class WorkerResource {


    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Autowired
    private Environment env;

    @Value("${test.config}")
    private String defaultConfig;
    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<Page<Worker>> findAll(Pageable pageable) {
        Page<Worker> list = workerRepository.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/configs")
    public ResponseEntity<Void> getConfigs() {
        logger.info("CONFIG = " + defaultConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        logger.info("PORT = " + env.getProperty("local.server.port"));
        Worker obj = workerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(obj);
    }

}
