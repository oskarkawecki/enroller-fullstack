package com.company.enroller.converter;

import com.company.enroller.dto.CreateMeetingDto;
import com.company.enroller.model.Meeting;

public class MeetingConverter {

    private MeetingConverter() {}

    public static Meeting fromDto(CreateMeetingDto dto) {
        Meeting meeting = new Meeting();
        meeting.setTitle(dto.getTitle());
        meeting.setDescription(dto.getDescription());
        meeting.setDate(dto.getDate());
        return meeting;
    }
}
