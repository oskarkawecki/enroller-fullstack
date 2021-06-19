package com.company.enroller.persistence;

import com.company.enroller.converter.MeetingConverter;
import com.company.enroller.dto.CreateMeetingDto;
import com.company.enroller.model.Meeting;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingService")
public class MeetingService {

    DatabaseConnector connector;

    public MeetingService() {
        connector = DatabaseConnector.getInstance();
    }

    public Collection<Meeting> getAll() {
        String hql = "FROM Meeting";
        Query query = connector.getSession().createQuery(hql);
        return query.list();
    }

    public void createMeeting(CreateMeetingDto dto) {
        Meeting meeting = MeetingConverter.fromDto(dto);
        connector.getSession().save(meeting);
    }

}
