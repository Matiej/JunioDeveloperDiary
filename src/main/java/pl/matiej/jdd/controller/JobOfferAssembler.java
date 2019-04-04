package pl.matiej.jdd.controller;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import pl.matiej.jdd.model.JobOfferFeDto;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class JobOfferAssembler implements ResourceAssembler<JobOfferFeDto, Resource<JobOfferFeDto>> {

    @Override
    public Resource<JobOfferFeDto> toResource(JobOfferFeDto jobOfferFeDto) {

        return new Resource<JobOfferFeDto>(jobOfferFeDto,
                linkTo(methodOn(JobOfferController.class).findOne(jobOfferFeDto.getId())).withSelfRel(),
                linkTo(methodOn(JobOfferController.class).findAllOffers()).withRel("All job offers"));
    }
}
