package com.example.calc;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

/**
 * ��������Splash֮�����
 * @author sanko
 */
public class MainActivity extends Activity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    
    /**
     * ����Fragment�ֱ�ʵ�ּ������ͻ��㹦��
     */
    private List<Fragment> list_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_frag = new ArrayList<Fragment>();
        FirstFragment fragFirst = new FirstFragment();
        SecondFragment fragSecond = new SecondFragment();
        list_frag.add(fragFirst);
        list_frag.add(fragSecond);
                
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * �˵���Help About Exit
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //About��ʵ�֣�������ʾ
        if (id == R.id.action_about) {
        	new AlertDialog.Builder(this)
			.setTitle("About")
			.setMessage("sanko\n\nfor 21st birthday")
			.setNegativeButton("Best Wishes", new OnClickListener() {				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					arg0.dismiss();
				}
			})
			.show(); 
        }
        //Exit��ʵ�֣�ֱ��finish()
        if(id == R.id.action_exit) {
        	finish();
        	return true;
        }
        //Help��ʵ�֣�����HelpActivity
        if(id == R.id.action_help) {
        	Intent intent = new Intent(MainActivity.this, HelpActivity.class);
			startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Fragment�ĳ������
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	return list_frag.get(position);
        }

        @Override
        public int getCount() {
            return list_frag.size();
        }

    }

}
