package com.wjx.android.weather.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wjx.android.weather.base.repository.BaseRepository
import com.wjx.android.weather.common.util.CommonUtil
import com.wjx.android.weather.common.state.State

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/6/3 22:51
 */
open class BaseViewModel<T : BaseRepository> : ViewModel(){
    val loadState by lazy {
        MutableLiveData<State>()
    }

    val mRepository : T by lazy {
        (CommonUtil.getClass<T>(this))
            .getDeclaredConstructor(MutableLiveData::class.java)
            .newInstance(loadState)
    }

    override fun onCleared() {
        super.onCleared()
        mRepository.unSubscribe()
    }
}
