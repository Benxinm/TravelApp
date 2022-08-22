package com.benxinm.travelapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.benxinm.travelapp.logic.Repository

class OtherDetailViewModel:ViewModel() {
    private val targetLiveData=MutableLiveData<String>()
    val getIntroHisLiveData=Transformations.switchMap(targetLiveData){target->
        Repository.getIntroHis(target)
    }
    val getRecipeLiveData=Transformations.switchMap(targetLiveData){target->
        Repository.getRecipe(target)
    }
    val getRestaurantLiveData=Transformations.switchMap(targetLiveData){target->
        Repository.getRestaurant(target)
    }
    val getFood=Transformations.switchMap(targetLiveData){target->
        Repository.getFood(target)
    }
    val getRelatedSight=Transformations.switchMap(targetLiveData){target->
        Repository.getRelatedSight(target)
    }
    fun changeTarget(target:String){
        targetLiveData.value=target
    }
}