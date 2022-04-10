package com.miu.mdp.resumebuildermary

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.miu.mdp.resumebuildermary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        setupViewPager()
    }

    private fun setupViewPager() {
        with(activityMainBinding.viewPager) {
            this.apply {
                adapter = ResumeViewPagerAdapter(this@MainActivity)
                setCurrentItem(0, true)

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                        configurePagerNavigation(position)
                    }
                })
            }
        }
    }

    private fun configurePagerNavigation(position: Int) {
        when (position) {
            0 -> homeState()
            1 -> educationState()
            2 -> workExpState()
            3 -> contactState()
            else -> homeState()
        }
    }

    private fun homeState() {
        with(activityMainBinding) {
            nextFrag.text = getString(R.string.education)
            prevFrag.text = ""
            prevFrag.isVisible = false
            nextFrag.setOnClickListener {
                activityMainBinding.viewPager.setCurrentItem(1, true)
            }
        }
    }

    private fun educationState() {
        with(activityMainBinding) {
            nextFrag.text = getString(R.string.work_experience)
            prevFrag.text = getString(R.string.home)
            prevFrag.isVisible = true
            nextFrag.setOnClickListener {
                activityMainBinding.viewPager.setCurrentItem(2, true)
            }
            prevFrag.setOnClickListener {
                activityMainBinding.viewPager.setCurrentItem(0, true)
            }
        }
    }

    private fun workExpState() {
        with(activityMainBinding) {
            nextFrag.text = getString(R.string.contact)
            prevFrag.text = getString(R.string.education)
            nextFrag.isVisible = true
            nextFrag.setOnClickListener {
                activityMainBinding.viewPager.setCurrentItem(3, true)
            }
            prevFrag.setOnClickListener {
                activityMainBinding.viewPager.setCurrentItem(1, true)
            }
        }
    }

    private fun contactState() {
        with(activityMainBinding) {
            nextFrag.text = ""
            nextFrag.isVisible = false
            prevFrag.text = getString(R.string.work_experience)
            prevFrag.setOnClickListener {
                activityMainBinding.viewPager.setCurrentItem(2, true)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout -> {
                val intent = Intent(this, LandingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

