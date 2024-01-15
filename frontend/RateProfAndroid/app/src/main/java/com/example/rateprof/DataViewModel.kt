package com.example.rateprof

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataViewModel : ViewModel() {
    private val namesLiveData = MutableLiveData<List<String>?>()
    private val namesLiveDataOcenyJak = MutableLiveData<Double?>()
    private val namesLiveDataOcenyTru = MutableLiveData<Double?>()
    private val namesLiveDataPseudonimy = MutableLiveData<List<String>?>()
    private val namesLiveDataTresci = MutableLiveData<List<String>?>()

    fun getNamesLiveData(): MutableLiveData<List<String>?> = namesLiveData
    fun getNamesLiveDataOcenyJak(): MutableLiveData<Double?> = namesLiveDataOcenyJak
    fun getNamesLiveDataOcenyTru(): MutableLiveData<Double?> = namesLiveDataOcenyTru
    fun getNamesLiveDataPseudonimy(): MutableLiveData<List<String>?> = namesLiveDataPseudonimy
    fun getNamesLiveDataTresci(): MutableLiveData<List<String>?> = namesLiveDataTresci

    fun fetchData(nazwaUczelni: String, apiInterface: ApiInterface) {
        viewModelScope.launch {
            try {
                val idUczelniResponse = withContext(Dispatchers.IO) {
                    apiInterface.getIdUczelni(nazwaUczelni).execute()
                }

                if (idUczelniResponse.isSuccessful) {
                    val idUczelni = idUczelniResponse.body()

                    if (idUczelni != null) {
                        try {
                            val namesResponse = withContext(Dispatchers.IO) {
                                apiInterface.getNamesByUczelni(idUczelni).execute()
                            }

                            if (namesResponse.isSuccessful) {
                                val names = namesResponse.body()
                                namesLiveData.value = names
                                names?.forEach { name ->
                                    Log.d("DataViewModel", "Fetched name: $name")
                                }
                            } else {
                                handleFailure(Exception("Error fetching names"))
                            }
                        } catch (e: Exception) {
                            handleFailure(e)
                        }
                    } else {
                        handleFailure(Exception("Received null idUczelni"))
                    }
                } else {
                    handleFailure(Exception("Error fetching idUczelni"))
                }
            } catch (e: Exception) {
                handleFailure(e)
            }
        }
    }

    fun fetchAllNames(apiInterface: ApiInterface) {
        viewModelScope.launch {
            try {
                val allNamesResponse = withContext(Dispatchers.IO) {
                    apiInterface.getAllNames().execute()
                }

                if (allNamesResponse.isSuccessful) {
                    val allNames = allNamesResponse.body()
                    namesLiveData.value = allNames
                    allNames?.forEach { name ->
                        Log.d("DataViewModel", "Fetched name: $name")
                    }
                } else {
                    handleFailure(Exception("Error fetching all names"))
                }
            } catch (e: Exception) {
                handleFailure(e)
            }
        }
    }

    fun fetchOcenaTru(prowadzacy_nazwa: String, apiInterface: ApiInterface) {
        viewModelScope.launch {
            try {
                val idProwadzacegoResponse = withContext(Dispatchers.IO) {
                    apiInterface.getIdByName(prowadzacy_nazwa).execute()
                }

                if (idProwadzacegoResponse.isSuccessful) {
                    val idProwadzacego = idProwadzacegoResponse.body()

                    if (idProwadzacego != null) {
                        try {
                            val ocenaTruResponse = withContext(Dispatchers.IO) {
                                apiInterface.getMeanTruByIdProw(idProwadzacego).execute()
                            }

                            if (ocenaTruResponse.isSuccessful) {
                                val ocenaTru = ocenaTruResponse.body()
                                namesLiveDataOcenyTru.value = ocenaTru
                            } else {
                                handleFailure(Exception("Error fetching oceny"))
                            }
                        } catch (e: Exception) {
                            handleFailure(e)
                        }
                    } else {
                        handleFailure(Exception("Received null idProwadzacego"))
                    }
                } else {
                    handleFailure(Exception("Error fetching idProwadzacego"))
                }
            } catch (e: Exception) {
                handleFailure(e)
            }
        }
    }

    fun fetchOcenaJak(prowadzacy_nazwa: String, apiInterface: ApiInterface) {
        viewModelScope.launch {
            try {
                val idProwadzacegoResponse = withContext(Dispatchers.IO) {
                    apiInterface.getIdByName(prowadzacy_nazwa).execute()
                }

                if (idProwadzacegoResponse.isSuccessful) {
                    val idProwadzacego = idProwadzacegoResponse.body()

                    if (idProwadzacego != null) {
                        try {
                            val ocenaJakResponse = withContext(Dispatchers.IO) {
                                apiInterface.getMeanJakByIdProw(idProwadzacego).execute()
                            }

                            if (ocenaJakResponse.isSuccessful) {
                                val ocenaJak = ocenaJakResponse.body()
                                namesLiveDataOcenyJak.value = ocenaJak
                            } else {
                                handleFailure(Exception("Error fetching oceny"))
                            }
                        } catch (e: Exception) {
                            handleFailure(e)
                        }
                    } else {
                        handleFailure(Exception("Received null idProwadzacego"))
                    }
                } else {
                    handleFailure(Exception("Error fetching idProwadzacego"))
                }
            } catch (e: Exception) {
                handleFailure(e)
            }
        }
    }

    fun fetchPseudonimy(prowadzacy_nazwa: String, apiInterface: ApiInterface) {
        viewModelScope.launch {
            try {
                val idProwadzacegoResponse = withContext(Dispatchers.IO) {
                    apiInterface.getIdByName(prowadzacy_nazwa).execute()
                }

                if (idProwadzacegoResponse.isSuccessful) {
                    val idProwadzacego = idProwadzacegoResponse.body()

                    if (idProwadzacego != null) {
                        try {
                            val pseudonimyResponse = withContext(Dispatchers.IO) {
                                apiInterface.getPseudonimy(idProwadzacego).execute()
                            }

                            if (pseudonimyResponse.isSuccessful) {
                                val pseudonimy = pseudonimyResponse.body()
                                namesLiveDataPseudonimy.value = pseudonimy
                                pseudonimy?.forEach { name ->
                                    Log.d("DataViewModel", "Fetched pseudonim: $name")
                                }
                            } else {
                                handleFailure(Exception("Error fetching pseudonimy"))
                            }
                        } catch (e: Exception) {
                            handleFailure(e)
                        }
                    } else {
                        handleFailure(Exception("Received null idProwadzacego"))
                    }
                } else {
                    handleFailure(Exception("Error fetching idProwadzacego"))
                }
            } catch (e: Exception) {
                handleFailure(e)
            }
        }
    }

    fun fetchTresci(prowadzacy_nazwa: String, apiInterface: ApiInterface) {
        viewModelScope.launch {
            try {
                val idProwadzacegoResponse = withContext(Dispatchers.IO) {
                    apiInterface.getIdByName(prowadzacy_nazwa).execute()
                }

                if (idProwadzacegoResponse.isSuccessful) {
                    val idProwadzacego = idProwadzacegoResponse.body()

                    if (idProwadzacego != null) {
                        try {
                            val tresciResponse = withContext(Dispatchers.IO) {
                                apiInterface.getTresci(idProwadzacego).execute()
                            }

                            if (tresciResponse.isSuccessful) {
                                val tresci = tresciResponse.body()
                                namesLiveDataTresci.value = tresci
                                tresci?.forEach { name ->
                                    Log.d("DataViewModel", "Fetched tresc: $name")
                                }
                            } else {
                                handleFailure(Exception("Error fetching tresci"))
                            }
                        } catch (e: Exception) {
                            handleFailure(e)
                        }
                    } else {
                        handleFailure(Exception("Received null idProwadzacego"))
                    }
                } else {
                    handleFailure(Exception("Error fetching idProwadzacego"))
                }
            } catch (e: Exception) {
                handleFailure(e)
            }
        }
    }

    private fun handleFailure(error: Throwable) {
        Log.e("DataViewModel", "Error during data fetching", error)
        namesLiveData.value = null
    }
}


