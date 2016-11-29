package io.swagger.api;

import io.swagger.model.NewBadge;
import io.swagger.model.Badge;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T09:54:16.483Z")

@Api(value = "badges", description = "the badges API")
public interface BadgesApi {

    @ApiOperation(value = "", notes = "Creates a new badge in the system.  Duplicates are allowed", response = Badge.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "badge response", response = Badge.class) })
    @RequestMapping(value = "/badges",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Badge> addBadge(@ApiParam(value = "Badge to add to the system" ,required=true ) @RequestBody NewBadge pet);


    @ApiOperation(value = "", notes = "deletes a single pet based on the ID supplied", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "badge deleted", response = Void.class) })
    @RequestMapping(value = "/badges/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteBadge(@ApiParam(value = "ID of badge to delete",required=true ) @PathVariable("id") Long id);


    @ApiOperation(value = "", notes = "Returns a badge based on a single ID", response = Badge.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "badge response", response = Badge.class) })
    @RequestMapping(value = "/badges/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Badge> findBadgeById(@ApiParam(value = "ID of the badge to fetch",required=true ) @PathVariable("id") Long id);


    @ApiOperation(value = "", notes = "Returns all badges from the system that the user has access to", response = Badge.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "badges response", response = Badge.class) })
    @RequestMapping(value = "/badges",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Badge>> findBadges();

}
