package com.example.avoidagglomeration;



import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	 private DrawerLayout mDrawerLayout;
	    private ListView mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle;

	    private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
	    private String[] mPlanetTitles;
	    private String[] navTitles;
	    public static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        navTitles = getResources().getStringArray(R.array.titles_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);    
        
        
       // mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

//        View header = getLayoutInflater().inflate(R.layout.header, null);
//        mDrawerList.addHeaderView(header);
//        TextView text = (TextView) findViewById(R.id.header_brand);
//        text.setText(title);

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                //getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       
    	
    	// Inflate the menu; this adds items to the action bar if it is present.
    	 boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
         //  menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
           return super.onPrepareOptionsMenu(menu);
//    	getMenuInflater().inflate(R.menu.main, menu);
//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
    	 if (mDrawerToggle.onOptionsItemSelected(item)) {
             return true;
         }
         // Handle action buttons
         switch(item.getItemId()) {
        /* case R.id.action_websearch:

                 Toast.makeText(getApplication(), "Buscar", Toast.LENGTH_LONG).show();

             return true;*/
         default:
             return super.onOptionsItemSelected(item);
         }
    }
    
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);
        
        FragmentManager fragmentManager = getFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.content_frame, maps).commit();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(navTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

//    @Override
//    public void setTitle(CharSequence title) {
//        mTitle = title;
//        getSupportActionBar().setTitle(mTitle);
//    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }
        
        public View rootView;
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
           // View rootView;
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            
           
            Log.d("position", Integer.toString(i));
            
            switch (i) {
			case 0: //Header del usuario
				//rootView = inflater.inflate(R.layout.fragment_profile, container, false);
				rootView = inflater.inflate(R.layout.fragment_search, container, false);
				
				break;
				
			case 1: //Perfil
				//Profilefragment profile = new Profilefragment();
				Fragment maps = new Mapsfragment();
		       // Bundle args = new Bundle();
		        //Fragment fragment3 = new Locationfragment();
//		    
//		        

		        FragmentManager fragmentManager3 = getFragmentManager();
		        //fragmentManager.beginTransaction().replace(R.id.content_frame, maps).commit();
		        fragmentManager3.beginTransaction().replace(R.id.content_frame, maps).commit();
//				Bundle titulo = new Bundle();
//			    titulo.putString("str", title);
//			    profile.setArguments(titulo);
//				FragmentManager fragmentManager2 = getFragmentManager();
//		        
//		        fragmentManager2.beginTransaction().replace(R.id.content_frame, profile).commit();

			
				break;
				
			case 2: //Subir foto
				rootView = inflater.inflate(R.layout.fragment_terms, container, false);
				
			case 3: //Ver fotos en la galer�a
//				rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
//			    GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
//			    gridview.setAdapter(new ImageAdapter(mContext));
//
//			    gridview.setOnItemClickListener(new OnItemClickListener() {
//			        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//			            Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
//			        }
//			    });
				
				

				break;
				
			case 4: //Bases del concurso
				//rootView = inflater.inflate(R.layout.fragment_terms, container, false);

				break;
				
			case 5: //Ubicaci�n de las fotos
				
//				Fragment maps = new Mapsfragment();
//		        Bundle args = new Bundle();
//		        //Fragment fragment3 = new Locationfragment();
////		    
////		        
//
//		        FragmentManager fragmentManager3 = getFragmentManager();
//		        //fragmentManager.beginTransaction().replace(R.id.content_frame, maps).commit();
//		        fragmentManager3.beginTransaction().replace(R.id.content_frame, maps).commit();
//				

			     
				break;

			default:
				break;
			}
            
/*            String planet = getResources().getStringArray(R.array.planets_array)[i];
            Log.d("position", Integer.toString(i));
            TextView textDrawer = (TextView) rootView.findViewById(R.id.fragment_text);
            textDrawer.setText(planet);*/
            
/*            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(planet);*/
            return rootView;
        }
    }
}
