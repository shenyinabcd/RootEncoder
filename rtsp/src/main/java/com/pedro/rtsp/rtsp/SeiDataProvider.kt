package com.pedro.rtsp.rtsp

interface SeiDataProvider {
    fun getImuData(): ByteArray
}