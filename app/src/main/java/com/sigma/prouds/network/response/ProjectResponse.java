package com.sigma.prouds.network.response;

import com.sigma.prouds.model.BusinessUnitExpendableModel;
import com.sigma.prouds.model.BusinessUnitModel;

import java.util.List;

/**
 * Created by 1414 on 7/18/2017.
 */

public class ProjectResponse
{
    private List<BusinessUnitExpendableModel> project;

    public List<BusinessUnitExpendableModel> getProject()
    {
        return project;
    }

    public void setProject(List<BusinessUnitExpendableModel> project)
    {
        this.project = project;
    }
}
