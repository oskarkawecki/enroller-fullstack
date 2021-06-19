package com.company.enroller.controllers;

import com.company.enroller.dto.CreateMeetingDto;
import com.company.enroller.model.Meeting;
import com.company.enroller.persistence.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/api/meetings")
public class MeetingRestController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingRestController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public ResponseEntity<Collection<Meeting>> getMeetings() {
        Collection<Meeting> meetings = meetingService.getAll();
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Collection<Meeting>> createMeeting(@RequestBody CreateMeetingDto dto) {
        meetingService.createMeeting(dto);
        return ResponseEntity.ok().build();
    }
}
