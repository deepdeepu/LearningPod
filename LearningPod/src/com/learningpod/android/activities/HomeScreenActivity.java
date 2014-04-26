package com.learningpod.android.activities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;






import com.learningpod.android.BackgroundAsyncTasks;
import com.learningpod.android.BackgroundTasks;
import com.learningpod.android.BaseActivity;
import com.learningpod.android.R;
import com.learningpod.android.beans.pods.PodBean;


public class HomeScreenActivity extends BaseActivity implements OnClickListener  {
	 
	private List<PodBean> pods = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		String userName = extras.get("username").toString();
		//get list of pods
		pods  = (List<PodBean>)extras.getSerializable("pods");
		
		setContentView(R.layout.home_screen);
		
		TextView txtUserName = (TextView)findViewById(R.id.username);
		txtUserName.setText("Hello, " + userName + "!");
		// this is only for testing
		//populatePods();
		
		LinearLayout scroller = (LinearLayout)findViewById(R.id.horizontalscroller);
		
		
		for(int idx=0;idx<pods.size();idx++) {
			PodBean pod = pods.get(idx);
			View podView =  getLayoutInflater().inflate(R.layout.pod_view, null);
			ViewGroup.LayoutParams params = podView.getLayoutParams();
			
			TextView podTitleView = (TextView)podView.findViewById(R.id.podtitle);
			podTitleView.setId(idx);
			podTitleView.setOnClickListener(this);
			podTitleView.setText(pod.getTitle());
			
			TextView podDescView = (TextView)podView.findViewById(R.id.poddesc);
			podDescView.setId(idx);
			podDescView.setOnClickListener(this);
			podDescView.setText(pod.getDescription());	
			
			scroller.addView(podView);
		}
		
	}
	
	private void populatePods(){
		PodBean pod = new PodBean();
		pod.setTitle("Learning Pod 1");
		pod.setDescription("This is the description of learning Pod 1");
		
		PodBean pod1 = new PodBean();
		pod1.setTitle("Learning Pod 1");
		pod1.setDescription("This is the description of learning Pod 1");
		
		PodBean pod2 = new PodBean();
		pod2.setTitle("Learning Pod 1");
		pod2.setDescription("This is the description of learning Pod 1");
		
		PodBean pod3 = new PodBean();
		pod3.setTitle("Learning Pod 1");
		pod3.setDescription("This is the description of learning Pod 1");
		
		pods.add(pod);
		pods.add(pod1);
		pods.add(pod2);
		pods.add(pod3);
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		PodBean selectedPod = pods.get(id);
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("selectedPod",selectedPod);
		new BackgroundAsyncTasks(this, params).execute(BackgroundTasks.LOAD_POD_QUESTIONS);
		
	}
}
