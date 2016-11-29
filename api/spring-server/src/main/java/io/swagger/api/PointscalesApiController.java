package io.swagger.api;

import io.swagger.model.PointScale;
import io.swagger.model.NewPointScale;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-29T09:54:16.483Z")

@Controller
public class PointscalesApiController implements PointscalesApi {

    public ResponseEntity<PointScale> addPointScale(@ApiParam(value = "PointScale to add to the system" ,required=true ) @RequestBody NewPointScale pointScale) {
        // do some magic!
        return new ResponseEntity<PointScale>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePointScale(@ApiParam(value = "ID of pointScale to delete",required=true ) @PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<PointScale> findPointScaleById(@ApiParam(value = "ID of pointScale to fetch",required=true ) @PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<PointScale>(HttpStatus.OK);
    }

    public ResponseEntity<List<PointScale>> findPointScales() {
        // do some magic!
        return new ResponseEntity<List<PointScale>>(HttpStatus.OK);
    }

}
