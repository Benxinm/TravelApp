package com.benxinm.travelapp.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.benxinm.travelapp.data.User
import com.benxinm.travelapp.data.responseModel.PostDetailModel
import com.benxinm.travelapp.data.responseModel.PostModel
import com.benxinm.travelapp.logic.network.util.network.*
import kotlinx.coroutines.Dispatchers

object Repository {
    /**
     * Login
     */
    fun login(username: String, password: String) = liveData(Dispatchers.IO) {
        val result = try {
            val loginResponse = LoginNetwork.login(username, password)
            if (loginResponse.code == 200) {
                val user = loginResponse.data
                Result.success(user)
            } else {
                Result.failure(RuntimeException("response status is${loginResponse.message}"))
            }
        } catch (e: Exception) {
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
            val sendEmailResponse = LoginNetwork.sendEmail(email)
            if (sendEmailResponse.code == 200) {
                Result.success(true)
            } else {
                Result.failure(RuntimeException("response status is${sendEmailResponse.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun verifyCode(code: String) = liveData(Dispatchers.IO) {
        val result = try {
            val codeResponse = LoginNetwork.verifyCode(code)
            if (codeResponse.code == 200) {
                Result.success(true)
            } else {
                Result.failure(RuntimeException("response status is${codeResponse.message}"))
            }
        } catch (e: Exception) {
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
                Log.d("motherfucker","fuck1")
                val response = DetailNetwork.addComment(token,username, type, word, target, level)
                Log.d("motherfucker",response.code.toString())
                if (response.code == 1) {
                    Log.d("received","${response.msg}")
                    Result.success(true)
                } else {
                    Result.failure(RuntimeException("response status is${response.msg}"))
                }
            } catch (e: Exception) {
                Log.d("motherfucker","fuck")
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
                    resultList.add(
                        PostModel(
                            list[0], list[1],
                            list[2].toLong(), list[3].toInt(),
                            list[4].toInt(), list[5]
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

    fun getUrls(id: String) = liveData(Dispatchers.IO) {
        val result = try {
            val response = CommunityNetwork.getUrl(id)
            if (response.code == 200) {
                val resultList = mutableListOf<String>()
                response.data.forEach { url ->
                    resultList.add(url[0])
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

    fun getPostDetail(id: String) = liveData(Dispatchers.IO) {
        val result = try {
            val response = CommunityNetwork.getPostDetail(id)
            if (response.code == 200) {
                val resultList = mutableListOf<PostDetailModel>()
                response.data.forEach { detail ->
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
            Result.failure(e)
        }
        emit(result)
    }

    fun getSubPost(email: String) = liveData(Dispatchers.IO) {
        val result = try {
            val response = CommunityNetwork.getSubPost(email)
            if (response.code == 200) {
                val resultList = mutableListOf<PostModel>()
                response.data.forEach { list ->
                    resultList.add(
                        PostModel(
                            list[0], list[1],
                            list[2].toLong(), list[3].toInt(),
                            list[4].toInt(), list[5]
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
}