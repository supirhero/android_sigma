package com.sigma.prouds.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sigma.prouds.LoginActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.DetailProjectResponse;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/15/2017.
 */

public class OverviewFragment extends BaseFragment
{

    private ApiService service;
    static Context ctx;
    private String projectId;
    private ProudsApplication app;
    private ScrollView svOverview;
    private ProgressBar pbOverview;
    private TextView tvIwo;
    private Double ev, pv, ac, spi, cpi;

    public static OverviewFragment newInstance(Context context, String projectId) {
        OverviewFragment fragment = new OverviewFragment();
        ctx = context;
        Bundle args = new Bundle();
        args.putString("project_id", projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_overview;
    }

    @Override
    protected void workingSpace(View view)
    {
        tvIwo = (TextView) view.findViewById(R.id.tv_iwo);
        svOverview = (ScrollView) view.findViewById(R.id.sv_overview);
        pbOverview = (ProgressBar) view.findViewById(R.id.pb_overview);
        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        projectId = getArguments().getString("project_id");
        Log.i("Project id frg : ", getArguments().getString("project_id"));
        getProjectDetailData();
        setTypeFace();

    }

    public void getProjectDetailData()
    {
        service.getDetailProject(projectId, app.getSessionManager().getToken()).enqueue(new Callback<DetailProjectResponse>()
        {
            @Override
            public void onResponse(Call<DetailProjectResponse> call, Response<DetailProjectResponse> response)
            {
                if (query != null)
                {
                    query.id(pbOverview).gone();
                    query.id(svOverview).visible();
                    setView(response.body());
                }

            }

            @Override
            public void onFailure(Call<DetailProjectResponse> call, Throwable t)
            {

            }
        });
    }

    public void setView(DetailProjectResponse model)
    {
        DecimalFormat form = new DecimalFormat("#.##");
        try
        {
            ev = Double.parseDouble(model.getProjectPerformanceIndex().getEv());
            pv = Double.parseDouble(model.getProjectPerformanceIndex().getPv());
            ac = Double.parseDouble(model.getProjectPerformanceIndex().getAc());
            query.id(R.id.tv_ev).text(form.format(ev));
            query.id(R.id.tv_pv).text(form.format(pv));
            query.id(R.id.tv_ac).text(form.format(ac));

            spi = Double.parseDouble(model.getProjectPerformanceIndex().getSpi());
            query.id(R.id.tv_spi).text(form.format(spi));

            cpi = Double.parseDouble(model.getProjectPerformanceIndex().getCpi());
            query.id(R.id.tv_cpi).text(form.format(cpi));

        }
        catch (Exception e)
        {
            query.id(R.id.tv_ev).text("0");
            query.id(R.id.tv_pv).text("0");
            query.id(R.id.tv_ac).text("0");
            query.id(R.id.tv_spi).text("0");
            query.id(R.id.tv_cpi).text("0");
        }


        query.id(R.id.tv_iwo).text(model.getOverview().getIwo());
        query.id(R.id.tv_buo).text(model.getOverview().getBuOwner());
        query.id(R.id.tv_desc).text(model.getOverview().getDescription() + "");


        /*if (model.getProjectPerformanceIndex().getSpi().toLowerCase().contains("unable to count")) {
            query.id(R.id.tv_spi).text(model.getProjectPerformanceIndex().getSpi() + "");
        }
        else {
            spi = Double.parseDouble(model.getProjectPerformanceIndex().getSpi());
            query.id(R.id.tv_spi).text(form.format(spi));
        }

        if (model.getProjectPerformanceIndex().getCpi().toLowerCase().contains("unable to count")) {
            query.id(R.id.tv_cpi).text(model.getProjectPerformanceIndex().getCpi() + "");
        }
        else {
            cpi = Double.parseDouble(model.getProjectPerformanceIndex().getCpi());
            query.id(R.id.tv_cpi).text(form.format(cpi));
        }*/
    }

    public void setTypeFace() {
        query.id(R.id.tv_iwo).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_buo).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_desc).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_ev).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_pv).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_ac).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_spi).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_cpi).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));
        query.id(R.id.tv_overview_title_iwo).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_buo).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_desc).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_ev).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_pv).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_ac).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_spi).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_cpi).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_project_detail).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_workplan).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_completed).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_inprogress).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_notstarted).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_ondue).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_overdue).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
        query.id(R.id.tv_overview_title_project_detail).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_bold.ttf"));
    }
}
