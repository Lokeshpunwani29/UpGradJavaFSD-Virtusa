package com.leave.bal;

import com.leave.dao.LeaveDao;
import com.leave.dao.LeaveDaoImpl;
import com.leave.exception.LeaveException;
import com.leave.model.LeaveDetails;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LeaveBal {
    private final LeaveDao dao = new LeaveDaoImpl();

    public String addLeave(LeaveDetails leave) throws LeaveException {
        validateDates(leave.getLeaveStartDate(), leave.getLeaveEndDate());
        int days = calculateDays(leave.getLeaveStartDate(), leave.getLeaveEndDate());
        leave.setNoOfDays(days);
        leave.setAppliedOn(dateFromLocal(LocalDate.now()));
        return dao.addLeave(leave);
    }

    public java.util.List<LeaveDetails> showLeave() {
        return dao.showLeave();
    }

    public LeaveDetails searchLeave(int leaveId) {
        return dao.searchLeave(leaveId);
    }

    public String updateLeave(LeaveDetails leave) throws LeaveException {
        validateDates(leave.getLeaveStartDate(), leave.getLeaveEndDate());
        int days = calculateDays(leave.getLeaveStartDate(), leave.getLeaveEndDate());
        leave.setNoOfDays(days);
        leave.setAppliedOn(dateFromLocal(LocalDate.now()));
        return dao.updateLeave(leave);
    }

    public String deleteLeave(int leaveId) {
        return dao.deleteLeave(leaveId);
    }

    private void validateDates(Date start, Date end) throws LeaveException {
        if (start == null || end == null) throw new LeaveException("Start date and end date are required.");

        LocalDate startLd = localDateFromDate(start);
        LocalDate endLd = localDateFromDate(end);
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        // disallow past dates and yesterday
        if (startLd.isBefore(today) || startLd.equals(yesterday)) {
            throw new LeaveException("leaveStartDate cannot be yesterday or a past date.");
        }
        if (endLd.isBefore(today) || endLd.equals(yesterday)) {
            throw new LeaveException("leaveEndDate cannot be yesterday or a past date.");
        }

        // start cannot be after end
        if (startLd.isAfter(endLd)) {
            throw new LeaveException("leaveStartDate cannot be greater than leaveEndDate.");
        }
    }

    private int calculateDays(Date start, Date end) {
        LocalDate s = localDateFromDate(start);
        LocalDate e = localDateFromDate(end);
        long daysBetween = ChronoUnit.DAYS.between(s, e) + 1; // inclusive
        return (int) daysBetween;
    }

    private LocalDate localDateFromDate(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Date dateFromLocal(LocalDate ld) {
        return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
