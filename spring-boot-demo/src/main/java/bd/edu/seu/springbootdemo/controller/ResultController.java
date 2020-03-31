package bd.edu.seu.springbootdemo.controller;import bd.edu.seu.springbootdemo.exceptions.ResourceAlreadyExistException;import bd.edu.seu.springbootdemo.exceptions.ResourceDoesNotExistException;import bd.edu.seu.springbootdemo.model.Result;import bd.edu.seu.springbootdemo.repository.StudentRepository;import bd.edu.seu.springbootdemo.service.ResultService;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import java.util.List;@RestController@RequestMapping(value = "/api/v1/result")public class ResultController {    private ResultService resultService;    public ResultController(ResultService resultService) {        this.resultService = resultService;    }    @GetMapping("")    public ResponseEntity<List<Result>> findAllResult(){        List<Result> results = resultService.findAll();        if(!results.isEmpty()){            return ResponseEntity.ok(results);        }else{            return ResponseEntity.notFound().build();        }    }    @GetMapping("/{id}")    public ResponseEntity<Result> findById(@PathVariable long id){        try {            Result result = resultService.findById(id);            return ResponseEntity.ok(result);        } catch (ResourceDoesNotExistException e) {            return ResponseEntity.notFound().build();        }    }    @PostMapping("")    public ResponseEntity<String> insertResult(@RequestBody Result result){        try {            Result insertedResult = resultService.insertResult(result);            return ResponseEntity.status(HttpStatus.CREATED).body("result submitted..");        } catch (ResourceAlreadyExistException e) {            return ResponseEntity.badRequest().body("id already exist..");        }    }    @PostMapping("/update/{id}")    public ResponseEntity<String> updateStudent(@RequestBody Result result, @PathVariable long id){        try {            resultService.updateResult(id,result);            return ResponseEntity.ok(id+" no. result updated successfully");        } catch (ResourceDoesNotExistException e) {            return ResponseEntity.badRequest().body("resource does not exist..");        }    }    @DeleteMapping("/{id}")    public ResponseEntity<String> deleteStudentById(@PathVariable long id){        try {            resultService.deleteResultById(id);            return ResponseEntity.ok(id+" no. result deleted..");        } catch (ResourceDoesNotExistException e) {            return ResponseEntity.badRequest().body(id+" does not exist..");        }    }}