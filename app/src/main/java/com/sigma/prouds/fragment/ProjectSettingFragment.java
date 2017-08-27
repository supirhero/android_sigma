package com.sigma.prouds.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sigma.prouds.AddTimesheetActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.network.ApiService;
import com.sigma.prouds.network.ApiUtils;
import com.sigma.prouds.network.response.ProjectSettingResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by goy on 7/15/2017.
 */

public class ProjectSettingFragment extends BaseFragment
{

    private static final int START_DATE = 1;
    private static final int END_DATE = 2;

    private ApiService service;
    static Context ctx;
    private String projectId;
    private ProudsApplication app;

    private EditText etprojectId;
    private EditText etProjectName;
    private EditText etBusinessUnit;
    private EditText etRelatedBusinessUnit;
    private EditText etCustomer;
    private EditText etEndCustomer;
    private EditText etProjectValue;
    private EditText etMargin;
    private EditText etDesc;
    private RadioButton rbProject;
    private RadioButton rbNonProject;
    private RadioButton rbYesOperation;
    private RadioButton rbNoOperation;
    private EditText etPm;
    private EditText etAccountManager;
    private EditText etTypeEffort;
    private EditText etProductType;
    private EditText etProjectStatus;
    private EditText etStartDate;
    private EditText etEndDate;
    private Typeface latoRegular;
    private RelativeLayout rlProjectSetting;
    private ProgressDialog dialog;
    final Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startDate, endDate;

    public static ProjectSettingFragment newInstance(Context context, String projectId)
    {
        ProjectSettingFragment fragment = new ProjectSettingFragment();
        ctx = context;
        Bundle args = new Bundle();
        args.putString("project_id", projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_project_setting;
    }

    @Override
    protected void workingSpace(View view)
    {
        dialog = new ProgressDialog(ctx);
        dialog.setMessage("Please wait...");

        app = (ProudsApplication) ctx.getApplicationContext();
        service = ApiUtils.apiService();
        projectId = getArguments().getString("project_id");

        etprojectId = (EditText) view.findViewById(R.id.et_projectid);
        etProjectName = (EditText) view.findViewById(R.id.et_project_name);
        etBusinessUnit = (EditText) view.findViewById(R.id.et_business_unit);
        etRelatedBusinessUnit = (EditText) view.findViewById(R.id.et_related_business_unit);
        etCustomer = (EditText) view.findViewById(R.id.et_customer);
        etEndCustomer = (EditText) view.findViewById(R.id.et_end_customer);
        etProjectValue = (EditText) view.findViewById(R.id.et_project_val);
        etMargin = (EditText) view.findViewById(R.id.et_margin);
        etDesc = (EditText) view.findViewById(R.id.et_desc);
        rbProject = (RadioButton) view.findViewById(R.id.rb_project);
        rbNonProject = (RadioButton) view.findViewById(R.id.rb_non_project);
        rbYesOperation = (RadioButton) view.findViewById(R.id.rb_yes);
        rbNoOperation = (RadioButton) view.findViewById(R.id.rb_no);
        etPm = (EditText) view.findViewById(R.id.et_pm);
        etAccountManager = (EditText) view.findViewById(R.id.et_account_manager);
        etTypeEffort = (EditText) view.findViewById(R.id.et_type_effort);
        etProductType = (EditText) view.findViewById(R.id.et_product_type);
        etProjectStatus = (EditText) view.findViewById(R.id.et_project_status);
        etStartDate = (EditText) view.findViewById(R.id.et_start_date);
        etEndDate = (EditText) view.findViewById(R.id.et_end_date);
        rlProjectSetting = (RelativeLayout) view.findViewById(R.id.rl_project_setting);

        latoRegular = Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf");
        setFontForContainer(rlProjectSetting);

        rbProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rbNonProject.setChecked(false);
                rbProject.setChecked(true);
            }
        });

        rbNonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rbNonProject.setChecked(true);
                rbProject.setChecked(false);
            }
        });

        rbYesOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbYesOperation.setChecked(true);
                rbNoOperation.setChecked(false);
            }
        });

        rbNoOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbYesOperation.setChecked(false);
                rbNoOperation.setChecked(true);
            }
        });

        startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String dataFormat = "yyy-MM-dd";
                SimpleDateFormat sdfData = new SimpleDateFormat(dataFormat);
                etStartDate.setText(sdfData.format(calendar.getTime()));
            }
        };

        endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String dataFormat = "yyy-MM-dd";
                SimpleDateFormat sdfData = new SimpleDateFormat(dataFormat);
                etEndDate.setText(sdfData.format(calendar.getTime()));
            }
        };

        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), startDate, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        etEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), endDate, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        getProjectSettingData();

        etProjectStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(ctx);
                builderSingle.setTitle("Select status");
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("COMPLETED");
                arrayAdapter.add("CANCELED");
                arrayAdapter.add("IN PROGRESS");

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        etProjectStatus.setText(arrayAdapter.getItem(position));
                        dialog.dismiss();
                    }
                });
                builderSingle.show();
            }
        });
    }

    // set typeface for all text
    private void setFontForContainer(ViewGroup contentLayout) {
        for (int i=0; i < contentLayout.getChildCount(); i++) {
            View view = contentLayout.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView)view).setTypeface(latoRegular);
            }
            else if (view instanceof ViewGroup) {
                setFontForContainer((ViewGroup) view);
            }
        }
    }

    public void getProjectSettingData()
    {
        dialog.show();
        service.getProjectSetting(app.getSessionManager().getToken(), projectId).enqueue(new Callback<ProjectSettingResponse>() {
            @Override
            public void onResponse(Call<ProjectSettingResponse> call, Response<ProjectSettingResponse> response)
            {
                dialog.dismiss();
                etprojectId.setText(response.body().getProjectSetting().getProjectId());
                etProjectName.setText(response.body().getProjectSetting().getProjectName());
                etBusinessUnit.setText(response.body().getProjectSetting().getBuName());
                etRelatedBusinessUnit.setText(response.body().getProjectSetting().getRelatedBu());
                etCustomer.setText(response.body().getProjectSetting().getCustId());
                etCustomer.setText(response.body().getProjectSetting().getCustEndId());
                //etProjectValue.setText(response.body().getProjectSetting().getProje);
                etMargin.setText(response.body().getProjectSetting().getMargin());
                etDesc.setText(response.body().getProjectSetting().getProjectDesc());
                etPm.setText(response.body().getProjectSetting().getPmId());
                //etAccountManager.setText(response.body().getProjectSetting().getAc);
                etTypeEffort.setText(response.body().getProjectSetting().getTypeOfEffort());
                etProductType.setText(response.body().getProjectSetting().getProductType());
                etProjectStatus.setText(response.body().getProjectSetting().getProjectStatus());
                etStartDate.setText(response.body().getProjectSetting().getScheduleStart());
                etEndDate.setText(response.body().getProjectSetting().getScheduleEnd());

                if (response.body().getProjectSetting().getProjectTypeId().equalsIgnoreCase("non project"))
                {
                    rbProject.setChecked(false);
                    rbNonProject.setChecked(true);
                }
                else
                {
                    rbNonProject.setChecked(false);
                    rbProject.setChecked(true);
                }

                //if (response.body().getProjectSetting().)

            }

            @Override
            public void onFailure(Call<ProjectSettingResponse> call, Throwable t)
            {
                dialog.dismiss();
                Log.i("Fail", t.toString());
            }
        });
    }
}
