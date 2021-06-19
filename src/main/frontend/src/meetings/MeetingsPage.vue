<template>
  <div>
    <new-meeting-form @added="addNewMeeting($event)"></new-meeting-form>

    <span v-if="meetings.length == 0">
               Brak zaplanowanych spotkań.
           </span>
    <h3 v-else>
      Zaplanowane zajęcia ({{ meetings.length }})
    </h3>

    <meetings-list :meetings="meetings"
                   :username="username"
                   @attend="addMeetingParticipant($event)"
                   @unattend="removeMeetingParticipant($event)"
                   @delete="deleteMeeting($event)"></meetings-list>
  </div>
</template>

<script>
import NewMeetingForm from "./NewMeetingForm";
import MeetingsList from "./MeetingsList";

export default {
  components: {NewMeetingForm, MeetingsList},
  props: ['username'],
  data() {
    return {
      meetings: []
    };
  },
  created() {
    fetch('http://localhost:8080/api/meetings', {
      mode: "cors"
    })
        .then(response => response.json())
        .then(meetings => this.meetings = meetings)
        .then(meeting => console.log(meeting))
  },
  methods: {
    addNewMeeting(meeting) {
      this.meetings.push(meeting);
      fetch('http://localhost:8080/api/meetings', {
        method: "POST",
        mode: "cors",
        headers: {
          'Content-Type': "application/json"
        },
        body: JSON.stringify(meeting)
      })
          .catch(exc => console.log(exc));
    },
    addMeetingParticipant(meeting) {
      let idx = this.meetings.indexOf(meeting);
      let foundMeeting = this.meetings[idx];
      if (!foundMeeting.participants) {
        foundMeeting.participants = [];
      }
      foundMeeting.participants.push(this.username);
      this.meetings.splice(idx, 1, foundMeeting);
    },
    removeMeetingParticipant(meeting) {
      let idx = this.meetings.indexOf(meeting);
      let foundMeeting = this.meetings[idx];
      foundMeeting.participants.splice(foundMeeting.participants.indexOf(this.username), 1);
      this.meetings.splice(idx, 1, foundMeeting);
    },
    deleteMeeting(meeting) {
      this.meetings.splice(this.meetings.indexOf(meeting), 1);
    }
  }
}
</script>
