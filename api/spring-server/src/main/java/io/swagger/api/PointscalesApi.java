package io.swagger.api;

import io.swagger.model.PointScale;
import io.swagger.model.NewPointScale;

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

@Api(value = "pointscales", description = "the pointscales API")
public interface PointscalesApi {

    @ApiOperation(value = "", notes = "Creates a new pointScale in the system.  Duplicates are allowed", response = PointScale.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pointScale response", response = PointScale.class) })
    @RequestMapping(value = "/pointscales",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PointScale> addPointScale(@ApiParam(value = "PointScale to add to the system" ,required=true ) @RequestBody NewPointScale pointScale);


    @ApiOperation(value = "", notes = "deletes a single pointScale based on the ID supplied", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "pointScale deleted", response = Void.class) })
    @RequestMapping(value = "/pointscales/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePointScale(@ApiParam(value = "ID of pointScale to delete",required=true ) @PathVariable("id") Long id);


    @ApiOperation(value = "", notes = "Returns the details of the badge based on a single ID", response = PointScale.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pointScale response", response = PointScale.class) })
    @RequestMapping(value = "/pointscales/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PointScale> findPointScaleById(@ApiParam(value = "ID of pointScale to fetch",required=true ) @PathVariable("id") Long id);


    @ApiOperation(value = "", notes = "Returns all badges from the system that the user has access to", response = PointScale.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pointscales response", response = PointScale.class) })
    @RequestMapping(value = "/pointscales",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<PointScale>> findPointScales();

}
