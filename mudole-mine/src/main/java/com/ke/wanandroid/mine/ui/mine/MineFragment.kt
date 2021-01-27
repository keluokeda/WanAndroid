package com.ke.wanandroid.mine.ui.mine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hi.dhl.binding.viewbind
import com.ke.mvvm.base.ui.BaseFragment
import com.ke.rximagepicker.RxImagePicker
import com.ke.wanandroid.common.const.PagePath
import com.ke.wanandroid.common.util.FileUtils
import com.ke.wanandroid.mine.R
import com.ke.wanandroid.mine.databinding.MineFragmentMineBinding
import com.ke.wanandroid.mine.model.UserInfo
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation
import java.io.File


@AndroidEntryPoint
class MineFragment : BaseFragment(R.layout.mine_fragment_mine) {

    private val binding: MineFragmentMineBinding by viewbind()

    private val viewModel: MineViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.refresh()
            }
            avatar.setOnClickListener {
                viewModel.avatarClicked()
            }
            image.setOnClickListener {
                viewModel.headerClicked()
            }
            myCoin.setOnClickListener {
                ARouter.getInstance().build(PagePath.MY_COIN).navigation()
            }
            coinRank.setOnClickListener {
                ARouter.getInstance().build(PagePath.COIN_RANK).navigation()
            }
            myCollection.setOnClickListener {
                ARouter.getInstance().build(PagePath.MY_COLLECTIONS).navigation()
            }
            myShare.setOnClickListener {
                ARouter.getInstance().build(PagePath.MY_SHARE_ARTICLES).navigation()
            }
            laterRead.setOnClickListener {
                ARouter.getInstance().build(PagePath.LATER_READ).navigation()
            }
            history.setOnClickListener {
                ARouter.getInstance().build(PagePath.ARTICLE_RECORDS).navigation()
            }
            setting.setOnClickListener {
                ARouter.getInstance().build(PagePath.SETTINGS).navigation()
            }
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = it
        }

        viewModel.refreshEnable.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isEnabled = it
        }

        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                MineViewModel.EVENT_PICK_BACKGROUND -> {
                    RxImagePicker(requireActivity())
                        .pick(RxImagePicker.SOURCE_GALLERY)
                        .subscribe { result ->
                            if (result.uri != null) {
                                viewModel.setHeaderImageUri(
                                    FileUtils.getPathFromUri(
                                        requireContext(),
                                        result.uri
                                    )
                                )
                            }
                        }
                }
                MineViewModel.EVENT_TO_LOGIN -> {
                    ARouter.getInstance().build(PagePath.LOGIN).navigation()
                }
                MineViewModel.EVENT_PICK_AVATAR -> {
                    RxImagePicker(requireActivity())
                        .pick(RxImagePicker.SOURCE_GALLERY)
                        .subscribe { result ->
                            if (result.uri != null) {
                                viewModel.setUserIconImagePath(
                                    FileUtils.getPathFromUri(
                                        requireContext(),
                                        result.uri
                                    )
                                )
                            }
                        }
                }
            }
        }


        viewModel.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is UserInfo.LoginUser -> {
                    if (it.iconImagePath == null) {
                        binding.avatar.setImageResource(it.defaultIconSrc)
                    } else {
//                        binding.avatar.setImageURI(Uri.parse(it.iconUri))
                        Glide.with(this)
                            .load(File(it.iconImagePath))
                            .into(binding.avatar)
                    }

                    if (it.backgroundImagePath == null) {
                        binding.image.setImageResource(it.defaultBackgroundSrc)
                    } else {
//                        binding.image.setImageURI(Uri.parse(it.backgroundUri))
                        Glide.with(this)
                            .load(File(it.backgroundImagePath))
                            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
                            .into(binding.image)
                    }
                    binding.name.text = it.username
                    binding.uid.text = it.userIdMessage
                    binding.level.text = it.levelMessage
                }
                UserInfo.NoLogin -> {
                    binding.avatar.setImageResource(it.defaultIconSrc)
                    binding.image.setImageResource(it.defaultBackgroundSrc)
                    binding.name.text = "去登录"
                    binding.uid.text = null
                    binding.level.text = null
                }
            }
        }
    }
}