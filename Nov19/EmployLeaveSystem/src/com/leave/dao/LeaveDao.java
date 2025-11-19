package com.leave.dao;

import com.leave.model.LeaveDetails;
import java.util.List;

public interface LeaveDao {
    String addLeave(LeaveDetails leave);
    List<LeaveDetails> showLeave();
    LeaveDetails searchLeave(int leaveId);
    String updateLeave(LeaveDetails leave);
    String deleteLeave(int leaveId);
}
