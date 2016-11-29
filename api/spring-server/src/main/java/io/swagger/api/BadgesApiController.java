package io.swagger.api;

import io.swagger.model.NewBadge;
import io.swagger.model.Badge;

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
public class BadgesApiController implements BadgesApi {

    public ResponseEntity<Badge> addBadge(@ApiParam(value = "Badge to add to the system" ,required=true ) @RequestBody NewBadge pet) {
        // do some magic!
        return new ResponseEntity<Badge>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteBadge(@ApiParam(value = "ID of badge to delete",required=true ) @PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Badge> findBadgeById(@ApiParam(value = "ID of the badge to fetch",required=true ) @PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<Badge>(HttpStatus.OK);
    }

    public ResponseEntity<List<Badge>> findBadges() {
        // do some magic!
        return new ResponseEntity<List<Badge>>(HttpStatus.OK);
    }

}
