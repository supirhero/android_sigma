package com.sigma.prouds.network.response;

import com.sigma.prouds.model.UserActivityTimesheetModel;
import com.sigma.prouds.model.UserProjectTimesheetModel;

import java.util.List;

/**
 * Created by lucgu.qolfiera on 8/15/2017.
 */

public class UserProjectTimesheetResponse
{
    List<UserProjectTimesheetModel> userProject;
    List<UserActivityTimesheetModel> userActivities;

    public List<UserProjectTimesheetModel> getUserProject() {
        return userProject;
    }

    public void setUserProject(List<UserProjectTimesheetModel> userProject) {
        this.userProject = userProject;
    }

    public List<UserActivityTimesheetModel> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(List<UserActivityTimesheetModel> userActivities) {
        this.userActivities = userActivities;
    }
}
