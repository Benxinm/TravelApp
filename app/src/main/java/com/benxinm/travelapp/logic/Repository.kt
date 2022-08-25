package com.benxinm.travelapp.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.benxinm.travelapp.data.User
import com.benxinm.travelapp.data.responseModel.MyCollectModel
import com.benxinm.travelapp.data.responseModel.PostDetailModel
import com.benxinm.travelapp.data.responseModel.PostModel
import com.benxinm.travelapp.data.responseModel.TwoParamShowModel
import com.benxinm.travelapp.logic.network.util.network.*
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody

object Repository {
    /**
     * Login
     */
    fun login(username: String, password: String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("Login R","1")
            val loginResponse = LoginNetwork.login(username, password)
            Log.d("Login R","2")
            if (loginResponse.code == 200) {
                val user = loginResponse.data
                Result.success(user)
            } else {
                Result.failure(RuntimeException("response status is${loginResponse.message}"))
            }
        } catch (e: Exception) {
            Log.d("Login R","3")

            Result.failure(e)
        }
        emit(result)
    }

    fun register(username: String, nickname: String, password: String, rePassword: String) =
        liveData(Dispatchers.IO) {
            val result = try {

                val registerResponse =
                    LoginNetwork.register(username, nickname, password, rePassword)
                if (registerResponse.code == 200) {
                    val user = registerResponse.data
                    Result.success(user)
                } else {
                    Result.failure(RuntimeException("response status is${registerResponse.message}"))
                }
            } catch (e: Exception) {

                Result.failure(e)
            }
            emit(result)
        }

    fun sendEmail(email: String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("Login 1","1")
            val sendEmailResponse = LoginNetwork.sendEmail(email)
            if (sendEmailResponse.code == 200) {
                Result.success(true)
            } else {
                Result.failure(RuntimeException("response status is${sendEmailResponse.message}"))
            }
        } catch (e: Exception) {
            Log.d("Login 1","2")
            Result.failure(e)
        }
        emit(result)
    }

    fun verifyCode(code: String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("Login v","1")

            val codeResponse = LoginNetwork.verifyCode(code)
            if (codeResponse.code == 200) {
                Result.success(true)
            } else {
                Result.failure(RuntimeException("response status is${codeResponse.message}"))
            }
        } catch (e: Exception) {
            Log.d("Login v","2")
            Result.failure(e)
        }
        emit(result)
    }

    /**
     * User
     */
    fun getFanSubNum(email: String) = liveData(Dispatchers.IO) {
        val result = try {
            val numResponse = UserNetwork.getFanSubNum(email)
            if (numResponse.code == 200) {
                Result.success(numResponse.data)
            } else {
                Result.failure(RuntimeException("response status is${numResponse.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
    fun uploadImage(token: String,email: String,file:MultipartBody.Part)= liveData(Dispatchers.IO) {
        val result=try {
            Log.d("ImageUpload","被调用")
            val response=UserNetwork.uploadImage(token,email,file)
            Log.d("ImageUpload","成功一半")
            if (response.code==200){
                Log.d("ImageUpload","成功")
                Log.d("ImageUpload",response.data["url"]?:"1")
                Result.success(response.data)
            }else{
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        }catch (e: Exception) {
            Log.d("ImageUpload",e.message?:"")
            Log.d("ImageUpload",e.toString())
            Result.failure(e)
        }
        emit(result)
    }

    fun getMyCollect(email: String)= liveData(Dispatchers.IO) {
        val result=try {
            val response=UserNetwork.getFoodCollect(email)
            val response1=UserNetwork.getStoreCollect(email)
            if (response.code==200){
                val list= mutableListOf<MyCollectModel>()
                response.data.forEach {
                    list.add(MyCollectModel(it[0].toLong(),it[1],it[2]))
                }
                response1.data.forEach {
                    list.add(MyCollectModel(it[0].toLong(),it[1],it[2]))
                }
                Result.success(list.toList())
            }else{
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        }catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
    fun follow(token: String,email: String,target: String)= liveData(Dispatchers.IO){
        val result=try {
            Log.d("follow R","1")
            val response= UserNetwork.follow(token, email, target)
            Log.d("follow R","2")
            if (response.code==200){
                Result.success(response.data)
            }else{
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        } catch (e:Exception){
            Log.d("follow R",e.toString())
            Result.failure(e)
        }
        emit(result)
    }
    fun changeNickname(token: String,email: String,newNickname:String)= liveData(Dispatchers.IO){
        val result=try {
            Log.d("follow R","1")
            val response= UserNetwork.changeNickname(token, email, newNickname)
            Log.d("follow R","2")
            if (response.code==200){
                Result.success(response.data)
            }else{
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        } catch (e:Exception){
            Log.d("follow R",e.toString())
            Result.failure(e)
        }
        emit(result)
    }
    fun changePassword(token: String,email: String,password1: String,password2: String)= liveData(Dispatchers.IO){
        val result=try {
            Log.d("follow R","1")
            val response= UserNetwork.changePassword(token, email, password1, password2)
            Log.d("follow R","2")
            if (response.code==200){
                Result.success(response.data)
            }else{
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        } catch (e:Exception){
            Log.d("follow R",e.toString())
            Result.failure(e)
        }
        emit(result)
    }
    fun setBottle(token: String,email: String,type: String,degree:String)= liveData(Dispatchers.IO){
        val result=try {
            Log.d("setBottle","1")
            val response= UserNetwork.setBottles(token, email, type, degree)
            Log.d("setBottle","2")
            if (response.code==200){
                Log.d("setBottle","3")
                Result.success(response.message)
            }else{
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        }catch (e:Exception){
            Log.d("setBottle",e.toString())
            Result.failure(e)
        }
        emit(result)
    }
    fun checkBottle(token: String,email: String)= liveData(Dispatchers.IO){
        val result=try {
            Log.d("checkBottle","1")
            val response= UserNetwork.checkBottle(token, email)
            Log.d("checkBottle","2")
            if (response.code==200){
                Log.d("checkBottle","3")
                Result.success(response.data)
            }else{
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        }catch (e:Exception){
            Log.d("checkBottle",e.toString())
            Result.failure(e)
        }
        emit(result)
    }
    fun getMyPost(token: String,email: String)= liveData(Dispatchers.IO){
        val result=try {
            Log.d("getMyPost","1")
            val response= UserNetwork.getMyPost(token, email)
            Log.d("getMyPost","2")
            if (response.code==200){
                Log.d("getMyPost","3")
                val resultList = mutableListOf<PostModel>()
                response.data.forEach { list ->
                    Log.d("getMyPost",list[2])
                    resultList.add(
                        PostModel(
                            "",list[1],list[2],list[3].toLong(),list[4].toInt(),list[5].toInt(),list[0]
                        )
                    )
                }
                Result.success(resultList.toList())
            }else{
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        }catch (e:Exception){
            Log.d("getMyPost",e.toString())
            Result.failure(e)
        }
        emit(result)
    }
    /**
     * Detail
     */
    fun addLike(type: String, target: String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("motherfucker", "rep addLike")
            val response = DetailNetwork.addLike(type, target)
            if (response.code == 1) {
                Result.success(response.data)
            } else {
                Result.failure(RuntimeException("response status is${response.msg}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun cancelLike(type: String, target: String) = liveData(Dispatchers.IO) {
        val result = try {
            val response = DetailNetwork.cancelLike(type, target)
            if (response.code == 1) {
                Result.success(response.data)
            } else {
                Result.failure(RuntimeException("response status is${response.msg}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun addComment(token:String,username: String, type: Int, word: String, target: String, level: Int) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = DetailNetwork.addComment(token,username, type, word, target, level)
                if (response.code == 1) {
                    Result.success(true)
                } else {
                    Result.failure(RuntimeException("response status is${response.msg}"))
                }
            } catch (e: Exception) {

                Result.failure(e)
            }
            emit(result)
        }

    fun getComments(type: Int, target: String, level: Int, page: Int, pageSize: Int) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = DetailNetwork.getComments(type, target, level, page, pageSize)
                if (response.code == 1) {
                    Result.success(response.data)
                } else {
                    Result.failure(RuntimeException("response status is${response.msg}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

    /**
     * Community
     */
    fun getAllPost() = liveData(Dispatchers.IO) {
        val result = try {
            val response = CommunityNetwork.getAllPost()
            if (response.code == 200) {
                val resultList = mutableListOf<PostModel>()
                response.data.forEach { list ->
                    Log.d("PostUrl",list[3])
                    resultList.add(
                        PostModel(
                            list[0], list[1],list[2],
                            list[3].toLong(), list[4].toInt(),
                            list[5].toInt(), list[6]
                        )
                    )
                }
                Result.success(resultList.toList())
            } else {
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun getUrls(token: String,id: String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("urlSize","被调用")
            val response = CommunityNetwork.getUrl(token,id)
            if (response.code == 200) {
                Log.d("urlSize",response.data.size.toString())
                val resultList = mutableListOf<String>()
                response.data.forEach { url ->
                    resultList.addAll(url)
                }
                Result.success(resultList.toList())
            } else {
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        } catch (e: Exception) {
            Log.d("urlSize","cuo wu")
            Result.failure(e)
        }
        emit(result)
    }

    fun getPostDetail(token: String,id: String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("get detail","被调用")
            val response = CommunityNetwork.getPostDetail(token,id)
            Log.d("get detail",id)
            if (response.code == 200) {
                val resultList = mutableListOf<PostDetailModel>()
                response.data.forEach { detail ->
                    Log.d("get detail nickname",detail[0])
                    resultList.add(
                        PostDetailModel(
                            detail[0], detail[1], detail[2],
                            detail[3].toLong(), detail[4].toInt(),
                            detail[5].toInt(), detail[6]
                        )
                    )
                }
                Result.success(resultList.toList())
            } else {
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        } catch (e: Exception) {
            Log.d("get detail","cuo wu")
            Result.failure(e)
        }
        emit(result)
    }

    fun getSubPost(token: String,email: String) = liveData(Dispatchers.IO) {
        val result = try {
            val response = CommunityNetwork.getSubPost(token,email)
            if (response.code == 200) {
                val resultList = mutableListOf<PostModel>()
                response.data.forEach { list ->
                    resultList.add(
                        PostModel(
                            list[0], list[1],list[2],
                            list[3].toLong(), list[4].toInt(),
                            list[5].toInt(), list[6]
                        )
                    )
                }
                Result.success(resultList.toList())
            } else {
                Result.failure(RuntimeException("response status is${response.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
    fun addPost(token: String,email: String,title: MultipartBody.Part,text: MultipartBody.Part,fileList: List<MultipartBody.Part>)= liveData(Dispatchers.IO){
            val result=try {
                Log.d("addPost","被调用")
                val response= CommunityNetwork.addPost(token, email, title, text, fileList)
                Log.d("addPost","成功一半")
                if (response.code==200){
                    Log.d("addPost","成功")
                    Result.success(response.message)
                }else{
                    Result.failure(RuntimeException("response status is${response.message}"))
                }
            }catch (e:Exception){
                Log.d("addPost",e.toString())
                Result.failure(e)
            }
            emit(result)
        }
    /**
     * Collect
     */
    fun addCollect(token: String,email: String, type: Int, target: String,firstUrl:String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = CollectNetwork.addCollect(token, email, type, target, firstUrl)
                if (response.code == 1) {
                    Result.success(response.data)
                } else {
                    Result.failure(RuntimeException("response status is${response.msg}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

    fun deleteCollect(token: String,id: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = CollectNetwork.deleteCollect(token, id)
                if (response.code == 1) {
                    Result.success(response.data)
                } else {
                    Result.failure(RuntimeException("response status is${response.msg}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

    fun updateCollect(token: String,email: String, type: Int, target: String,firstUrl:String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = CollectNetwork.updateCollect(token, email, type, target, firstUrl)
                if (response.code == 1) {
                    Result.success(response.data)
                } else {
                    Result.failure(RuntimeException("response status is${response.msg}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

    fun getCollects(token: String,email: String,type: Int,page: Int,pageSize: Int) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = CollectNetwork.getCollects(token, email, type, page, pageSize)
                if (response.code == 1) {
                    Result.success(response.data)
                } else {
                    Result.failure(RuntimeException("response status is${response.msg}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
    fun getCollectCount(token: String, target: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = CollectNetwork.getCollectCount(token,target)
                if (response.code == 1) {
                    Result.success(response.map["count"])
                } else {
                    Result.failure(RuntimeException("response status is${response.msg}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
    /**
     * OtherDetail
     */
    fun getIntroHis( target: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = OtherDetailNetwork.getIntroHis(target)
                if (response.code == 200) {
                    Result.success(response.data)
                } else {
                    Result.failure(RuntimeException("response status is${response.message}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
    fun getFood( target: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = OtherDetailNetwork.getFood(target)
                if (response.code == 200) {
                    val list = mutableListOf<TwoParamShowModel>()
                    response.data.forEach {
                        list.add(TwoParamShowModel(it[0],it[1]))
                    }
                    Result.success(list.toList())
                } else {
                    Result.failure(RuntimeException("response status is${response.message}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
    fun getRelatedSight( target: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = OtherDetailNetwork.getRelatedSight(target)
                if (response.code == 200) {
                    val list = mutableListOf<TwoParamShowModel>()
                    response.data.forEach {
                        list.add(TwoParamShowModel(it[0],it[1]))
                    }
                    Result.success(list.toList())
                } else {
                    Result.failure(RuntimeException("response status is${response.message}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
    fun getRecipe( target: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = OtherDetailNetwork.getRecipe(target)
                if (response.code == 200) {
                    val list = mutableListOf<TwoParamShowModel>()
                    response.data.forEach {
                        list.add(TwoParamShowModel(it[0],it[1]))
                    }
                    Result.success(list.toList())
                } else {
                    Result.failure(RuntimeException("response status is${response.message}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

    fun getRestaurant( target: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val response = OtherDetailNetwork.getRestaurant(target)
                if (response.code == 200) {
                    val list = mutableListOf<TwoParamShowModel>()
                    response.data.forEach {
                        list.add(TwoParamShowModel(it[0],it[1]))
                    }
                    Result.success(list.toList())
                } else {
                    Result.failure(RuntimeException("response status is${response.message}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
}