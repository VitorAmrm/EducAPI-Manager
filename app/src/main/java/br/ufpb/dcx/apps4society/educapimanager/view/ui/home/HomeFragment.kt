package br.ufpb.dcx.apps4society.educapimanager.view.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import br.ufpb.dcx.apps4society.educapimanager.R
import br.ufpb.dcx.apps4society.educapimanager.view.NavDrawerActivity
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment(){

    private final var TAG : String = "HomeFragment"
    private lateinit var tabMenuAdapter : TabMenuAdapter
    private lateinit var viewPagerTabMenu : ViewPager
    private lateinit var tabLayoutTabMenu : TabLayout

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        tabMenuAdapter = TabMenuAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

        return root
    }

    fun fillTabMenu(view: View) {
        viewPagerTabMenu = view.findViewById(R.id.vpTabMenu)
        viewPagerTabMenu.adapter = tabMenuAdapter
        tabLayoutTabMenu = view.findViewById(R.id.tlTabMenu)
        tabLayoutTabMenu.setupWithViewPager(viewPagerTabMenu)
        viewPagerTabMenu.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutTabMenu))
        viewPagerTabMenu.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                Log.i(TAG,"ScrollState")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.i(TAG,"Scrolled")
            }

            override fun onPageSelected(position: Int) {
                NavDrawerActivity.OnChangeViewPagerFragment(object : NavDrawerActivity.OnNavDrawerListener {
                    override fun onChangeFragment(): Fragment {
                        Log.i(TAG,"CHANGE")
                        return tabMenuAdapter.getItem(position)
                    }
                })
            }

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillTabMenu(view)
    }

}