package com.ke.wanandroid.mine.ui.coinrank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ke.mvvm.base.ui.BaseFragmentActivity
import com.ke.wanandroid.common.const.PagePath
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Route(path = PagePath.COIN_RANK)
class CoinRankActivity : BaseFragmentActivity() {
    override val fragment: Fragment
        get() = CoinRankFragment()

}