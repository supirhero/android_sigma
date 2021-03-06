package com.sigma.prouds.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sigma.prouds.FormReportIssueActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.ProjectIssueAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.ProjectIssueResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/15/2017.
 */

public class IssuesFragment extends BaseFragment
{
    public static final int REFRESH_ISSUE_REQ_CODE = 0;

    private ApiService service;
    static Context ctx;
    private String projectId;
    private ProudsApplication app;
    private ProjectIssueAdapter adapter;
    private RecyclerView rvIssue;
    private RelativeLayout rlAddIssue;
    private ProgressBar pbIssue;
    private TextView tvEmptyIssue;

    public static IssuesFragment newInstance(Context context, String projectId)
    {
        IssuesFragment fragment = new IssuesFragment();
        ctx = context;
        Bundle args = new Bundle();
        args.putString("project_id", projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_issues;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        projectId = getArguments().getString("project_id");
        rvIssue = (RecyclerView) view.findViewById(R.id.rv_issues);
        rlAddIssue = (RelativeLayout) view.findViewById(R.id.rl_report_issue);
        pbIssue = (ProgressBar) view.findViewById(R.id.pb_issue);
        tvEmptyIssue = (TextView) view.findViewById(R.id.tv_empty_issue);
        query.id(R.id.tv_empty_issue).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_report_issue).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        getIssueData();

        if (app.getSessionManager().getPrivilageUploadIssue())
        {
            rlAddIssue.setVisibility(View.VISIBLE);
        }
        else
        {
            rlAddIssue.setVisibility(View.GONE);
        }

        rlAddIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddIssue();
            }
        });
    }

    public void getIssueData()
    {
        pbIssue.setVisibility(View.VISIBLE);
        tvEmptyIssue.setVisibility(View.GONE);
        service.getProjectIssues(projectId, app.getSessionManager().getToken()).enqueue(new Callback<ProjectIssueResponse>()
        {
            @Override
            public void onResponse(Call<ProjectIssueResponse> call, Response<ProjectIssueResponse> response)
            {
                adapter = new ProjectIssueAdapter(ctx, response.body().getProjectIssueList());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
                rvIssue.setLayoutManager(mLayoutManager);
                rvIssue.setAdapter(adapter);

                pbIssue.setVisibility(View.GONE);
                if (response.body().getProjectIssueList().size() == 0)
                {
                    tvEmptyIssue.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ProjectIssueResponse> call, Throwable t)
            {

            }
        });
    }

    public void toAddIssue()
    {
        Intent intent = new Intent(getActivity(), FormReportIssueActivity.class);
        intent.putExtra("project_id", projectId);
        startActivityForResult(intent, REFRESH_ISSUE_REQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REFRESH_ISSUE_REQ_CODE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                getIssueData();
            }
        }
    }
}
