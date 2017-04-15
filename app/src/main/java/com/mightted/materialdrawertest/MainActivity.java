package com.mightted.materialdrawertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.ExpandableBadgeDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        IProfile profile = new ProfileDrawerItem()
                .withName("soma5431")
                .withEmail("sosowolf0125@gmail.com")
                .withIcon("http://noavatar.csdn.net/5/D/8/1_soma5431.jpg")
                .withIdentifier(100);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(profile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        switch ((int)profile.getIdentifier()) {
                            case 100:
                                Toast.makeText(MainActivity.this,"the icon is clicked",Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        new DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_container)
//                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName("User")
                                .withDescription("This is a user")
                                .withIcon(R.drawable.ic_account_circle_black_24dp)
                                .withIdentifier(1)
                                .withSelectable(false),

                        new SectionDrawerItem().withName("menu组"),  //分组item，类似于group标签，无点击效果

                        new ExpandableBadgeDrawerItem() //伸缩式item
                                .withName("伸缩式圆角item")
                                .withIcon(R.drawable.ic_android_black_24dp)
                                .withIdentifier(2)
                                .withBadge("10")  //设置圆角气泡中的数字
                                .withSubItems(
                                        new SecondaryDrawerItem().withName("内部item1").withIdentifier(3)  //添加子item
                                ),

                        new SwitchDrawerItem()  //添加带有switch开关的item
                                .withName("开关item1")
                                .withIcon(R.drawable.ic_android_black_24dp)
                                .withIdentifier(3)
                                .withCheckable(false)
                                .withOnCheckedChangeListener(checkedChangeListener),

                        new SwitchDrawerItem()
                                .withName("开关item2")
                                .withIcon(R.drawable.ic_android_black_24dp)
                                .withIdentifier(4)
                                .withOnCheckedChangeListener(checkedChangeListener)
                                .withChecked(true)  //设置默认为ON状态
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //监听方法实现
                        Toast.makeText(MainActivity.this,"The DrawerItem is clicked",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
//                .withShowDrawerOnFirstLaunch(true)  //设置为默认启动抽屉菜单
                .build();
    }

    private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if(drawerItem instanceof Nameable) {
                Toast.makeText(MainActivity.this,((Nameable)drawerItem).getName() + "'s check is" + isChecked,Toast.LENGTH_SHORT).show();
            }

        }
    };
}
