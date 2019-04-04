package pl.matiej.jdd.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.matiej.jdd.facade.JddFacade;
import pl.matiej.jdd.model.JobOfferFeDto;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Api(description = "Job offers controller")
@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = {"http://localhost:4200"})
@Slf4j
public class JobOfferController {

    private final JddFacade facade;
    private final JobOfferAssembler jobOfferAssembler;

    @Autowired
    public JobOfferController(JddFacade facade, JobOfferAssembler jobOfferAssembler) {
        this.facade = facade;
        this.jobOfferAssembler = jobOfferAssembler;
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "Add new job offer to data base", response = JobOfferFeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Job offer saved successful"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No offers saved!"),
            @ApiResponse(code = 503, message = "Data base server error. Can't add  measurement to data base.")})
    public ResponseEntity<Object> save(@RequestBody @Valid JobOfferFeDto jobOffer) {
        try {
            return ResponseEntity.status(201).body(facade.save(jobOffer));
        } catch (HibernateException e) {
            return ResponseEntity.status(503).body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "Update offer in data base", response = JobOfferFeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Job offer updated successful"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No offers updated!"),
            @ApiResponse(code = 500, message = "No job offer updated"),
            @ApiResponse(code = 503, message = "Data base server error. Can't add  measurement to data base.")})
    public ResponseEntity<Object> update(@RequestBody @Valid JobOfferFeDto jobOfferFeDto) {
        try {
            return ResponseEntity.status(200).body(facade.update(jobOfferFeDto));
        } catch (HibernateException e) {
            return ResponseEntity.status(503).body(e.getMessage());
        }
    }


    @GetMapping(value = "/findAll")
    @ApiOperation(value = "Get all offers from data base", response = JobOfferFeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Job offers loaded from db successful."),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No offers found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get job offers information from db.")})
    public ResponseEntity<Object> findAllOffers() {
        try {
            List<Resource<JobOfferFeDto>> resourceList = facade.findAll().stream()
                    .map(jobOfferAssembler::toResource)
                    .collect(toList());
            return ResponseEntity.ok().body(facade.findAll());
//            return ResponseEntity.status(200).body(new Resources<>(resourceList,
//                    linkTo(methodOn(JobOfferController.class).findAllOffers()).withSelfRel()));
        } catch (HibernateException e) {
            return ResponseEntity.status(503).body(e.getMessage());
        }
    }

    @GetMapping(value = "/findOne")
    @ApiOperation(value = "Get specific offer from data base by ID", response = JobOfferFeDto.class)
    @ApiImplicitParam(required = true, name = "offerId", value = "Offer ID in data base", paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Job offers loaded from db successful."),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No offer found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get job offers information from db.")})
    public ResponseEntity<Object> findOne(@RequestParam Long offerId) {
        try {
            JobOfferFeDto jobOfferFeDto = facade.findOne(offerId);
            return ResponseEntity.ok().body(jobOfferAssembler.toResource(jobOfferFeDto));
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (HibernateException e) {
            return ResponseEntity.status(503).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "Delete specific offer from data base by ID")
    @ApiImplicitParam(required = true, name = "offerId", value = "Offer ID in data base", paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Job offers deleted from db successful."),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No offer found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get job offers information from db.")})
    public ResponseEntity<Object> delete(Long offerId) {
        try {
            JobOfferFeDto jobOfferFeDto = facade.findOne(offerId);
            facade.delete(jobOfferFeDto);
            return ResponseEntity.ok().body(jobOfferFeDto);
        } catch (HibernateException e) {
            return ResponseEntity.status(503).body(e.getMessage());
        }
    }

}
