package com.pedro.rtsp.rtp.packets

import com.pedro.rtsp.utils.decodeHex

class UtilsSei {
    companion object {
        public fun muxSEI(msg: String): ByteArray {
            val seiContent = msg.toByteArray()
            val seiType = byteArrayOf(0x06, 0x05)
            val seiUuid = byteArrayOf(
                0x01, 0x02, 0x03, 0x04, 0x01, 0x02, 0x03, 0x04,
                0x01, 0x02, 0x03, 0x04, 0x01, 0x02, 0x03, 0x04
            )
            val seiEnd = byteArrayOf(0x80.toByte())
            var contentSize = 16 + seiContent.size

            //数据长度(数据长度减去255，有多少个就写多少个FF，剩下的不为0，再写一个字节)
            var ffCount = 0
            while (true) {
                if (contentSize >= 255) {
                    ffCount++
                }
                if (contentSize < 255) {
                    break
                }
                contentSize -= 255
            }

            val size = Integer.toHexString(contentSize)
//            val contentLastSize: ByteArray = Hex.decodeHex(size)
            val contentLastSize: ByteArray = size.decodeHex()
            val contentFirstSize = ByteArray(ffCount)
            for (i in 0 until ffCount) {
                contentFirstSize[i] = 0xff.toByte()
            }
            val sei_content_size = combineArrays(contentFirstSize, contentLastSize)

            return combineArrays(seiType, sei_content_size, seiUuid, seiContent, seiEnd)
        }

//        private fun convertIntToByteArray(data: Int) : ByteArray {
//            return byteArrayOf((data shr 0).toByte(),
//                (data shr 8).toByte(),(data shr 16).toByte(),(data shr 24).toByte()
//            )
//        }

        private fun combineArrays(vararg a: ByteArray): ByteArray {
            var massLength = 0
            for (b in a) {
                massLength += b.size
            }
            val c = ByteArray(massLength)
            var d: ByteArray
            var index = 0
            for (anA in a) {
                d = anA
                System.arraycopy(d, 0, c, 0 + index, d.size)
                index += d.size
            }
            return c
        }
    }
}