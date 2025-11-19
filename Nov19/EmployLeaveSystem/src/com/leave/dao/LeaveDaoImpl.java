package com.leave.dao;

import com.leave.model.LeaveDetails;
import java.util.ArrayList;
import java.util.List;

public class LeaveDaoImpl implements LeaveDao {
    private static final List<LeaveDetails> leaveList = new ArrayList<>();

    @Override
    public String addLeave(LeaveDetails leave) {
        leaveList.add(leave);
        return "Leave added.";
    }

    @Override
    public List<LeaveDetails> showLeave() {
        return new ArrayList<>(leaveList);
    }

    @Override
    public LeaveDetails searchLeave(int leaveId) {
        for (LeaveDetails l : leaveList) {
            if (l.getLeaveId() == leaveId) return l;
        }
        return null;
    }

    @Override
    public String updateLeave(LeaveDetails leave) {
        for (int i = 0; i < leaveList.size(); i++) {
            if (leaveList.get(i).getLeaveId() == leave.getLeaveId()) {
                leaveList.set(i, leave);
                return "Leave updated.";
            }
        }
        return "Leave not found.";
    }

    @Override
    public String deleteLeave(int leaveId) {
        for (int i = 0; i < leaveList.size(); i++) {
            if (leaveList.get(i).getLeaveId() == leaveId) {
                leaveList.remove(i);
                return "Leave deleted.";
            }
        }
        return "Leave not found.";
    }
}
