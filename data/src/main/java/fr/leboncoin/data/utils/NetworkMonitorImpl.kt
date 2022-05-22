package fr.leboncoin.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import javax.inject.Inject

class NetworkMonitorImpl @Inject constructor(context: Context) :
    ConnectivityManager.NetworkCallback(), NetworkMonitor {


    private var mNetworkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()
    private var mConnectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var isNetworkOk = false

    init {
        mConnectivityManager.registerNetworkCallback(mNetworkRequest, this)
    }


    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        isNetworkOk = true
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        isNetworkOk = false
    }

    override fun hasNetwork(): Boolean {
        return isNetworkOk
    }
}

